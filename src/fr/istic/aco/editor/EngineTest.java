package fr.istic.aco.editor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    private Engine engine;
    private Selection selection;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
         selection = engine.getSelection();
    }

    private void todo() {
        fail("Unimplemented test");
    }
    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
        assertEquals("",engine.getBufferContents());
    }

    @Test
    void getBufferContents() {
        engine.insert("fatou");
        assertEquals("fatou", engine.getBufferContents(),"Failure of insert");
        assertEquals(5,selection.getBufferEndIndex());
    }

    @Test
    void getClipboardContents() {
        engine.insert("abcdef");
        selection.setBeginIndex(0);
        selection.setEndIndex(2);
        engine.copySelectedText();
        assertEquals("ab", engine.getClipboardContents());
        assertEquals("abcdef", engine.getBufferContents());
    }

    @Test
    void cutSelectedText() {
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
      void insert(){
        engine.insert("abcdef"); 
        assertEquals("abcdef", engine.getBufferContents(),"Buffer should content 'sdef' after insert ");
        assertEquals(6, selection.getBeginIndex());
        assertEquals(6, selection.getEndIndex());
       
    }
    @Test
    void pasteClipboard() {
        engine.insert("bcdefg");
        selection.setBeginIndex(0);
        selection.setEndIndex(2);
        engine.copySelectedText();
        // Collage à la position 5
        selection.setBeginIndex(5);
        selection.setEndIndex(5);
        engine.pasteClipboard();
         assertEquals("bcdefbcg", engine.getBufferContents(), "Le buffer devrait contenir 'bcdefbcg' après le collage");
        assertEquals(7, selection.getBeginIndex());
        assertEquals(7, selection.getEndIndex());
    }
    
    @Test
    void delete(){
        engine.insert("abcdef");
        selection.setBeginIndex(3);
        selection.setEndIndex(4);
        engine.delete();
        assertEquals("abcef", engine.getBufferContents(),"Buffer should content 'adef' after delete");
        assertEquals(3, selection.getBeginIndex());
        assertEquals(3, selection.getEndIndex()); 
    }
    @Test
    void testSetBeginIndexNegative() {
    assertThrows(IndexOutOfBoundsException.class, () -> {
        selection.setBeginIndex(-1); // Tenter de définir un index négatif
    });
}

@Test
    void testInsertNull() {
        assertThrows(NullPointerException.class, () -> engine.insert(null), "L'insertion d'une valeur null devrait lancer une exception.");
    }
}
