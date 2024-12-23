package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Selection;
import fr.istic.aco.editor.Interface.Recorder;

/**
 * The {@code DeleteCommand} class implements the {@link CommandOriginator}
 * interface
 * and is responsible for deleting the currently selected text in the engine.
 * It interacts with the engine to perform the delete operation and uses the
 * recorder
 * to save the command state.
 */
public class DeleteCommand implements CommandOriginator {
    private Engine engine;
    private Selection selection;
    private Recorder recorder;

    /**
     * Constructs a {@code DeleteCommand} with the specified engine, selection, and
     * recorder.
     *
     * @param engine    the engine to perform the delete operation
     * @param selection the selection object that provides the selected text range
     * @param recorder  the recorder to save the state of the command
     */
    public DeleteCommand(Engine engine, Selection selection, Recorder recorder) {
        this.engine = engine;
        this.selection = selection;
        this.recorder = recorder;
    }

    /**
     * Executes the delete operation by invoking the engine's delete method
     * and saves the command state using the recorder.
     */
    @Override
    public void execute() {
        engine.delete();
        recorder.save(this);
    }

    /**
     * Sets the memento for this command. This method is currently not implemented.
     *
     * @param memento the memento to set
     */
    @Override
    public void setMemento(Memento memento) {
        // No implementation for setting memento in this command
    }

    /**
     * Gets the memento for this command. This method returns {@code null}
     * as the DeleteCommand does not currently use mementos.
     *
     * @return {@code null}
     */
    @Override
    public Memento getMemento() {
        return null;
    }

}
