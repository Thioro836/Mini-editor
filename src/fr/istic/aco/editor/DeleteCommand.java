package fr.istic.aco.editor;

public class DeleteCommand implements Command{
    private Engine engine;
    public DeleteCommand(Engine engine){
        this.engine=engine;
    }
    @Override
    public void execute() {
       engine.delete();
    }
    
}
