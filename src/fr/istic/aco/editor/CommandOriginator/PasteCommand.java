package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Selection;
import fr.istic.aco.editor.Interface.Recorder;

/**
 * The PasteCommand class encapsulates the logic for pasting selected text
 * from the editor. It integrates with undo/redo functionality and supports
 * recording for replaying commands.
 */
public class PasteCommand implements CommandOriginator {
    private Engine engine;
    private Selection selection;
    private Recorder recorder;
    private UndoManager undoManager;

    /**
     * Constructs a PasteCommand with the specified components.
     *
     * @param engine      the engine that performs editor operations
     * @param selection   the selection handler for managing selected text
     * @param recorder    the recorder for saving commands
     * @param undoManager the undo manager for storing states
     */
    public PasteCommand(Engine engine, Selection selection, Recorder recorder, UndoManager undoManager) {
        this.engine = engine;
        this.selection = selection;
        this.recorder = recorder;
        this.undoManager = undoManager;
    }

    /**
     * Executes the paste operation, storing the action in the undo manager
     * and saving the command to the recorder.
     */
    @Override
    public void execute() {
        engine.pasteClipboard();
        undoManager.store();
        recorder.save(this);

    }

    /**
     * Sets the state (memento) for the command.
     * Currently unimplemented as pastting doesn't modify state.
     *
     * @param memento the memento to restore
     */
    @Override
    public void setMemento(Memento memento) {
        // No implementation needed since the paste command doesn't have state to
        // restore
    }

    /**
     * Retrieves the state (memento) of the command.
     * Currently returns null as pasting doesn't store state.
     *
     * @return null (no state to save for the paste command)
     */
    @Override
    public Memento getMemento() {
        return null;
    }

}
