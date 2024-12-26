package fr.istic.aco.editor.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Memento.EditorMemento;

public class UndoManagerTest {
    private UndoManager undoManager;
    private Engine engine;

    @BeforeEach
    public void setUp() {
        engine = new EngineImpl();
        undoManager = new UndoManager(engine);
    }

    @Test
    public void testInitialState() {
        assertEquals("", engine.getBufferContents());
        assertEquals(0, undoManager.getPastStates().size());
        assertEquals(0, undoManager.getFutureStates().size());
    }

    @Test
    public void testStoreMultipleStates() {
        engine.insert("First");
        undoManager.store();
        engine.insert("Second");
        undoManager.store();
        engine.insert("Third");
        undoManager.store();

        assertEquals(3, undoManager.getPastStates().size());
        assertEquals("FirstSecondThird", engine.getBufferContents());
    }

    @Test
    public void testStoreDuplicateState() {
        engine.insert("Test");
        undoManager.store();
        undoManager.store(); // Tentative de stocker le même état

        assertEquals(1, undoManager.getPastStates().size());
    }

    @Test
    public void testUndoRedoWithSelection() {
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5);
        undoManager.store();

        engine.delete();
        undoManager.store();

        assertEquals(" World", engine.getBufferContents());
        undoManager.undo();
        assertEquals("Hello World", engine.getBufferContents());
        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(5, engine.getSelection().getEndIndex());
    }

    @Test
    public void testComplexUndoRedoSequence() {
        // Séquence: insert -> undo -> insert -> redo
        engine.insert("First");
        undoManager.store();
        engine.insert("Second");
        undoManager.store();

        undoManager.undo(); // Retour à "First"
        assertEquals("First", engine.getBufferContents());

        engine.insert("Third"); // Nouvelle branche
        undoManager.store();

        // Le redo ne devrait pas être possible car nous avons créé une nouvelle branche
        undoManager.redo();
        assertEquals("FirstThird", engine.getBufferContents());
    }

    @Test
    public void testMultipleUndoRedo() {
        engine.insert("1");
        undoManager.store();
        engine.insert("2");
        undoManager.store();
        engine.insert("3");
        undoManager.store();

        // Trois undo
        undoManager.undo();
        undoManager.undo();
        undoManager.undo();
        assertEquals("", engine.getBufferContents());

        // Trois redo
        undoManager.redo();
        undoManager.redo();
        undoManager.redo();
        assertEquals("123", engine.getBufferContents());
    }

    // Tests de cas limites
    @Test
    public void testUndoAfterClearingBuffer() {
        engine.insert("Text");
        undoManager.store();
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(4);
        engine.delete();
        undoManager.store();

        assertEquals("", engine.getBufferContents());
        undoManager.undo();
        assertEquals("Text", engine.getBufferContents());
    }

    @Test
    public void testClipboardState() {

        engine.insert("Copy this text");
        undoManager.store();

        // "Copy"
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(4);
        engine.copySelectedText();
        undoManager.store();
        assertEquals("Copy", engine.getClipboardContents());

        // 3. Couper "this"
        engine.getSelection().setBeginIndex(5);
        engine.getSelection().setEndIndex(9);
        engine.cutSelectedText();
        undoManager.store();
        assertEquals("this", engine.getClipboardContents());

        // 4. Faire un undo
        undoManager.undo();
        assertEquals("Copy this text", engine.getBufferContents());
        assertEquals("Copy", engine.getClipboardContents());
    }

    // Tests de robustesse
    @Test
    public void testUndoRedoWithEmptyStates() {
        // Tester undo/redo quand il n'y a pas d'états
        undoManager.undo();
        undoManager.redo();
        assertEquals("", engine.getBufferContents());
    }

    @Test
    public void testInsertEmptyString() {
        engine.insert("");
        undoManager.store();
        engine.insert("Text");
        undoManager.store();

        undoManager.undo();
        assertEquals("", engine.getBufferContents());
    }

    @Test
    public void testUndoRedoWithMaxOperations() {
        for (int i = 0; i < 100; i++) {
            engine.insert(String.valueOf(i));
            undoManager.store();
        }

        // Vérifions que tous les undo fonctionnent
        for (int i = 0; i < 100; i++) {
            undoManager.undo();
        }

        assertEquals("", engine.getBufferContents());
    }
}