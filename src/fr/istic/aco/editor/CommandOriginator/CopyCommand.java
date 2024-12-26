  package fr.istic.aco.editor.CommandOriginator;

  import fr.istic.aco.editor.ClassImpl.UndoManager;
  import fr.istic.aco.editor.Interface.CommandOriginator;
  import fr.istic.aco.editor.Interface.Engine;
  import fr.istic.aco.editor.Interface.Memento;
  import fr.istic.aco.editor.Interface.Selection;
  import fr.istic.aco.editor.Interface.Recorder;
  public class CopyCommand implements CommandOriginator {
  private Engine engine;
  private Selection selection;
  private Recorder recorder;
  private UndoManager undoManager;

  public CopyCommand(Engine engine, Selection selection, Recorder recorder,UndoManager undoManager){
      this.engine=engine; 
      this.selection=selection;
      this.recorder=recorder;
      this.undoManager=undoManager;
  }
      @Override
      public void execute() {
        engine.copySelectedText();
        undoManager.store();
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
