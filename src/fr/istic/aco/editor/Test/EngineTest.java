package fr.istic.aco.editor.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.ClasseImpl.EngineImpl;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Selection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for operation implementations in the text editor.
 */
class EngineTest {

    private Engine engine;
    private Selection selection;

    /**
     * Set up the test environment before each test case.
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        selection = engine.getSelection();
    }

    /**
     * Test case to verify that the buffer is empty after the engine is initialized.
     */
    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
        assertEquals("", engine.getBufferContents());
    }

    /**
     * Test case to verify that the text can be inserted correctly into the buffer.
     */
    @Test
    void getBufferContents() {
        engine.insert("fatou");
        assertEquals("fatou", engine.getBufferContents(), "Failure of insert");
        assertEquals(5, selection.getBufferEndIndex());
    }

    /**
     * Test case to verify that the clipboard contents are correctly set after
     * cutting text.
     */
    @Test
    void getClipboardContents() {
        engine.insert("abcdef");
        selection.setBeginIndex(0);
        selection.setEndIndex(2);
        engine.cutSelectedText();
        assertEquals("ab", engine.getClipboardContents());
        assertEquals("cdef", engine.getBufferContents());
    }

    /**
     * Test case to verify that the selected text is correctly cut and placed into
     * the clipboard.
     */
    @Test
    void cutSelectedText() {
        engine.insert("abcdef");
        selection.setBeginIndex(0);
        selection.setEndIndex(2);
        engine.cutSelectedText();
        assertEquals("ab", engine.getClipboardContents(), "clipboard should contain 'ab'");
        assertEquals("cdef", engine.getBufferContents(), "buffer should changed  after cut");
        assertEquals(0, selection.getBeginIndex());
        assertEquals(0, selection.getEndIndex());
    }

    /**
     * Test case to verify that the selected text is copied to the clipboard.
     */

    @Test
    void copySelectedText() {
        engine.insert("abcdef");
        selection.setBeginIndex(0);
        selection.setEndIndex(2);
        engine.copySelectedText();
        assertEquals("ab", engine.getClipboardContents(), "clipboard should contain 'ab'");
        assertEquals(0, selection.getBeginIndex());
        assertEquals(2, selection.getEndIndex());
        assertEquals("abcdef", engine.getBufferContents(), "buffer should remain unchanged after copy");

    }

    /**
     * Test case to verify that text can be correctly inserted into the buffer.
     */
    @Test
    void insert() {
        engine.insert("abcdef");
        assertEquals("abcdef", engine.getBufferContents(), "Buffer should content 'abcdef' after insert ");
        assertEquals(6, selection.getBeginIndex());
        assertEquals(6, selection.getEndIndex());

    }

    /**
     * Test case to verify that text from the clipboard can be pasted into the
     * buffer at a specified position.
     */
    @Test
    void pasteClipboard() {
        engine.insert("abcd");
        selection.setBeginIndex(0);
        selection.setEndIndex(2);
        engine.copySelectedText();

        selection.setBeginIndex(3);
        selection.setEndIndex(4);
        engine.delete();
        engine.pasteClipboard();
        assertEquals("ab", engine.getClipboardContents());
        assertEquals("abcab", engine.getBufferContents(), "Buffer should content 'abcab' after paste");
        assertEquals(5, selection.getBeginIndex());
        assertEquals(5, selection.getEndIndex());
    }

    /**
     * Test case to verify that text can be deleted from the buffer at the selected
     * indices.
     */
    @Test
    void delete() {
        engine.insert("abcdef");
        selection.setBeginIndex(3);
        selection.setEndIndex(4);
        engine.delete();
        assertEquals("abcef", engine.getBufferContents(), "Buffer should content 'adef' after delete");
        assertEquals(3, selection.getBeginIndex());
        assertEquals(3, selection.getEndIndex());
    }

    /**
     * Test case to ensure that setting a negative begin index throws an
     * {@link IndexOutOfBoundsException}.
     */
    @Test
    void testSetBeginIndexNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            selection.setBeginIndex(-1);
        });
    }

    /**
     * Test case to ensure that attempting to insert null into the engine throws a
     * {@link NullPointerException}.
     */
    @Test
    void testInsertNull() {
        assertThrows(NullPointerException.class, () -> engine.insert(null),
                "L'insertion d'une valeur null devrait lancer une exception.");
    }
}
