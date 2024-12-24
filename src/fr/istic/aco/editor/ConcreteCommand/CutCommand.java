package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Selection;

/**
 * The {@code CutCommand} class implements the  Command interface
 * and is responsible for cuting the currently selected text in the engine.
 */
public class CutCommand implements Command {
    private Engine engine;
    private Selection selection;

   /**
     * Constructs an {@code  CutCommand} with the specified engine and invoker.
     * 
     * @param engine    the engine to perform the cut operation
     * @param selection the selection object that provides the selected text range
     */
    public CutCommand(Engine engine, Selection selection) {
        this.engine = engine;
        this.selection = selection;
    }

    /**
     * Executes the cut command by invoking the cut operation on the engine.
     */
    @Override
    public void execute() {
        engine.cutSelectedText();
    }

}
