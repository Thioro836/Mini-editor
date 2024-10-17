package fr.istic.aco.editor;

public class SelectionCommand implements Command {
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
    
}
