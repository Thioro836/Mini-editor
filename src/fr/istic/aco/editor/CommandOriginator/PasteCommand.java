package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Selection;
import fr.istic.aco.editor.Interface.Recorder;
public class PasteCommand implements CommandOriginator{
private Engine engine;
private  Selection selection;
private Recorder recorder;
public PasteCommand (Engine engine,Selection selection,Recorder recorder){
    this.engine = engine;
    this.selection=selection;
    this.recorder=recorder;
}
    @Override
    public void execute() {
        engine.pasteClipboard();
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
