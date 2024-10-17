package fr.istic.aco.editor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    private Engine engine; 
    private Invoker invoker; // Instance d'Invoker
    private Selection selection;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine= new EngineImpl();
        invoker =new Invoker(engine, selection);
    }

    private void todo() {
        fail("Unimplemented test");
    }
    @Test
    void insertCommandTest(){
        //in the beginning buffer is empty
        assertEquals("", engine.getBufferContents());

        //Define the text to insert before executing the insert command
        invoker.setTextToInsert("abcde");
        invoker.playCommand("insert");
        //Check that the text "abcde" has been inserted into the buffer
        assertEquals("abcde", invoker.getTextToInsert()); 
        assertEquals("abcde", engine.getBufferContents()); 
        //repeat the process   
        invoker.setTextToInsert("fgh");
        invoker.playCommand("insert");

        assertEquals("fgh", invoker.getTextToInsert());  
        assertEquals("abcdefgh", engine.getBufferContents());
        }
    @Test
    void copyCommand(){
        
    }
}
