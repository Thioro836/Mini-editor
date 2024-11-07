package fr.istic.aco.editor.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.RecorderImpl;
import fr.istic.aco.editor.CommandOriginator.CopyCommand;
import fr.istic.aco.editor.CommandOriginator.InsertCommand;
import fr.istic.aco.editor.CommandOriginator.SelectionCommand;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.Interface.Selection;

public class RecorderTest { 
    private Recorder recorder;
    private Engine engine;
    private Invoker invoker;
    private Selection selection;
    private CommandOriginator cOriginator;
     @org.junit.jupiter.api.BeforeEach
    void setUp() {
        recorder = new RecorderImpl();
       engine=new EngineImpl();
       selection=engine.getSelection();
       invoker = new Invoker(engine, selection,recorder);
    }

    private void todo() {
        fail("Unimplemented test");
    }
    @Test
    void start(){ 
        cOriginator=new SelectionCommand(selection,invoker, recorder); 
        recorder.start();
        recorder.save(cOriginator);
        assertTrue(recorder.getList()>0) ;
       

    }
    @Test
    void stop(){
        cOriginator=new SelectionCommand(selection,invoker, recorder); 
        recorder.start();
        recorder.save(cOriginator);
        assertEquals(1, recorder.getList());
        recorder.stop();
        recorder.save(cOriginator);
        assertEquals(1, recorder.getList());
        
    }
    @Test
    void save(){
       cOriginator=new InsertCommand(engine,invoker, recorder);
       recorder.start();    
       recorder.save(cOriginator);
       recorder.save(cOriginator);
       recorder.stop();
      assertEquals(2, recorder.getList());
    }
    @Test 
    void replay(){
        cOriginator=new InsertCommand(engine,invoker, recorder);
        invoker.setTextToInsert("hello");
        invoker.playCommand("insert");
       
        recorder.start();
        recorder.save(cOriginator);
        recorder.stop();    
        recorder.replay();
        assertEquals("hello", invoker.getTextToInsert());
    }
}
