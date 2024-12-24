package fr.istic.aco.editor.Test;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Memento.EditorMemento;

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
        System.out.println("Initial buffer: " + engine.getBufferContents());
        assertEquals("", engine.getBufferContents());
        
        engine.insert("abcd");  
        undoManager.store();    
        System.out.println("After first insert: " + engine.getBufferContents());
        System.out.println("Past states size: " + undoManager.getPastStates().size());
        assertEquals("abcd", engine.getBufferContents());
        assertEquals(1, undoManager.getPastStates().size());
        
        engine.insert("123");  
        undoManager.store();     
        System.out.println("After second insert: " + engine.getBufferContents());
        System.out.println("Past states size: " + undoManager.getPastStates().size());
        assertEquals("abcd123", engine.getBufferContents());
        assertEquals(2, undoManager.getPastStates().size());
        
        // Avant undo
        System.out.println("\nBefore undo:");
        System.out.println("Past states: ");
        for(EditorMemento m : undoManager.getPastStates()) {
            System.out.println(" - " + m.getBufferContent());
        }
        
        undoManager.undo();
        
        // Après undo
        System.out.println("\nAfter undo:");
        System.out.println("Buffer content: " + engine.getBufferContents());
        System.out.println("Past states size: " + undoManager.getPastStates().size());
        System.out.println("Future states size: " + undoManager.getFuturStates().size());
        
        assertEquals("abcd", engine.getBufferContents());
        assertEquals(1, undoManager.getPastStates().size());
        assertEquals(1, undoManager.getFuturStates().size());
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
