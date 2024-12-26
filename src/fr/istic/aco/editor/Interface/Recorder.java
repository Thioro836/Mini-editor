package fr.istic.aco.editor.Interface;

/**
 * This interface defines the Recorder in the Command pattern with Memento.
 * It provides methods for starting and stopping recording commands, saving
 * commands,
 * replaying recorded commands, and retrieving the number of recorded commands.
 */

public interface Recorder {

    /**
     * Starts the recording mode.
     */

    void start();

    /**
     * Stops the recording mode. No further commands will be recorded after this is
     * called.
     */

    void stop();

    /**
     * Saves the specified command and its current state (memento) if recording is
     * enabled
     * and replaying is not in progress.
     * 
     * @param c the {@code CommandOriginator} to save
     */

    void save(CommandOriginator c);

    /**
     * Replays all recorded commands in the order they were saved. During replay,
     * the state of each command is restored using the corresponding memento before
     * execution.
     * All replayed commands are added to the historical log.
     */
    void replay();

    /**
     * Gets the number of recorded commands in the current recording session.
     * 
     * @return the size of the list of recorded commands
     */
    int getList();

    /**
     * Checks if the recorder is currently replaying commands.
     * 
     * @return {@code true} if replaying is in progress, {@code false} otherwise
     */
    boolean isReplaying();
}
