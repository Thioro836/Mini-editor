package fr.istic.aco.editor.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.ClasseImpl.EngineImpl;
import fr.istic.aco.editor.ClasseImpl.Invoker;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Selection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for command implementations in the text editor.
 */

public class CommandTest {
    private Engine engine;
    private Invoker invoker; // Instance d'Invoker
    private Selection selection;

    /**
     * Set up the test environment before each test case.
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        selection = engine.getSelection();
        invoker = new Invoker(engine, selection);
    }

    /**
     * Test the InsertCommand functionality.
     */
    @Test
    @DisplayName("Test Insert Command")
    void insertCommandTest() {
        // Initially, the buffer is empty
        assertEquals("", engine.getBufferContents());

        // Insert text into the buffer
        invoker.setTextToInsert("abcde");
        invoker.playCommand("insert");
        assertEquals("abcde", invoker.getTextToInsert());
        assertEquals("abcde", engine.getBufferContents());

        // Insert more text and verify the result
        invoker.setTextToInsert("fgh");
        invoker.playCommand("insert");

        assertEquals("fgh", invoker.getTextToInsert());
        assertEquals("abcdefgh", engine.getBufferContents());
    }

    /**
     * Test the SelectionCommand functionality.
     */
    @Test
    @DisplayName("Test Selection Command")
    void SelectionCommandTest() {

        invoker.setTextToInsert("abcde");
        invoker.playCommand("insert");
        invoker.setBeginIndex(2);
        invoker.setEndIndex(4);

        invoker.playCommand("selection");

        assertEquals(2, selection.getBeginIndex());
        assertEquals(4, selection.getEndIndex());
    }

    /**
     * Test the CopyCommand functionality.
     */
    @Test
    @DisplayName("Test Copy Command")
    void CopyCommandTest() {
        invoker.setTextToInsert("abcd");
        invoker.playCommand("insert");

        invoker.setBeginIndex(0);
        invoker.setEndIndex(2);
        invoker.playCommand("selection");

        invoker.playCommand("copy");

        assertEquals("ab", engine.getClipboardContents());
        assertEquals("abcd", engine.getBufferContents());

    }

    /**
     * Test the CutCommand functionality.
     */
    @Test
    @DisplayName("Test Cut Command")
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
     * Test the PasteCommand functionality.
     */
    @Test
    @DisplayName("Test Paste Command")
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

        assertEquals(invoker.getEndIndex(), 2);

    }

    /**
     * Test the DeleteCommand functionality.
     */
    @Test
    @DisplayName("Test Delete Command")
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
