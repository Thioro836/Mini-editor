package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Selection;
import fr.istic.aco.editor.Memento.InsertMemento;
import fr.istic.aco.editor.Memento.SelectMemento;

public class SelectionCommand implements CommandOriginator {
private Selection selection;
private Invoker inv;
private int begin, end;
public SelectionCommand (Selection selection, Invoker inv ){
    this.selection=selection;
    this.inv=inv;
}
    @Override
    public void execute() {
       
        selection.setBeginIndex(inv.getBeginIndex());
        selection.setEndIndex(inv.getEndIndex());
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
