package fr.istic.aco.editor;

public class SelectionCommand implements Command {
private Engine engine;
public SelectionCommand (Engine engine){
    this.engine=engine;
}
    @Override
    public void execute() {
        engine.getSelection();
    }
    
}
