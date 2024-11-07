package fr.istic.aco.editor.ConcreteCommandd;

import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Recorder;

public class StopCommand implements Command{
 private Recorder recorder;
    public StopCommand(Recorder recorder){
        this.recorder=recorder;
    }
    @Override
    public void execute() {
       recorder.stop();
    }

}
