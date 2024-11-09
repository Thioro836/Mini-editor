package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.ClasseImpl.Invoker;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Selection;

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
