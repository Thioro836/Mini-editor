package fr.istic.aco.editor.UI;

import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.RecorderImpl;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.Interface.Selection;

/**
 * The {@code Editor} class initializes the components of the text editor, such
 * as the {@link Engine}, {@link Selection}, {@link Recorder}, and
 * {@link Invoker},
 * and starts the textual interface for interacting with the editor.
 * <p>
 * This class serves as the entry point for the application, setting up all
 * necessary components and starting the user interface for the text editor.
 */
public class Editor {
    /**
     * The {@code Main} class is the entry point of the application.
     * It initializes the necessary components and starts the
     * {@link TextualInterface}.
     *
     * @param args command line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        // Initialize the core components of the editor
        Engine engine = new EngineImpl();
        Selection selection = engine.getSelection();
        Recorder recorder = new RecorderImpl();
        Invoker invoker = new Invoker(engine, selection, recorder);
        // Start the textual user interface for interacting with the editor
        TextualInterface ui = new TextualInterface(invoker, engine);
        ui.start();
    }
}
