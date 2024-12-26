package fr.istic.aco.editor.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.RecorderImpl;
import fr.istic.aco.editor.CommandOriginator.InsertCommand;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.Interface.Selection;

public class ConcreteCommandTest {
    private Engine engine;
    private Invoker invoker; // Instance d'Invoker
    private Selection selection;
    private Recorder recorder;
    private CommandOriginator Ocommand;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        selection = engine.getSelection();
        recorder = new RecorderImpl();
        invoker = new Invoker(engine, selection, recorder);
    }

    @Test
    void startCommandTest() {
        Ocommand = new InsertCommand(engine, invoker, recorder);
        invoker.playCommandConcrete("start");
        System.out.println("Recorder size after start: " + recorder.getList());
        invoker.setTextToInsert("abcd");
        invoker.playCommand("insert");
        System.out.println("Recorder size after insert: " + recorder.getList());
        assertEquals(1, recorder.getList());

    }

    @Test
    void stopCommandTest() {
        Ocommand = new InsertCommand(engine, invoker, recorder);
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
        // commencer l'enregistrement
        Ocommand = new InsertCommand(engine, invoker, recorder);
        invoker.playCommandConcrete("start");
        // insérer du text
        invoker.setTextToInsert("abcd");
        invoker.playCommand("insert");
        assertEquals("abcd", invoker.getTextToInsert());

        // vérifier que les commandes ont été bien enregistré
        assertEquals(1, recorder.getList());
        invoker.playCommandConcrete("stop");
        // rejouer les commandes enregistrés
        invoker.playCommandConcrete("replay");
        assertEquals("abcd", invoker.getTextToInsert());

    }

    @Test
    @DisplayName("test du replay avec une copy dans le clipboard")
    void replayCommandCopyTest() {
        Ocommand = new InsertCommand(engine, invoker, recorder);
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
        assertEquals("b", engine.getClipboardContents());
    }
}
