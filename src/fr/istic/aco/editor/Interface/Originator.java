package fr.istic.aco.editor.Interface;

public interface Originator {
    /**
     * Creates and returns a {@link Memento} object that represents the current
     * state of the originator.
     * 
     * @return A {@link Memento} object that contains the current state of the
     *         originator.
     */
    Memento getMemento();

    /**
     * Restores the state of the originator from the given {@link Memento}.
     * 
     * @param m The {@link Memento} object that contains the state to restore.
     */
    void setMemento(Memento m);
}
