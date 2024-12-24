package fr.istic.aco.editor.ClassImpl;

import java.util.Map;

import fr.istic.aco.editor.CommandOriginator.CopyCommand;
import fr.istic.aco.editor.CommandOriginator.CutCommand;
import fr.istic.aco.editor.CommandOriginator.DeleteCommand;
import fr.istic.aco.editor.CommandOriginator.InsertCommand;
import fr.istic.aco.editor.CommandOriginator.PasteCommand;
import fr.istic.aco.editor.CommandOriginator.SelectionCommand;
import fr.istic.aco.editor.ConcreteCommand.ReplayCommand;
import fr.istic.aco.editor.ConcreteCommand.StartCommand;
import fr.istic.aco.editor.ConcreteCommand.StopCommand;
import fr.istic.aco.editor.ConcreteCommand.UndoCommand;
import fr.istic.aco.editor.ConcreteCommand.RedoCommand;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.Interface.Selection;

import java.util.HashMap;

public class Invoker {
    // Map to store commands originator associated with an identifier (String)
    private Map<String, CommandOriginator> map;
    // Map to store concret commands
    private Map<String, Command> mapCommand;
    private Engine engine;
    private Selection selection;
    private UndoManager undoManager;
    private Recorder recorder;
    private InsertCommand insertCommand; // Specific command for inserting text
    private String textToInsert; // text to insert
    private int beginIndex, endIndex; // Indices for text selection

    public Invoker(Engine engine, Selection selection, Recorder recorder,UndoManager undoManager) {
        map = new HashMap<>();
        mapCommand = new HashMap<>();
        this.engine = engine;
        this.selection = selection;
        this.recorder = recorder;
        this.undoManager = undoManager;
        this.textToInsert = "";
        this.beginIndex = 0;
        this.endIndex = 0;
        // Initializing the different commands with Engine and/or Selection
        insertCommand = new InsertCommand(engine, this, recorder,undoManager);
        map.put("insert", insertCommand);
        map.put("cut", new CutCommand(engine, selection, recorder));
        map.put("copy", new CopyCommand(engine, selection, recorder));
        map.put("delete", new DeleteCommand(engine, selection, recorder));
        map.put("paste", new PasteCommand(engine, selection, recorder));
        map.put("selection", new SelectionCommand(selection, this, recorder,undoManager));

        // Initializing the different concrete commands with recorder
        mapCommand.put("start", new StartCommand(recorder));
        mapCommand.put("stop", new StopCommand(recorder));
        mapCommand.put("replay", new ReplayCommand(recorder));
        mapCommand.put("undo", new UndoCommand(undoManager));
        mapCommand.put("redo", new RedoCommand(undoManager));

    }

    // Getter to retrieve the text to insert
    public String getTextToInsert() {
        return textToInsert;
    }

    // Setter to define the text to insert before executing the insert command
    public void setTextToInsert(String text) {
        this.textToInsert = text;
    }
    // getteurs et setteurs pour la selection

    public int getBeginIndex() {
        return beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    /**
     * Defines a new selection and checks that the indices are valid.
     * 
     * @param begin the begin index of the selection
     * @param end   the end index of the selection
     * @throws IllegalArgumentException if the indices are out of bounds or
     *                                  inconsistent
     */
    public void setBeginIndex(int beginIndex) {

        this.beginIndex = beginIndex;
    }

    public void setEndIndex(int endIndex) {

        this.endIndex = endIndex;
    }

    /**
     * Executes a command originator based on the given identifier.
     * 
     * @param id the identifier of the command to execute
     */
    public void playCommand(String id) {
        // Check if the command exists in the map before executing it
        if (map.containsKey(id)) {
            map.get(id).execute();
        } else {
            // If the command does not exist, print an error message
            System.out.println("la clé spécifié n'existe pas ");
        }
    }

    /**
     * Executes a command concrete based on the given identifier.
     * 
     * @param id the identifier of the command to execute
     */

    public void playCommandConcrete(String id) {
        // Check if the command exists in the map before executing it
        if (mapCommand.containsKey(id)) {
            mapCommand.get(id).execute();

        } else {
            // If the command does not exist, print an error message
            System.out.println("la clé spécifié n'existe pas ");
        }
    }
}
