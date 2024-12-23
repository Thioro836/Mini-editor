package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Selection;
import fr.istic.aco.editor.Interface.Recorder;

/**
 * The {@code PasteCommand} class implements the {@link CommandOriginator}
 * interface
 * and is responsible for pasting content from the clipboard into the engine at
 * the
 * current selection. It also interacts with the recorder to save the command
 * state
 * after the execution.
 */

public class PasteCommand implements CommandOriginator {
    private Engine engine;
    private Selection selection;
    private Recorder recorder;

    /**
     * Constructs a {@code PasteCommand} with the specified engine, selection, and
     * recorder.
     *
     * @param engine    the engine to perform the paste operation
     * @param selection the selection object that provides the current selection
     *                  position
     * @param recorder  the recorder to save the state of the command after
     *                  execution
     */
    public PasteCommand(Engine engine, Selection selection, Recorder recorder) {
        this.engine = engine;
        this.selection = selection;
        this.recorder = recorder;
    }

    /**
     * Executes the paste operation by pasting the content from the clipboard into
     * the
     * engine at the current selection position. The state of the command is saved
     * using the recorder.
     */
    @Override
    public void execute() {
        engine.pasteClipboard();
        recorder.save(this);
    }

    /**
     * Sets the memento for this command. This method does not do anything in this
     * implementation since the paste command does not maintain any state.
     *
     * @param memento the memento to set
     */
    @Override
    public void setMemento(Memento memento) {
        // No state to restore for the paste command
    }

    /**
     * Gets the memento for this command. This method returns {@code null} because
     * the paste command does not have any state to capture.
     *
     * @return {@code null} since this command does not maintain any state
     */
    @Override
    public Memento getMemento() {
        return null;
    }

}
