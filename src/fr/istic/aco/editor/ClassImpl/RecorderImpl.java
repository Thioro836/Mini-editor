
package fr.istic.aco.editor.ClassImpl;

import java.util.ArrayList;
import java.util.List;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Recorder;

/**
 * The {@code RecorderImpl} class implements the {@code Recorder} interface
 * and provides functionality to record, save, and replay commands along with
 * their states.
 * <p>
 * This implementation uses an internal {@code Pair} class to store the
 * association between a command and its corresponding memento (state).
 * It maintains a list of these pairs to enable recording and replaying of
 * commands.
 */

public class RecorderImpl implements Recorder {

    private List<Pair<CommandOriginator, Memento>> liste;
    private List<Pair<CommandOriginator, Memento>> historique;

    private boolean recording = false;
    private boolean replaying = false;

    /**
     * Constructs a new {@code RecorderImpl} and initializes the lists for
     * recording and maintaining historical data.
     */

    public RecorderImpl() {
        liste = new ArrayList<>();
        historique = new ArrayList<>();
    }

    /**
     * Starts the recording mode. Clears the current list of recorded commands.
     */
    @Override
    public void start() {
        liste.clear();
        this.recording = true;
    }

    /**
     * Stops the recording mode. No further commands will be recorded after this is
     * called.
     */
    @Override
    public void stop() {
        this.recording = false;
    }

    /**
     * Saves the specified command and its current state (memento) if recording is
     * enabled
     * and replaying is not in progress.
     * 
     * @param c the {@code CommandOriginator} to save
     */
    @Override
    public void save(CommandOriginator c) {
        if (recording && !replaying) {
            Memento memento = c.getMemento();
            Pair<CommandOriginator, Memento> pair = new Pair<>(c, memento);
            liste.add(pair);
        }
    }

    /**
     * Returns the historical log of all replayed commands.
     * 
     * @return the list of pairs representing historical commands and their states
     */

    public List<Pair<CommandOriginator, Memento>> getHistorique() {
        return historique;
    }

    /**
     * Replays all recorded commands in the order they were saved. During replay,
     * the state of each command is restored using the corresponding memento before
     * execution.
     * All replayed commands are added to the historical log.
     */
    @Override
    public void replay() {
        replaying = true;
        historique.addAll(liste);
        for (Pair<CommandOriginator, Memento> pair : liste) {

            CommandOriginator command = pair.getCommandOriginator();
            Memento memento = pair.getMemento();

            command.setMemento(memento);
            command.execute();
        }

        replaying = false;
    }

    /**
     * Gets the number of recorded commands in the current recording session.
     * 
     * @return the size of the list of recorded commands
     */
    public int getList() {
        return liste.size();
    }

    /**
     * Checks if the recorder is currently replaying commands.
     * 
     * @return {@code true} if replaying is in progress, {@code false} otherwise
     */
    public boolean isReplaying() {
        return replaying;
    }

}
