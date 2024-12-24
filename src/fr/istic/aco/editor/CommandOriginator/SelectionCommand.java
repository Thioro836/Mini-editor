package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Selection;
import fr.istic.aco.editor.Memento.SelectMemento;
import fr.istic.aco.editor.Interface.Recorder;

/**
 * The {@code SelectionCommand} class implements the {@link CommandOriginator}
 * interface
 * and is responsible for setting the selection range in the editor by defining
 * the
 * beginning and ending indices. It also interacts with the recorder to save the
 * state of the selection command after execution.
 */
public class SelectionCommand implements CommandOriginator {
    private Selection selection;
    private Invoker inv;
    private Recorder recorder;
    private int begin, end;
    private boolean recording=true;

    /**
     * Constructs a {@code SelectionCommand} with the specified selection, invoker,
     * and recorder.
     *
     * @param selection the selection object to perform the selection operation
     * @param inv       the invoker object that provides the begin and end indices
     *                  for selection
     * @param recorder  the recorder to save the state of the command after
     *                  execution
     */
    public SelectionCommand(Selection selection, Invoker inv, Recorder recorder) {
        this.selection = selection;
        this.inv = inv;
        this.recorder = recorder;
    }

    /**
     * Executes the selection operation by setting the begin and end indices of the
     * selection
     * using the values provided by the invoker. If recording is enabled, the state
     * of the
     * command is saved using the recorder.
     */
    @Override
    public void execute() {

        selection.setBeginIndex(inv.getBeginIndex());
        selection.setEndIndex(inv.getEndIndex());
        if (recording) {
            recorder.save(this);
        }

    }

    /**
     * Gets the memento for this command, capturing the current selection state.
     * This includes the begin and end indices of the selection.
     *
     * @return a {@link SelectMemento} capturing the current selection state
     */
    @Override
    public Memento getMemento() {
        return new SelectMemento(begin, end);
    }

    /**
     * Sets the memento for this command, restoring the selection state.
     * The begin and end indices are set from the provided {@link SelectMemento}.
     *
     * @param memento the memento to set, containing the selection state to restore
     */
    @Override
    public void setMemento(Memento memento) {
        SelectMemento selectMemento = (SelectMemento) memento;
        this.begin = selectMemento.getBeginIndex();
        this.end = selectMemento.getEndIndex();
    }

}
