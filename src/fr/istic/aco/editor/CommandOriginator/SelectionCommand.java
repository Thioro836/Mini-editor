package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Selection;
import fr.istic.aco.editor.Memento.InsertMemento;
import fr.istic.aco.editor.Memento.SelectMemento;
import fr.istic.aco.editor.Interface.Recorder;

public class SelectionCommand implements CommandOriginator {
private Selection selection;
private Invoker inv;
private Recorder recorder;
private int begin, end;
public SelectionCommand (Selection selection, Invoker inv, Recorder recorder){
    this.selection=selection;
    this.inv=inv;
    this.recorder=recorder;
}
    @Override
    public void execute() {
       
        selection.setBeginIndex(inv.getBeginIndex());
        selection.setEndIndex(inv.getEndIndex());
        recorder.save(this);
    }
  
   
    @Override
    public Memento getMemento() {
       return new SelectMemento(begin,end);
    }

    @Override
    public void setMemento(Memento memento) {
        SelectMemento selectMemento = (SelectMemento) memento;
        this.begin = selectMemento.getBeginIndex();  
        this.end = selectMemento.getEndIndex();     
    }
    
}
