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

/**
 * The {@code Invoker} class is responsible for managing and executing both
 * originator and concrete commands. It acts as a bridge between the user inputs
 * and the execution of corresponding commands.
 */
public class Invoker {
    // Map to store commands originator associated with an identifier (String)
    private Map<String, CommandOriginator> map;
    // Map to store concret commands
    private Map<String, Command> mapCommand;
    private Engine engine;
    private Selection selection;
    private UndoManager undoManager;
    private Recorder recorder;
    private InsertCommand insertCommand;
    private String textToInsert;
    private int beginIndex, endIndex;

    /**
     * Constructs an {@code Invoker} instance and initializes command mappings.
     *
     * @param engine      the engine instance for text manipulation
     * @param selection   the selection instance for text selection
     * @param recorder    the recorder instance for recording commands
     * @param undoManager the undo manager instance for undo/redo functionality
     */
    public Invoker(Engine engine, Selection selection, Recorder recorder, UndoManager undoManager) {
        map = new HashMap<>();
        mapCommand = new HashMap<>();
        this.engine = engine;
        this.selection = selection;
        this.recorder = recorder;
        this.undoManager = undoManager;
        this.textToInsert = "";
        this.beginIndex = 0;
        this.endIndex = 0;
        /**
         * Initializes both originator and concrete commands and maps them to
         * identifiers.
         */
        insertCommand = new InsertCommand(engine, this, recorder, undoManager);
        map.put("insert", insertCommand);
        map.put("cut", new CutCommand(engine, selection, recorder, undoManager));
        map.put("copy", new CopyCommand(engine, selection, recorder, undoManager));
        map.put("delete", new DeleteCommand(engine, selection, recorder, undoManager));
        map.put("paste", new PasteCommand(engine, selection, recorder, undoManager));
        map.put("selection", new SelectionCommand(selection, this, recorder, undoManager));

        // Initialize concrete commands
        mapCommand.put("start", new StartCommand(recorder));
        mapCommand.put("stop", new StopCommand(recorder));
        mapCommand.put("replay", new ReplayCommand(recorder));
        mapCommand.put("undo", new UndoCommand(undoManager));
        mapCommand.put("redo", new RedoCommand(undoManager));

    }

    /**
     * Gets the text to be inserted.
     *
     * @return the text to insert
     */
    public String getTextToInsert() {
        return textToInsert;
    }

    /**
     * Sets the text to be inserted.
     *
     * @param text the text to insert
     */
    public void setTextToInsert(String text) {
        this.textToInsert = text;
    }

    /**
     * Gets the beginning index of the selection.
     *
     * @return the begin index
     */

    public int getBeginIndex() {
        return beginIndex;
    }

    /**
     * Gets the end index of the selection.
     *
     * @return the end index
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * Sets the beginning index of the selection.
     *
     * @param beginIndex the new begin index
     */
    public void setBeginIndex(int beginIndex) {

        this.beginIndex = beginIndex;
    }

    /**
     * Sets the end index of the selection.
     *
     * @param endIndex the new end index
     */
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
