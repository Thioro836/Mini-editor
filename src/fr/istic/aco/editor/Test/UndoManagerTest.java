package fr.istic.aco.editor.Test;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.Engine;

public class UndoManagerTest {
      private UndoManager undoManager;
    private Engine engine;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        engine = new EngineImpl();
        undoManager = new UndoManager(engine);
    }

    // Cas de test pour vérifier si un état est bien stocké
    @Test
    public void testStoreState() {
        //enregistre l'état initial du buffer
        undoManager.store();
        //insertion de text
        engine.insert("abcd");
        undoManager.store();

        engine.insert("123");
        undoManager.store();
       

        // Vérifier que l'état a été ajouté à la liste des états passés
        assertEquals(3, undoManager.getPastStates().size());
        assertEquals("abcd", undoManager.getPastStates().get(1).getBufferContent());
        assertEquals("abcd123", undoManager.getPastStates().get(2).getBufferContent());
       
    }
    @Test
    public void testStoreAndUndo() {
        undoManager.store();
        engine.insert("abcd");
        undoManager.store(); // Sauvegarder "abcd"

        engine.insert("123");
        undoManager.store(); // Sauvegarder "abcd123"
        assertEquals(3, undoManager.getPastStates().size());
        undoManager.undo(); // Revenir à "abcd"
        System.out.println("Buffer content after undo: " + engine.getBufferContents());
        
        assertEquals(2, undoManager.getPastStates().size());
        //assertEquals("abcd", engine.getBufferContents());
    }


    // Cas de test pour vérifier que undo revient bien à l'état précédent
    @Test
    public void testUndoState() {
        // Sauvegarder l'état initial
        undoManager.store();

        // Changer l'état de l'éditeur
        engine.insert("abcd");
        undoManager.store();
        System.out.println("After inserting 'abcd': " + engine.getBufferContents());

        engine.insert("123");
        undoManager.store();
        System.out.println("After inserting '123': " + engine.getBufferContents());
        // Effectuer un undo
        undoManager.undo();

        // Vérifier que l'état est revenu à l'état précédent
        assertEquals("abcd", engine.getBufferContents());
    }

    // Cas de test pour vérifier que redo fonctionne après un undo
    @Test
    public void testRedoState() {
        // Sauvegarder l'état initial
        undoManager.store();

        // Changer l'état de l'éditeur
        engine.insert("abcd");
        undoManager.store();

        // Effectuer un undo
        undoManager.undo();
        assertEquals("", engine.getBufferContents());
        // Effectuer un redo
        undoManager.redo();

        // Vérifier que l'état est revenu à "New State"
        assertEquals("abcd", engine.getBufferContents());
    }

    // Cas de test pour vérifier le comportement lorsque l'historique d'annulation est vide
    @Test
    public void testUndoWithoutStates() {
        engine.insert("abcd");
        // Effectuer un undo sans avoir stocké d'état
        undoManager.undo();

        // Vérifier que l'état n'a pas changé
        assertEquals("abcd", engine.getBufferContents());
    }

    // Cas de test pour vérifier le comportement lorsque l'historique de reprise est vide
    @Test
    public void testRedoWithoutStates() {
        engine.insert("abcd");
        // Effectuer un redo sans avoir stocké d'état
        undoManager.redo();

        // Vérifier que l'état n'a pas changé
        assertEquals("abcd", engine.getBufferContents());
    }
}
