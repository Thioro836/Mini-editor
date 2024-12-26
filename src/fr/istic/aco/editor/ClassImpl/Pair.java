package fr.istic.aco.editor.ClassImpl;

import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;

/**
 * A generic class that encapsulates a pair consisting of a
 * {@code CommandOriginator}
 * and its corresponding {@code Memento}.
 * This class is used to associate a command with its saved state.
 *
 * @param <CommandOriginator> the type representing the command
 * @param <Memento>           the type representing the saved state of the
 *                            command
 */
public class Pair<CommandOriginator, Memento> {

    /**
     * Constructs a new {@code Pair} object with the specified command and memento.
     *
     * @param c the command to associate with the memento
     * @param m the memento representing the saved state of the command
     */
    private CommandOriginator command;
    private Memento memento;

    Pair(CommandOriginator c, Memento m) {
        this.command = c;
        this.memento = m;
    }

    /**
     * Retrieves the command stored in this pair.
     *
     * @return the {@code CommandOriginator} instance
     */
    CommandOriginator getCommandOriginator() {
        return command;
    }

    /**
     * Retrieves the memento stored in this pair.
     *
     * @return the {@code Memento} instance
     */
    Memento getMemento() {
        return memento;
    }

}
