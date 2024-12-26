package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Recorder;

/**
 * The {@code StopCommand} class implements the {@link Command} interface
 * and is responsible for stopping the recording process in the
 * {@link Recorder}.
 * This command triggers the recorder to stop recording actions.
 */

public class StopCommand implements Command {
    private Recorder recorder;

    /**
     * Constructs a {@code StopCommand} with the specified recorder.
     * 
     * @param recorder the recorder that will stop recording actions
     */

    public StopCommand(Recorder recorder) {
        this.recorder = recorder;
    }

    /**
     * Executes the stop command, causing the {@link Recorder} to stop recording.
     */

    @Override
    public void execute() {
        recorder.stop();
    }

}
