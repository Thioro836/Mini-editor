package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Selection;
import fr.istic.aco.editor.Memento.SelectMemento;
import fr.istic.aco.editor.Interface.Recorder;

/**
 * The SelectionCommand class encapsulates the logic for updating a text
 * selection.
 * It supports undo/redo functionality and command recording for replaying.
 */
public class SelectionCommand implements CommandOriginator {
    private Selection selection;
    private Invoker inv;
    private Recorder recorder;
    private int begin, end;
    private boolean recording;
    private UndoManager undoManager;

    /**
     * Constructs a SelectionCommand with the specified components.
     *
     * @param selection   the selection component for managing text selection
     * @param inv         the invoker that provides user input for selection indices
     * @param recorder    the recorder for saving and replaying commands
     * @param undoManager the undo manager for managing undo/redo operations
     */
    public SelectionCommand(Selection selection, Invoker inv, Recorder recorder, UndoManager undoManager) {
        this.selection = selection;
        this.inv = inv;
        this.recorder = recorder;
        this.undoManager = undoManager;
        begin = 0;
        end = 0;
        recording = false;
    }

    /**
     * Executes the selection update operation.
     * Retrieves the selection indices from the invoker (if not replaying), updates
     * the selection,
     * stores the current state, and saves the command to the recorder.
     */
    @Override
    public void execute() {
        if (!recorder.isReplaying()) {
            this.begin = inv.getBeginIndex();
            this.end = inv.getEndIndex();

        }

        selection.setBeginIndex(this.begin);
        selection.setEndIndex(this.end);
        undoManager.store();
        recorder.save(this);

        System.out.println("Sélection enregistrée: Begin Index: " + selection.getBeginIndex() + ", End Index: "
                + selection.getEndIndex());

    }

    /**
     * Creates a memento to capture the state of the command.
     *
     * @return a memento containing the begin and end indices of the selection
     */
    @Override
    public Memento getMemento() {
        begin = inv.getBeginIndex();
        end = inv.getEndIndex();
        return new SelectMemento(begin, end);
    }

    /**
     * Restores the state of the command from the provided memento.
     *
     * @param memento the memento containing the saved state
     */
    @Override
    public void setMemento(Memento memento) {
        SelectMemento selectMemento = (SelectMemento) memento;
        this.begin = selectMemento.getBeginIndex();
        this.end = selectMemento.getEndIndex();

    }

}
