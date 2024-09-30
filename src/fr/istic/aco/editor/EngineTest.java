package fr.istic.aco.editor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    private Engine engine;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
    }

    private void todo() {
        fail("Unimplemented test");
    }
    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
        assertEquals("",engine.getBufferContents());
    }

    @Test
    void getBufferContents() {
        setUp();
        engine.insert("fatou");
        Selection selection = engine.getSelection();
        assertEquals("fatou", engine.getBufferContents(),"Failure of insert");
        assertEquals(5,selection.getBufferEndIndex());
    }

    @Test
    void getClipboardContents() {
        Selection selection = engine.getSelection();
        engine.insert("abcdef");
        selection.setBeginIndex(0);
        selection.setEndIndex(2);
        engine.copySelectedText();
        assertEquals("ab", engine.getClipboardContents());
        assertEquals("abcdef", engine.getBufferContents());
    }

    @Test
    void cutSelectedText() {
        Selection selection = engine.getSelection();
        engine.insert("abcdef");
        selection.setBeginIndex(0);
        selection.setEndIndex(2);
        engine.cutSelectedText();
        assertEquals("ab", engine.getClipboardContents(),"clipboard should contain 'ab'");
        assertEquals("cdef", engine.getBufferContents(),"buffer should changed  after cut");   
        assertEquals(0, selection.getBeginIndex());
        assertEquals(0, selection.getEndIndex());
    }

    @Test
    void copySelectedText() {
        Selection selection = engine.getSelection();
        engine.insert("abcdef");
        selection.setBeginIndex(0);
        selection.setEndIndex(2);
        engine.copySelectedText();
        assertEquals("ab", engine.getClipboardContents(),"clipboard should contain 'ab'");
        assertEquals(0, selection.getBeginIndex());
        assertEquals(2, selection.getEndIndex());
        assertEquals("abcdef", engine.getBufferContents(),"buffer should remain unchanged after copy");
      
    }

    @Test
    void pasteClipboard() {
        Selection selection = engine.getSelection();
        engine.insert("abcdef");
        selection.setBeginIndex(1);
        selection.setEndIndex(3);
        engine.copySelectedText();
        selection.setBeginIndex(5);
        selection.setEndIndex(6);
        engine.pasteClipboard();
        assertEquals("abcdefbc", engine.getBufferContents(),"buffer should content 'abcdefbc' after paste");
        assertEquals(8, selection.getBeginIndex());
        assertEquals(8,selection.getEndIndex());
    }
    @Test
    void delete(){
        Selection selection = engine.getSelection();
        engine.insert("abcdef");
        selection.setBeginIndex(1);
        selection.setEndIndex(3);
        engine.delete();
        assertEquals("adef", engine.getBufferContents(),"Buffer should content 'adef' after delete");
        assertEquals(2, selection.getBeginIndex());
        assertEquals(2, selection.getEndIndex());
    }
    @Test
    void insert(String s){
        Selection selection = engine.getSelection();
        engine.insert("abcdef"); 
        selection.setBeginIndex(0);
        selection.setEndIndex(3);
        engine.cutSelectedText();
        assertEquals("sdef", engine.getBufferContents(),"Buffer should content 'sdef' after insert ");
        assertEquals(1, selection.getBeginIndex());
        assertEquals(1, selection.getEndIndex());
        assertEquals(4, selection.getBufferEndIndex());



    }
}
