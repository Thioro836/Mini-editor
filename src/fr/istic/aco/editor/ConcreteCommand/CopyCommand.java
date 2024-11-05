package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.Selection;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;

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
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setMemento'");
    }
    @Override
    public Memento getMemento() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getMemento'");
    }

    
}
