package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.ClassImpl.UndoManager;

/**
 * The {@code RedoCommand} class implements the {@link Command} interface and is
 * responsible
 * for executing the redo action within the application. It interacts with the
 * {@link UndoManager}
 * to perform the redo operation.
 * <p>
 * This command is typically used to restore the previous undone action,
 * effectively reapplying
 * the most recent action that was undone by the {@link UndoCommand}.
 * </p>
 */
public class RedoCommand implements Command {

    private UndoManager undoManager;

    /**
     * Constructs a new {@code RedoCommand} with the specified {@link UndoManager}.
     *
     * @param undoManager The {@link UndoManager} that will handle the redo
     *                    functionality.
     */
    public RedoCommand(UndoManager undoManager) {
        this.undoManager = undoManager;

    }

    /**
     * Executes the redo action by calling the {@code redo} method on the
     * {@link UndoManager}.
     * This restores the most recent undone action, if any.
     */

    @Override
    public void execute() {
        undoManager.redo();

    }

}
