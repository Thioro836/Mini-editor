package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;


public class UndoCommand implements Command{
     private UndoManager undoManager;
     private Engine engine;
     public UndoCommand(UndoManager undoManager){
        this.undoManager=undoManager;
        //this.engine=engine;
     }
    @Override
    public void execute() {
        undoManager.undo();
        
    }

    
}
