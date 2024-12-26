package fr.istic.aco.editor.UI;

import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.RecorderImpl;
import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.Interface.Selection;

/**
 * The {@code Editor} class is the entry point for the text editor application.
 * It initializes the necessary components of the text editor, including the
 * engine,
 * selection management, recorder, and undo manager. The editor relies on the
 * command pattern
 * to execute various actions such as text manipulation and undo/redo
 * operations.
 * 
 * This class sets up the system by creating instances of the engine, selection,
 * recorder,
 * and undo manager, and then it initializes the invoker and user interface. The
 * user interface
 * runs in a textual mode through the {@code TextualInterface} class.
 * 
 */
public class Editor {
    /**
     * The main method to start the text editor.
     * It initializes the engine, selection, recorder, undo manager, invoker, and
     * the user interface.
     * Then, it starts the user interface to interact with the editor.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        Selection selection = engine.getSelection();
        Recorder recorder = new RecorderImpl();
        UndoManager undoManager = new UndoManager(engine);
        Invoker invoker = new Invoker(engine, selection, recorder, undoManager);

        TextualInterface ui = new TextualInterface(invoker, engine, undoManager);
        ui.start();
    }
}
