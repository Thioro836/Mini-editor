package fr.istic.aco.editor.UI;

import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.RecorderImpl;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.Interface.Selection;

/**
 * The {@code Editor} class initializes the components of the text editor, such
 * as the
 * {@link Engine}, {@link Selection}, {@link Recorder}, and {@link Invoker},
 * and starts the textual interface for interacting with the editor.
 * <p>
 * It contains a {@link Main} class with a main method to launch the
 * application.
 * </p>
 */

public class Editor {
    /**
     * The {@code Main} class is the entry point of the application.
     * It initializes the necessary components and starts the
     * {@link TextualInterface}.
     */
    public class Main {

        /**
         * The main method that initializes the {@link Engine}, {@link Selection},
         * {@link Recorder},
         * and {@link Invoker} instances and starts the {@link TextualInterface}.
         * 
         * @param args command line arguments (not used in this implementation)
         */
        public static void main(String[] args) {

            Engine engine = new EngineImpl();
            Selection selection = engine.getSelection();
            Recorder recorder = new RecorderImpl();
            Invoker invoker = new Invoker(engine, selection, recorder);
            // Start the textual user interface for interacting with the editor
            TextualInterface ui = new TextualInterface(invoker, engine);
            ui.start();
        }
    }

}
