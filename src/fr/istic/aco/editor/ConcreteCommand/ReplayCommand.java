package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Recorder;

/**
 * The {@code ReplayCommand} class implements the {@link Command} interface
 * and is responsible for replaying the recorded actions from the recorder.
 * This command triggers the replay operation to redo the sequence of previously
 * executed commands.
 */

public class ReplayCommand implements Command {
    private Recorder recorder;

    /**
     * Constructs a {@code ReplayCommand} with the specified recorder.
     * 
     * @param recorder the recorder that holds the history of commands to be
     *                 replayed
     */

    public ReplayCommand(Recorder recorder) {
        this.recorder = recorder;
    }

    /**
     * Executes the replay command, causing the recorder to replay the recorded
     * actions.
     */

    @Override
    public void execute() {
        recorder.replay();

    }

}
