  package fr.istic.aco.editor.CommandOriginator;

  import fr.istic.aco.editor.Interface.CommandOriginator;
  import fr.istic.aco.editor.Interface.Engine;
  import fr.istic.aco.editor.Interface.Memento;
  import fr.istic.aco.editor.Interface.Selection;

  public class CopyCommand implements CommandOriginator {
  private Engine engine;
  private Selection selection;


  public CopyCommand(Engine engine, Selection selection){
      this.engine=engine; 
      this.selection=selection;
  }
      @Override
      public void execute() {
        engine.copySelectedText();
        
      }
      @Override
      public void setMemento(Memento memento) {
      
      }
      @Override
      public Memento getMemento() {
      return null;
      }

      
  }
