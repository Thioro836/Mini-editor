package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Selection;

/**
 * The {@code DeleteCommand} class implements the Command interface
 * and is responsible for deleting the currently selected text in the engine.
 */
public class DeleteCommand implements Command {
    private Engine engine;
    private Selection selection;

    /**
     * Constructs an {@code DeleteCommand} with the specified engine and invoker.
     * 
     * @param engine    the engine to perform the delete operation
     * @param selection the selection object that provides the selected text range
     */
    public DeleteCommand(Engine engine, Selection selection) {
        this.engine = engine;
        this.selection = selection;
    }

    /**
     * Executes the delete command by invoking the delete operation on the engine.
     */
    @Override
    public void execute() {
        engine.delete();
    }

}
