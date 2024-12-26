package fr.istic.aco.editor.ClassImpl;

import java.util.ArrayList;
import java.util.List;

import fr.istic.aco.editor.CommandOriginator.InsertCommand;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Recorder;

/**
 * Implementation of the {@link Recorder} interface for recording and replaying
 * command execution sequences along with their states (mementos).
 */
public class RecorderImpl implements Recorder {

    private List<Pair<CommandOriginator, Memento>> liste;
    private List<Pair<CommandOriginator, Memento>> historique;

    private boolean recording = false;
    private boolean replaying = false;

    /**
     * Constructs a new instance of {@code RecorderImpl}.
     */
    public RecorderImpl() {
        liste = new ArrayList<>();
        historique = new ArrayList<>();
    }

    /**
     * Starts recording commands and clears the current recording list.
     */

    @Override
    public void start() {
        liste.clear();
        this.recording = true;
    }

    /**
     * Stops the recording of commands.
     */

    @Override
    public void stop() {
        this.recording = false;
    }

    /**
     * Saves a command along with its memento if recording is enabled and replaying
     * is not in progress.
     *
     * @param c the command to save
     */
    @Override
    public void save(CommandOriginator c) {
        if (recording && !replaying) {
            Memento memento = c.getMemento();
            Pair<CommandOriginator, Memento> pair = new Pair<>(c, memento);
            liste.add(pair);
            // System.out.println("Commande enregistrée: " + c.getClass().getSimpleName() +
            // " avec état: " + memento);
        }
    }

    /**
     * Retrieves the history of all executed commands.
     *
     * @return a list of all executed commands and their states
     */
    public List<Pair<CommandOriginator, Memento>> getHistorique() {
        return historique;
    }

    /**
     * Replays all recorded commands in the current session by restoring their
     * mementos and executing them. Saves the replayed commands into the history.
     */
   
    @Override
    public void replay() {
        replaying = true;
        try {
            historique.addAll(liste);
            for (Pair<CommandOriginator, Memento> pair : liste) {
                if (pair == null || pair.getCommandOriginator() == null || pair.getMemento() == null) {
                    System.err.println("Invalid pair in recording list");
                    continue;
                }
    
                CommandOriginator command = pair.getCommandOriginator();
                Memento memento = pair.getMemento();
    
                try {
                    command.setMemento(memento);
                    command.execute();
                } catch (Exception e) {
                    System.err.println("Error replaying command: " + e.getMessage());
                }
            }
        } finally {
            replaying = false;
        }
    }

    /**
     * Retrieves the number of commands in the current recording list.
     *
     * @return the size of the recording list
     */

    public int getList() {
        return liste.size();
    }

    /**
     * Checks if the recorder is currently replaying commands.
     *
     * @return {@code true} if replaying is in progress, otherwise {@code false}
     */
    public boolean isReplaying() {
        return replaying;
    }

}
