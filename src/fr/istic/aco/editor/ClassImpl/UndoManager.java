package fr.istic.aco.editor.ClassImpl;

import java.util.ArrayList;
import java.util.List;

import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Memento.EditorMemento;

/**
 * Manages undo and redo functionality by maintaining a history of states
 * (mementos) for an editor. Allows restoring previous or future states.
 */
public class UndoManager {
    private List<EditorMemento> pastStates;
    private List<EditorMemento> futureStates;
    private Engine engine;
    private EditorMemento currentState;

    /**
     * Constructs an UndoManager for the specified engine and saves the initial
     * state.
     *
     * @param engine the engine whose states will be managed
     */
    public UndoManager(Engine engine) {
        pastStates = new ArrayList<>();
        futureStates = new ArrayList<>();
        this.engine = engine;
        // Save the initial state
        currentState = (EditorMemento) engine.getMemento();
    }

    /**
     * Stores the current state in the history if it has changed since the last
     * save.
     */
    public void store() {
        try {
            EditorMemento newState = (EditorMemento) engine.getMemento();
            if (newState == null) {
                System.out.println("Error: Current memento is null");
                return;
            }

            if (!statesAreEqual(currentState, newState)) {
                pastStates.add(currentState);
                currentState = newState;
                futureStates.clear();
                System.out.println("Storing state: " + newState.getBufferContent());
            }
        } catch (Exception e) {
            System.out.println("Error in store(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Restores the previous state if available, saving the current state for redo.
     */
    public void undo() {
        if (!pastStates.isEmpty()) {

            futureStates.add(currentState);

            currentState = pastStates.remove(pastStates.size() - 1);
            System.out.println("Restoring to previous state: " + currentState.getBufferContent());
            engine.setMemento(currentState);

            System.out.println("Buffer content after setMemento: " + engine.getBufferContents());
        }
    }

    /**
     * Restores the next state if available, saving the current state for undo.
     */
    public void redo() {
        if (!futureStates.isEmpty()) {

            pastStates.add(currentState);

            currentState = futureStates.remove(futureStates.size() - 1);
            engine.setMemento(currentState);
        }
    }

    /**
     * Compares two editor states to determine if they are identical.
     *
     * @param state1 the first state
     * @param state2 the second state
     * @return true if the states are equal; false otherwise
     */
    private boolean statesAreEqual(EditorMemento state1, EditorMemento state2) {
        if (state1 == null || state2 == null)
            return false;
        return state1.getBufferContent().equals(state2.getBufferContent()) &&
                state1.getBeginIndex() == state2.getBeginIndex() &&
                state1.getEndIndex() == state2.getEndIndex() &&
                state1.getClipboardContent().equals(state2.getClipboardContent());
    }

    /**
     * Returns the list of past states.
     *
     * @return a list of past states
     */
    public List<EditorMemento> getPastStates() {
        return this.pastStates;
    }

    /**
     * Returns the list of future states.
     *
     * @return a list of future states
     */
    public List<EditorMemento> getFutureStates() {
        return this.futureStates;
    }
}
