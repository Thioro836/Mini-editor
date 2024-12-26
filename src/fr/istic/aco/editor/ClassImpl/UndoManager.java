package fr.istic.aco.editor.ClassImpl;

import java.util.ArrayList;
import java.util.List;

import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Memento.EditorMemento;

public class UndoManager {
    private List<EditorMemento> pastStates;
    private List<EditorMemento> futureStates;
    private Engine engine;
    private EditorMemento currentState;

    public UndoManager(Engine engine) {
        pastStates = new ArrayList<>();
        futureStates = new ArrayList<>();
        this.engine = engine;
        // Sauvegarder l'état initial
        currentState = (EditorMemento) engine.getMemento();
    }

    public void store() {
        try {
            EditorMemento newState = (EditorMemento) engine.getMemento();
            if (newState == null) {
                System.out.println("Error: Current memento is null");
                return;
            }
            
            // Ne stockez que si l'état a changé
            if (!statesAreEqual(currentState, newState)) {
                pastStates.add(currentState);
                currentState = newState;
                futureStates.clear(); // Effacer les états futurs car une nouvelle action a été effectuée
                System.out.println("Storing state: " + newState.getBufferContent());
            }
        } catch (Exception e) {
            System.out.println("Error in store(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void undo() {
        if (!pastStates.isEmpty()) {
            // Sauvegarder l'état actuel dans les états futurs
            futureStates.add(currentState);
            
            // Restaurer l'état précédent
            currentState = pastStates.remove(pastStates.size() - 1);
            System.out.println("Restoring to previous state: " + currentState.getBufferContent());
            engine.setMemento(currentState);
            
            System.out.println("Buffer content after setMemento: " + engine.getBufferContents());
        }
    }

    public void redo() {
        if (!futureStates.isEmpty()) {
            // Sauvegarder l'état actuel dans les états passés
            pastStates.add(currentState);
            
            // Restaurer l'état suivant
            currentState = futureStates.remove(futureStates.size() - 1);
            engine.setMemento(currentState);
        }
    }

    private boolean statesAreEqual(EditorMemento state1, EditorMemento state2) {
        if (state1 == null || state2 == null) return false;
        return state1.getBufferContent().equals(state2.getBufferContent()) &&
               state1.getBeginIndex() == state2.getBeginIndex() &&
               state1.getEndIndex() == state2.getEndIndex() &&
               state1.getClipboardContent().equals(state2.getClipboardContent());
    }

    public List<EditorMemento> getPastStates() {
        return this.pastStates;
    }

    public List<EditorMemento> getFutureStates() {
        return this.futureStates;
    }
}
