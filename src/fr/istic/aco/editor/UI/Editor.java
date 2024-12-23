package fr.istic.aco.editor.UI;

import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.RecorderImpl;
import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.Interface.Selection;

public class Editor {
    public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        Selection selection = engine.getSelection();
        Recorder recorder = new RecorderImpl();
        UndoManager undoManager = new UndoManager(engine);
        Invoker invoker = new Invoker(engine, selection, recorder,undoManager);
        
        TextualInterface ui = new TextualInterface(invoker,engine,undoManager);
        ui.start();
    }
}


}
