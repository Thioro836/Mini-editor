package fr.istic.aco.editor.Test;

import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.RecorderImpl;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.Interface.Selection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for the CommandOriginator pattern in the text
 * editor system.
 * It tests various commands such as insert, selection, copy, cut, paste, and
 * delete
 * to ensure the proper behavior and interactions between the components.
 */
public class CommandOriginatorTest {
    private Engine engine;
    private Invoker invoker;
    private Selection selection;
    private Recorder recorder;

    /**
     * Sets up the necessary components before each test.
     * Initializes the Engine, Selection, Recorder, and Invoker.
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        selection = engine.getSelection();
        recorder = new RecorderImpl();
        invoker = new Invoker(engine, selection, recorder);
    }

    /**
     * Tests the InsertCommand behavior in the editor.
     * Verifies that text is inserted into the buffer correctly.
     */
    @Test
    void insertCommandTest() {
        // in the beginning buffer is empty
        assertEquals("", engine.getBufferContents());

        // Define the text to insert before executing the insert command
        invoker.setTextToInsert("abcde");
        invoker.playCommand("insert");
        // Check that the text "abcde" has been inserted into the buffer
        assertEquals("abcde", invoker.getTextToInsert());
        assertEquals("abcde", engine.getBufferContents());
        // repeat the process
        invoker.setTextToInsert("fgh");
        invoker.playCommand("insert");

        assertEquals("fgh", invoker.getTextToInsert());
        assertEquals("abcdefgh", engine.getBufferContents());
    }

    /**
     * Tests the SelectionCommand behavior in the editor.
     * Verifies that the selection indices are correctly set.
     */
    @Test
    void SelectionCommandTest() {
        // Define the text to insert before executing the selection command
        invoker.setTextToInsert("abcde");
        invoker.playCommand("insert");

        // Setting the selection indices
        invoker.setBeginIndex(2); // Index 2
        invoker.setEndIndex(4); // Index 4

        invoker.playCommand("selection");

        assertEquals(2, selection.getBeginIndex());
        assertEquals(4, selection.getEndIndex());
    }

    /**
     * Tests the CopyCommand behavior in the editor.
     * Verifies that the selected text is copied to the clipboard.
     */
    @Test
    void CopyCommandTest() {
        invoker.setTextToInsert("abcd");
        invoker.playCommand("insert");
        // Avant d'exécuter la commande de copie
        invoker.setBeginIndex(0);
        invoker.setEndIndex(2); // Sélectionne tout le texte disponible
        invoker.playCommand("selection");

        invoker.playCommand("copy");

        assertEquals("ab", engine.getClipboardContents());
        assertEquals("abcd", engine.getBufferContents());

    }

    /**
     * Tests the CutCommand behavior in the editor.
     * Verifies that the selected text is cut and placed on the clipboard.
     */
    @Test
    void cutCommandTest() {
        invoker.setTextToInsert("abcdefg");
        invoker.playCommand("insert");

        invoker.setBeginIndex(2);
        invoker.setEndIndex(5);
        invoker.playCommand("selection");

        invoker.playCommand("cut");

        assertEquals("cde", engine.getClipboardContents());
        assertEquals("abfg", engine.getBufferContents());

    }

    /**
     * Tests the PasteCommand behavior in the editor.
     * Verifies that the text from the clipboard is correctly pasted at the selected
     * position.
     */
    @Test
    void pasteCommandTest() {

        invoker.setTextToInsert("abcdef");
        invoker.playCommand("insert");

        invoker.setBeginIndex(2);
        invoker.setEndIndex(4);
        invoker.playCommand("selection");

        invoker.playCommand("copy");

        invoker.setBeginIndex(0);
        invoker.setEndIndex(2);
        invoker.playCommand("selection");

        invoker.playCommand("delete");
        invoker.playCommand("paste");
        assertEquals("cd", engine.getClipboardContents());
        assertEquals("cdcdef", engine.getBufferContents());
        // assertEquals(invoker.getBeginIndex(), 2);
        assertEquals(invoker.getEndIndex(), 2);

    }

    /**
     * Tests the DeleteCommand behavior in the editor.
     * Verifies that the selected text is deleted from the buffer.
     */
    @Test
    void deleteCommandTest() {
        invoker.setTextToInsert("abcde");
        invoker.playCommand("insert");

        invoker.setBeginIndex(2);
        invoker.setEndIndex(4);
        invoker.playCommand("selection");

        invoker.playCommand("delete");
        assertEquals("abe", engine.getBufferContents());
        assertEquals(2, invoker.getBeginIndex());

    }
}
