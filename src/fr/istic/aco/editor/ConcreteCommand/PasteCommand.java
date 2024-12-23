package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Selection;

/**
 * The {@code PasteCommand} class implements the  Command interface
 * and is responsible for pasting the contents of the clipboard into the engine.
 */
public class PasteCommand implements Command {
    private Engine engine;
    private Selection selection;

    /**
     * Constructs a {@code PasteCommand} with the specified engine and selection.
     * 
     * @param engine    the engine where the clipboard content will be pasted
     * @param selection the selection that indicates the paste location
     */
    public PasteCommand(Engine engine, Selection selection) {
        this.engine = engine;
        this.selection = selection;
    }

    /**
     * Executes the paste command by invoking the paste operation on the engine.
     */
    @Override
    public void execute() {
        engine.pasteClipboard();
    }

}
