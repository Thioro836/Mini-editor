package fr.istic.aco.editor.Test;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.RecorderImpl;
import fr.istic.aco.editor.ConcreteCommandd.ReplayCommand;
import fr.istic.aco.editor.ConcreteCommandd.StartCommand;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.Interface.Selection;


public class ConcreteCommandTest {
   private Engine engine;
    private Invoker invoker; // Instance d'Invoker
    private Selection selection;
    private Recorder recorder;
    private Command command;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        selection = engine.getSelection();
        recorder=new RecorderImpl();
        invoker = new Invoker(engine, selection,recorder);
    }

    private void todo() {
        fail("Unimplemented test");
    }

    @Test
    void startCommandTest(){
     

    }
}
