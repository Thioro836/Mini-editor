package fr.istic.aco.editor.ClassImpl;

import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;

public class Pair<CommandOriginator, Memento> {

    private CommandOriginator command;
    private Memento memento;

    /**
     * Constructs a new {@code Pair} object with the specified command and memento.
     * 
     * @param c the command to be associated with the memento
     * @param m the memento representing the state of the command
     */
    Pair(CommandOriginator c, Memento m) {
        this.command = c;
        this.memento = m;
    }

    /**
     * Gets the command stored in this pair.
     * 
     * @return the {@code CommandOriginator} instance
     */

    CommandOriginator getCommandOriginator() {
        return command;
    }

    /**
     * Gets the memento stored in this pair.
     * 
     * @return the {@code Memento} instance
     */

    Memento getMemento() {
        return memento;
    }

}
