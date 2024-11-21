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
    public UndoManager(Engine engine){
        pastStates=new ArrayList<EditorMemento>();
        futurStates= new ArrayList<EditorMemento>();
        this.engine=engine;
    }
        // enregistrer l'état de l'engine
        public void store(){
            Memento m=engine.getMemento();
            EditorMemento editorMemento=(EditorMemento)m;
            pastStates.add(editorMemento);
            futurStates.clear();
            
        }
        //revenir en arrière
        public void undo(){
            for (int i=0; i<pastStates.size();i++){
              Memento memento= pastStates.get(i);
             
              engine.setMemento(memento);
            }

        }
        //re
    public void redo(){
        
    }
    }
