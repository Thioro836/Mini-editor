package fr.istic.aco.editor.Interface;

/**
 * The {@code CommandOriginator} interface combines the functionalities of the
 * {@link Command}
 * and {@link Originator} interfaces. It represents an object that can execute
 * commands
 * and also manage the creation and restoration of its state, as defined by the
 * Memento design pattern.
 * <p>
 * Classes implementing this interface must provide the behavior for executing
 * commands,
 * as well as managing their state through the {@link Originator} methods such
 * as {@code createMemento}
 * and {@code setMemento}.
 * </p>
 * 
 * @see Command
 * @see Originator
 */

public interface CommandOriginator extends Command, Originator {

}
