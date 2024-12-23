package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.ClassImpl.UndoManager;

public class RedoCommand implements Command {
 
    private UndoManager undoManager;

    public RedoCommand( UndoManager undoManager) {
        this.undoManager = undoManager;

    }

    @Override
    public void execute() {
        undoManager.redo();


    }

}
