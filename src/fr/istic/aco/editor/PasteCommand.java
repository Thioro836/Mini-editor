package fr.istic.aco.editor;

public class PasteCommand implements Command{
private Engine engine;
public PasteCommand (Engine engine){
    this.engine = engine;
}
    @Override
    public void execute() {
        engine.pasteClipboard();
    }
    
}
