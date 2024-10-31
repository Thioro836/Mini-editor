package fr.istic.aco.editor;

public class SelectionCommand implements CommandOriginator {
private Selection selection;
private Invoker inv;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMemento'");
    }
    @Override
    public void setMemento(Memento memento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMemento'");
    }
    
}
