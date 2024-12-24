package fr.istic.aco.editor.Interface;

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
    /* */
     boolean isReplaying();
}
