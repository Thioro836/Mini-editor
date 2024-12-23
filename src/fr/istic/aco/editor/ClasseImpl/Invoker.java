package fr.istic.aco.editor.ClasseImpl;

import java.util.Map;

import fr.istic.aco.editor.ConcreteCommand.CopyCommand;
import fr.istic.aco.editor.ConcreteCommand.CutCommand;
import fr.istic.aco.editor.ConcreteCommand.DeleteCommand;
import fr.istic.aco.editor.ConcreteCommand.InsertCommand;
import fr.istic.aco.editor.ConcreteCommand.PasteCommand;
import fr.istic.aco.editor.ConcreteCommand.SelectionCommand;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Selection;
import java.util.HashMap;

/**
 * The  Invoker class acts as an intermediary between the user and the
 * command objects.
 * It initializes and stores different commands in a map and provides methods to
 * execute them.
 * This class also manages the text insertion and selection indices.
 */
public class Invoker {
    private Map<String, Command> map;
    private Engine engine;
    private Selection selection;
    private InsertCommand insertCommand;
    private String textToInsert;
    int beginIndex, endIndex;

    /**
     * Constructs an object and initializes the command map and related fields.
     * 
     * @param engine    used for executing text-related commands
     * @param selection used for managing text selection
     */
    public Invoker(Engine engine, Selection selection) {
        map = new HashMap<>();
        this.engine = engine;
        this.selection = selection;
        this.textToInsert = "";
        this.beginIndex = 0;
        this.endIndex = 0;
        // Initializing the different commands with Engine and/or Selection
        insertCommand = new InsertCommand(engine, this);
        map.put("insert", insertCommand);
        map.put("cut", new CutCommand(engine, selection));
        map.put("copy", new CopyCommand(engine, selection));
        map.put("delete", new DeleteCommand(engine, selection));
        map.put("paste", new PasteCommand(engine, selection));
        map.put("selection", new SelectionCommand(selection, this));

    }

    /**
     * Retrieves the text that will be inserted.
     * 
     * @return the text to insert
     */
    public String getTextToInsert() {
        return textToInsert;
    }

    /**
     * Sets the text to be inserted by the insert command.
     * 
     * @param text the text to insert
     */
    public void setTextToInsert(String text) {
        this.textToInsert = text;
    }

    /**
     * Retrieves the starting index of the selection.
     * 
     * @return the starting index of the selection
     */

    public int getBeginIndex() {
        return beginIndex;
    }

    /**
     * Retrieves the ending index of the selection.
     * 
     * @return the ending index of the selection
     */

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

    /**
     * Sets the ending index of the selection.
     * 
     * @param endIndex the ending index of the selection
     */

    public void setEndIndex(int endIndex) {

        this.endIndex = endIndex;
    }

    /**
     * Executes a command based on the given identifier.
     * 
     * @param id the identifier of the command to execute
     * @throws IllegalArgumentException if the command identifier does not exist in
     *                                  the map
     */
    public void playCommand(String id) {
        if (map.containsKey(id)) {
            map.get(id).execute();
        } else {
            System.out.println("la clé spécifié n'existe pas ");
        }

    }

}
