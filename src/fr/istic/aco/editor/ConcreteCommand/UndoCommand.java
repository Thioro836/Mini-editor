package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;


public class UndoCommand implements Command{

     private UndoManager undoManager;

     public UndoCommand(UndoManager undoManager){
       
        this.undoManager=undoManager;
       
     }
    @Override
    public void execute() {
        undoManager.undo();
        
    }

    
}
