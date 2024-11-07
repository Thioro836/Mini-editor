package fr.istic.aco.editor.ConcreteCommandd;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Recorder;

public class StartCommand  implements Command{

    private Recorder recorder;
    public StartCommand(Recorder recorder){
        this.recorder=recorder;
    }
    @Override
    public void execute() {
       recorder.start();
    }



}
