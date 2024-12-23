package fr.istic.aco.editor.ConcreteCommandd;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Recorder;
/**
 * The {@code StartCommand} class implements the {@link Command} interface
 * and is responsible for starting the recording process in the {@link Recorder}.
 * This command triggers the recorder to begin recording actions.
 */
public class StartCommand  implements Command{
/**
   * Constructs a {@code StartCommand} with the specified recorder.
   * 
   * @param recorder the recorder that will start recording actions
   */
    private Recorder recorder;
    public StartCommand(Recorder recorder){
        this.recorder=recorder;
    }
    /**
   * Executes the start command, causing the {@link Recorder} to begin recording.
   */
    @Override
    public void execute() {
       recorder.start();
    }



}
