package fr.istic.aco.editor;

public class DeleteCommand implements Command{
    private Engine engine;
    private Selection selection;
    public DeleteCommand(Engine engine,Selection selection){
        this.engine=engine;
        this.selection=selection;
    }
    @Override
    public void execute() {
       engine.delete();
    }
    
}
