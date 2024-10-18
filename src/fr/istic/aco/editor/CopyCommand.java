package fr.istic.aco.editor;

public class CopyCommand implements Command {
private Engine engine;
private Selection selection;

public CopyCommand(Engine engine, Selection selection){
    this.engine=engine; 
    this.selection=selection;
}
    @Override
    public void execute() {
      System.out.println("Executing Copy Command:");
      System.out.println("Begin Index: " + selection.getBeginIndex());
      System.out.println("End Index: " + selection.getEndIndex());
      engine.copySelectedText();
       
    }

    
}
