package fr.istic.aco.editor.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.RecorderImpl;
import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.CommandOriginator.InsertCommand;
import fr.istic.aco.editor.CommandOriginator.SelectionCommand;
import fr.istic.aco.editor.ConcreteCommand.ReplayCommand;
import fr.istic.aco.editor.ConcreteCommand.StartCommand;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.Interface.Selection;

public class ConcreteCommandTest {
    private Engine engine;
    private Invoker invoker; // Instance d'Invoker
    private Selection selection;
    private Recorder recorder;
    private UndoManager undoManager;
    private CommandOriginator Ocommand;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        selection = engine.getSelection();
        recorder = new RecorderImpl();
        undoManager = new UndoManager(engine);
        invoker = new Invoker(engine, selection, recorder, undoManager);
    }

    @Test
    void startCommandTest() {
        Ocommand = new InsertCommand(engine, invoker, recorder, undoManager);
        invoker.playCommandConcrete("start");
        invoker.setTextToInsert("abcd");
        invoker.playCommand("insert");
        assertEquals("abcd", invoker.getTextToInsert());
        assertTrue(recorder.getList() > 0);
        assertEquals(1, recorder.getList());

    }

    @Test
    void stopCommandTest() {
        Ocommand = new InsertCommand(engine, invoker, recorder, undoManager);
        invoker.playCommandConcrete("start");
        invoker.setTextToInsert("abcd");
        invoker.playCommand("insert");
        assertEquals("abcd", invoker.getTextToInsert());
        assertEquals(1, recorder.getList());
        invoker.playCommandConcrete("stop");
        invoker.playCommand("insert");
        assertEquals(1, recorder.getList());

    }

    @Test
    @DisplayName("tester le replay avec une insertion")
    void replayCommandTest() {
        // Démarrer l'enregistrement
        recorder.start();
        invoker.setTextToInsert("hello");
        Ocommand = new InsertCommand(engine, invoker, recorder, undoManager);
        
        // Exécuter et vérifier l'insertion initiale
        Ocommand.execute();
        assertEquals("hello", engine.getBufferContents());
        
        // Arrêter l'enregistrement
        recorder.stop();
        
        // Vérifier que la commande a bien été enregistrée
        assertEquals(1, recorder.getList());
        
        // Replay et vérification
        recorder.replay();
        assertEquals("hellohello", engine.getBufferContents());

    }

    @Test
    @DisplayName("test du replay avec une copy dans le clipboard")
    void replayCommandCopyTest() {
        Ocommand = new InsertCommand(engine, invoker, recorder, undoManager);
        invoker.playCommandConcrete("start");

        // insérer du text
        invoker.setTextToInsert("abcd");
        invoker.playCommand("insert");
        assertEquals("abcd", invoker.getTextToInsert());

        // selectionner le text et copier dans le clipboard
        invoker.setBeginIndex(1);
        invoker.setEndIndex(2);
        invoker.playCommand("selection");
        invoker.playCommand("copy");
        assertEquals("b", engine.getClipboardContents());

        // vérifier que les commandes ont été bien enregistré
        assertEquals(3, recorder.getList());
        invoker.playCommandConcrete("stop");

        // rejouer les commandes enregistrés
        invoker.playCommandConcrete("replay");
        assertEquals("abcd", invoker.getTextToInsert());
        // assertEquals("b", engine.getClipboardContents());
    }

    @Test
    void undoTest() {
        Ocommand = new InsertCommand(engine, invoker, recorder, undoManager);
        invoker.setTextToInsert("abcd");
        invoker.playCommand("insert");
        assertEquals("abcd", invoker.getTextToInsert());
        invoker.setTextToInsert("123");
        invoker.playCommand("insert");

        assertEquals("123", invoker.getTextToInsert());
        invoker.playCommandConcrete("undo");

        assertEquals("abcd", engine.getBufferContents());

    }

    @Test
    void redoTest() {
        Ocommand = new InsertCommand(engine, invoker, recorder, undoManager);
        invoker.setTextToInsert("abcd");
        invoker.playCommand("insert");
        assertEquals("abcd", invoker.getTextToInsert());
        invoker.setTextToInsert("123");
        invoker.playCommand("insert");

        assertEquals("123", invoker.getTextToInsert());
        invoker.playCommandConcrete("undo");
        assertEquals("abcd", engine.getBufferContents());

        invoker.playCommandConcrete("redo");
        assertEquals("abcd123", engine.getBufferContents());

    }
}
