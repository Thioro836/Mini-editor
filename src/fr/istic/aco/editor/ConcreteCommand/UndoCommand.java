package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;

/**
 * The {@code UndoCommand} class implements the {@link Command} interface and is
 * responsible
 * for executing the undo action within the application. It interacts with the
 * {@link UndoManager}
 * to perform the undo operation.
 * <p>
 * This command is typically used to revert the most recent action, effectively
 * undoing the
 * last action performed in the system. It is part of the command pattern,
 * allowing for a
 * clean separation of the action invocation and the underlying implementation.
 * </p>
 */

public class UndoCommand implements Command {

    private UndoManager undoManager;

    /**
     * Constructs a new {@code UndoCommand} with the specified {@link UndoManager}.
     *
     * @param undoManager The {@link UndoManager} that will handle the undo
     *                    functionality.
     */
    public UndoCommand(UndoManager undoManager) {

        this.undoManager = undoManager;

    }

    /**
     * Executes the undo action by calling the {@code undo} method on the
     * {@link UndoManager}.
     * This reverts the most recent action performed, if any.
     */
    @Override
    public void execute() {
        undoManager.undo();

    }

}
