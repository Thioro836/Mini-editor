package fr.istic.aco.editor.Interface;

/**
 * The {@code Command} interface defines the structure for all commands
 * in the editor. Implementing classes must provide an implementation
 * for the {@code execute} method, which performs the associated action.
 */
public interface Command {
    /**
     * Executes the command's action. This method must be implemented by
     * all concrete command classes.
     */
    void execute();
}
