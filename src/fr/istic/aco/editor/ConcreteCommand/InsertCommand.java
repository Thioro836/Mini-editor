package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.ClasseImpl.Invoker;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;

/**
 * The {@code InsertCommand} class implements the  Command interface
 * and is responsible for inserting text into the engine at the specified
 * location.
 */
public class InsertCommand implements Command {
    private Engine engine;
    private Invoker inv;
    private String textToInsert;

    /**
     * Constructs an {@code InsertCommand} with the specified engine and invoker.
     * 
     * @param engine the engine where the text will be inserted
     * @param inv    the invoker providing the text to insert
     */
    public InsertCommand(Engine engine, Invoker inv) {
        this.engine = engine;
        this.inv = inv;

    }

    /**
     * Executes the insert command by retrieving the text from the invoker and
     * inserting it into the engine.
     */
    @Override
    public void execute() {
        engine.insert(inv.getTextToInsert());
    }

}
