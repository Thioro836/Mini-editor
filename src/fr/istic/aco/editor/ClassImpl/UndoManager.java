package fr.istic.aco.editor.ClassImpl;

import java.util.ArrayList;
import java.util.List;

import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Memento.EditorMemento;

public class UndoManager {
    private List<EditorMemento> pastStates;
    private List<EditorMemento> futurStates;
    private Engine engine;

    public UndoManager(Engine engine) {
        pastStates = new ArrayList<EditorMemento>();
        futurStates = new ArrayList<EditorMemento>();
        this.engine = engine;
    }

    // enregistrer l'état de l'engine
    public void store() {
        EditorMemento currentMemento = (EditorMemento)engine.getMemento();
        System.out.println("Storing state: " + currentMemento.getBufferContent()+ "'");
        pastStates.add(currentMemento);
        futurStates.clear();
    }

    // revenir en arrière
    
    public void undo() {
        if (!pastStates.isEmpty()) {
            // Sauvegarde de l'état actuel
            EditorMemento currentState = (EditorMemento)engine.getMemento();
            System.out.println("Current state before undo: " + currentState.getBufferContent());
            futurStates.add(0,currentState);
    
            // Restauration de l'état précédent
            EditorMemento previousMemento = pastStates.remove(pastStates.size() - 1);
            System.out.println("Restoring to previous state: " + previousMemento.getBufferContent());
            engine.setMemento(previousMemento);
            
            // Vérification après restauration
            System.out.println("Buffer content after setMemento: " + engine.getBufferContents());
        }
    }

    public void redo() {
        if (!futurStates.isEmpty()) {
            EditorMemento currentState = (EditorMemento)engine.getMemento();
            pastStates.add(currentState);
            EditorMemento nextState = futurStates.remove(futurStates.size() - 1);
            engine.setMemento(nextState);
        }

    }

    public List<EditorMemento> getPastStates() {
        return this.pastStates;
    }

    public List<EditorMemento> getFuturStates() {
        return this.futurStates;
    }
}
