package fr.istic.aco.editor.ClassImpl;

import java.util.Map;

import fr.istic.aco.editor.CommandOriginator.CopyCommand;
import fr.istic.aco.editor.CommandOriginator.CutCommand;
import fr.istic.aco.editor.CommandOriginator.DeleteCommand;
import fr.istic.aco.editor.CommandOriginator.InsertCommand;
import fr.istic.aco.editor.CommandOriginator.PasteCommand;
import fr.istic.aco.editor.CommandOriginator.SelectionCommand;
import fr.istic.aco.editor.ConcreteCommandd.ReplayCommand;
import fr.istic.aco.editor.ConcreteCommandd.StartCommand;
import fr.istic.aco.editor.ConcreteCommandd.StopCommand;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.Interface.Selection;

import java.util.HashMap;

/**
 * The {@code Invoker} class acts as an intermediary between the user and the
 * command objects.
 * It initializes and stores different commands in a map and provides methods to
 * execute them.
 * This class also manages the text insertion and selection indices.
 */
public class Invoker {

    private Map<String, CommandOriginator> map;
    private Map<String, Command> mapCommand;
    private Engine engine;
    private Selection selection;
    private Recorder recorder;
    private InsertCommand insertCommand; // Specific command for inserting text
    private String textToInsert; // text to insert
    private int beginIndex, endIndex; // Indices for text selection

    /**
     * Constructs an {@code Invoker} with the specified engine, selection, and
     * recorder.
     * Initializes commands and maps them to identifiers.
     * 
     * @param engine    the {@link Engine} instance
     * @param selection the {@link Selection} instance
     * @param recorder  the {@link Recorder} instance
     */
    public Invoker(Engine engine, Selection selection, Recorder recorder) {
        map = new HashMap<>();
        mapCommand = new HashMap<>();
        this.engine = engine;
        this.selection = selection;
        this.recorder = recorder;
        this.textToInsert = "";
        this.beginIndex = 0;
        this.endIndex = 0;
        // Initializing the different commands with Engine and/or Selection
        insertCommand = new InsertCommand(engine, this, recorder);
        map.put("insert", insertCommand);
        map.put("cut", new CutCommand(engine, selection, recorder));
        map.put("copy", new CopyCommand(engine, selection, recorder));
        map.put("delete", new DeleteCommand(engine, selection, recorder));
        map.put("paste", new PasteCommand(engine, selection, recorder));
        map.put("selection", new SelectionCommand(selection, this, recorder));

        // Initializing the different concrete commands with recorder
        mapCommand.put("start", new StartCommand(recorder));
        mapCommand.put("stop", new StopCommand(recorder));
        mapCommand.put("replay", new ReplayCommand(recorder));

    }

    /**
     * Retrieves the text to be inserted.
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
     * Retrieves the begin index of the text selection.
     * 
     * @return the begin index
     */

    public int getBeginIndex() {
        return beginIndex;
    }

    /**
     * Sets the begin index of the text selection.
     * 
     * @param beginIndex the begin index
     * @throws IllegalArgumentException if the index is invalid
     */
    public void setBeginIndex(int beginIndex) {

        this.beginIndex = beginIndex;
    }

    /**
     * Retrieves the end index of the text selection.
     * 
     * @return the end index
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * Sets the end index of the text selection.
     * 
     * @param endIndex the end index
     * @throws IllegalArgumentException if the index is invalid
     */

    public void setEndIndex(int endIndex) {

        this.endIndex = endIndex;
    }

    /**
     * Executes a {@link CommandOriginator} based on the given identifier.
     * 
     * @param id the identifier of the command to execute
     */
    public void playCommand(String id) {
        if (map.containsKey(id)) {
            map.get(id).execute();
        } else {

            System.out.println("la clé spécifié n'existe pas ");
        }
    }

    /**
     * Executes a command concrete based on the given identifier.
     * 
     * @param id the identifier of the command to execute
     */

    public void playCommandConcrete(String id) {

        if (mapCommand.containsKey(id)) {
            mapCommand.get(id).execute();

        } else {

            System.out.println("la clé spécifié n'existe pas ");
        }
    }
}
