package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Recorder;

public class ReplayCommand implements Command{
    private Recorder recorder;

    public ReplayCommand (Recorder recorder){
        this.recorder=recorder;
    }
    @Override
    public void execute() {
        recorder.replay();
       
    }

}
