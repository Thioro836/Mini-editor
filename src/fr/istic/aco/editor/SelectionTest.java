package fr.istic.aco.editor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SelectionTest {
    private Selection selection;
    private Engine engine;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine=new EngineImpl();
        selection = engine.getSelection();

    }

    private void todo() {
        fail("Unimplemented test");
    }
    @Test
    void createSelection(){
        assertEquals(selection.getBeginIndex(), 0);
        assertEquals(selection.getEndIndex(), 0);
        assertEquals(selection.getBufferBeginIndex(), 0);
        assertEquals(selection.getBufferEndIndex(), 0);

    }
    @Test
    void testBufferEndIndex(){
        StringBuilder b = new StringBuilder("1234");
        Selection s =new SelectionImpl(b);
        assertEquals(s.getBufferEndIndex(), b.length());


    }
    @Test
    void testEndIndex(){
        int newEndIndex=2;
        StringBuilder b = new StringBuilder("1234");
        Selection s =new SelectionImpl(b);
        s.setEndIndex(newEndIndex);
        assertEquals(s.getEndIndex(),newEndIndex);
    }
    @Test
    void testBeginIndex(){
        int begin=2, end =3;
        StringBuilder b = new StringBuilder("1234");
        Selection s =new SelectionImpl(b);
        s.setBeginIndex(begin);
        s.setEndIndex(end);
        assertEquals(s.getBeginIndex(), begin);
        
    }
    @Test
    void testBeginIndexNegative(){
        StringBuilder b = new StringBuilder("1234");
        Selection s =new SelectionImpl(b);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            s.setBeginIndex(s.getBufferBeginIndex() - 1); // Déclenchement de l'exception attendu
        });
    }
    @Test
    void testBeginBiggerThanEnd(){
        StringBuilder b = new StringBuilder("1234");
        Selection s =new SelectionImpl(b);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            s.setBeginIndex(10); // Déclenchement de l'exception attendu
        });

    }
    @Test
    void testEndIndexLessThanBeginIndex() {
    StringBuilder b = new StringBuilder("1234");
    Selection s = new SelectionImpl(b);
    s.setBeginIndex(2);
    assertThrows(IndexOutOfBoundsException.class, () -> {
        s.setEndIndex(1); // Déclenchement de l'exception attendu car endIndex < beginIndex
    });
}

    
}
