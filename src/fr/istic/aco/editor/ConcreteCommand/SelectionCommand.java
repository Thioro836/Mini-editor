package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.Invoker;
import fr.istic.aco.editor.Selection;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;

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
    public void setMemento(Memento memento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMemento'");
    }
    @Override
    public Memento getMemento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMemento'");
    }
    
}
