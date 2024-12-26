package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Selection;
import fr.istic.aco.editor.Memento.SelectMemento;
import fr.istic.aco.editor.Interface.Recorder;

public class SelectionCommand implements CommandOriginator {
    private Selection selection;
    private Invoker inv;
    private Recorder recorder;
    private int begin, end;
    private boolean recording;
    private UndoManager undoManager;

    public SelectionCommand(Selection selection, Invoker inv, Recorder recorder,UndoManager undoManager) {
        this.selection = selection;
        this.inv = inv;
        this.recorder = recorder;
        this.undoManager=undoManager;
        begin = 0;
        end = 0;
        recording = false;
    }

    @Override
    public void execute() {
        if (!recorder.isReplaying()) {
            this.begin = inv.getBeginIndex();
            this.end = inv.getEndIndex();
            
        }

        selection.setBeginIndex(this.begin);
        selection.setEndIndex(this.end);
        undoManager.store();
        recorder.save(this);
      
        System.out.println("Sélection enregistrée: Begin Index: " + selection.getBeginIndex() + ", End Index: "
                + selection.getEndIndex());

    }

    @Override
    public Memento getMemento() {
        begin = inv.getBeginIndex();
        end = inv.getEndIndex();
        return new SelectMemento(begin, end);
    }

    @Override
    public void setMemento(Memento memento) {
        SelectMemento selectMemento = (SelectMemento) memento;
        this.begin = selectMemento.getBeginIndex();
        this.end = selectMemento.getEndIndex();

    }

}
