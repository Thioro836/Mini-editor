package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.ClasseImpl.Invoker;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Selection;

/**
 * The {@code SelectionCommand} class implements the Command interface
 * and allows the user to update the selection range in the text editor.
 */
public class SelectionCommand implements Command {
    private Selection selection;
    private Invoker inv;

    /**
     * Constructs a {@code SelectionCommand} with the specified selection and
     * invoker.
     * 
     * @param selection the selection object to be updated
     * @param inv       the invoker providing the begin and end indices for the
     *                  selection
     */
    public SelectionCommand(Selection selection, Invoker inv) {
        this.selection = selection;
        this.inv = inv;
    }

    /**
     * Executes the selection command by setting the begin and end indices
     * from the invoker to the selection object.
     */
    @Override
    public void execute() {

        selection.setBeginIndex(inv.getBeginIndex());
        selection.setEndIndex(inv.getEndIndex());
    }

}
