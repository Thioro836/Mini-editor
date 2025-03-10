package fr.istic.aco.editor.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.RecorderImpl;
import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.CommandOriginator.InsertCommand;
import fr.istic.aco.editor.CommandOriginator.SelectionCommand;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.Interface.Selection;

public class RecorderTest {
    private Recorder recorder;
    private Engine engine;
    private Invoker invoker;
    private Selection selection;
    private UndoManager undoManager;
    private CommandOriginator cOriginator;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        recorder = new RecorderImpl();
        engine = new EngineImpl();
        selection = engine.getSelection();
        undoManager = new UndoManager(engine);
        invoker = new Invoker(engine, selection, recorder, undoManager);
    }

    @Test
    void start() {
        cOriginator = new InsertCommand(engine, invoker, recorder, undoManager);
        recorder.start();
        assertEquals("", engine.getBufferContents());
        engine.insert("abcde");
        recorder.save(cOriginator);
        assertEquals("abcde", engine.getBufferContents());
        assertTrue(recorder.getList() > 0);

    }

    @Test
    void stop() {
        // créer une instance de originatorcommand
        cOriginator = new SelectionCommand(selection, invoker, recorder, undoManager);
        // commencer un enregistrement
        recorder.start();
        // insérer du texte
        engine.insert("abcde");
        // faire une sélection
        selection.setBeginIndex(1);
        selection.setEndIndex(2);
        // copier le texte
        engine.copySelectedText();
        // sauvegarder l'enregistrement
        recorder.save(cOriginator);
        // verifier le contenu du clipboard
        assertEquals("b", engine.getClipboardContents());
        // verifier que le recorder a bien ajouter la commande dans la liste
        assertEquals(1, recorder.getList());
        // arreter l'enregistrement
        recorder.stop();
        // faire un save de nouveau pour s'assurer que l'enregistrement ne sera pas pris
        // en compte
        recorder.save(cOriginator);
        assertEquals(1, recorder.getList());

    }

    @Test
    void save() {
        cOriginator = new InsertCommand(engine, invoker, recorder, undoManager);
        recorder.start();
        engine.insert("abcde");
        recorder.save(cOriginator);
        assertEquals("abcde", engine.getBufferContents());
        engine.insert("hello");
        recorder.save(cOriginator);
        recorder.stop();
        assertEquals("abcdehello", engine.getBufferContents());
        assertEquals(2, recorder.getList());

    }

    @Test
    void replay() {
        cOriginator = new InsertCommand(engine, invoker, recorder, undoManager);
        recorder.start();
        invoker.setTextToInsert("hello");
        invoker.playCommand("insert");
        assertEquals("hello", engine.getBufferContents());
        recorder.stop();
        invoker.playCommandConcrete("replay");
        assertEquals("hellohello", engine.getBufferContents());

    }

    @Test
    @DisplayName("tester le cas où on fait un replay sans faire de save")
    void replayWithoutSave() {
        recorder.start();
        recorder.stop();

        recorder.replay();
        assertEquals("", engine.getBufferContents());
    }

}
