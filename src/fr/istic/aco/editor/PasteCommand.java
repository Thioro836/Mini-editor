package fr.istic.aco.editor;

public class PasteCommand implements Command{
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
    
}
