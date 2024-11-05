package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Selection;

public class PasteCommand implements CommandOriginator{
private Engine engine;
private  Selection selection;
public PasteCommand (Engine engine,Selection selection){
    this.engine = engine;
    this.selection=selection;
}
    @Override
    public void execute() {
        engine.pasteClipboard();
    }
   
    @Override
    public void setMemento(Memento memento) {
       
    }
    @Override
    public Memento getMemento() {
       return null;
    }
    
}
