package fr.istic.aco.editor.Interface;

/**
 * This interface represents the Originator in the Memento design pattern.
 * It provides methods for creating and restoring a Memento object, which
 * captures
 * the internal state of the Originator.
 */

public interface Originator {
    /**
     * Creates and returns a {@link Memento} object that represents the current
     * state of the originator.
     * 
     * <p>
     * This method captures the internal state of the originator and allows
     * it to be stored in a memento object for future restoration.
     * </p>
     *
     * @return A {@link Memento} object that contains the current state of the
     *         originator. The state can later be restored using the
     *         {@link #setMemento(Memento)} method.
     */

    Memento getMemento();

    /**
     * Restores the state of the originator from the given {@link Memento}.
     * 
     * <p>
     * This method is used to restore the internal state of the originator
     * from a previously saved {@link Memento} object. The state in the Memento
     * will replace the current internal state of the originator.
     * </p>
     * 
     * @param m The {@link Memento} object that contains the state to restore.
     *          The state of the originator will be replaced by the one stored
     *          in the memento.
     */

    void setMemento(Memento m);
}
