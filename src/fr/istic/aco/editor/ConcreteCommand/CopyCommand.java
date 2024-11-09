package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Selection;

public class CopyCommand implements Command {
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

    
}
