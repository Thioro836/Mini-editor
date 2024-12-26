package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Selection;
import fr.istic.aco.editor.Memento.SelectMemento;
import fr.istic.aco.editor.Interface.Recorder;

public class CutCommand implements CommandOriginator {
    private Engine engine;
    private Selection selection;
    private Recorder recorder;
    private boolean replay;
     private UndoManager undoManager;
    public CutCommand(Engine engine, Selection selection, Recorder recorder,UndoManager undoManager) {
        this.engine = engine;
        this.selection = selection;
        this.recorder = recorder;
        this.undoManager=undoManager;
        this.replay = false;
    }

    @Override
    public void execute() {
        System.out.println("Indices de sélection avant coupure: Begin Index: " + selection.getBeginIndex()+ ", End Index: " + selection.getEndIndex());
        engine.cutSelectedText();
        undoManager.store();
        recorder.save(this);
      
        
    }

    @Override
    public void setMemento(Memento memento) {

    }

    @Override
    public Memento getMemento() {
        return null;
    }

}
