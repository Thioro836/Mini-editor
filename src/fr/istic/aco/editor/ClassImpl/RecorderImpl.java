package fr.istic.aco.editor.ClassImpl;

import java.util.ArrayList;
import java.util.List;

import fr.istic.aco.editor.CommandOriginator.InsertCommand;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Recorder;

public class RecorderImpl implements Recorder {

    private List<Pair<CommandOriginator, Memento>> liste;
    private List<Pair<CommandOriginator, Memento>> historique;

    private boolean recording = false;
    private boolean replaying = false;

    public RecorderImpl() {
        liste = new ArrayList<>();
        historique = new ArrayList<>();
    }

    @Override
    public void start() {
        liste.clear();
        this.recording = true;
    }

    @Override
    public void stop() {
        this.recording = false;
    }

    @Override
    public void save(CommandOriginator c) {
        if (recording && !replaying) {
            Memento memento = c.getMemento();
            Pair<CommandOriginator, Memento> pair = new Pair<>(c, memento);
            liste.add(pair);
           // System.out.println("Commande enregistrée: " + c.getClass().getSimpleName() + " avec état: " + memento);
        }
    }

    public List<Pair<CommandOriginator, Memento>> getHistorique() {
        return historique;
    }

    @Override
    public void replay() {
        replaying = true;
        historique.addAll(liste); // Sauvegarder l'historique
        for (Pair<CommandOriginator, Memento> pair : liste) {

            CommandOriginator command = pair.getCommandOriginator();
            Memento memento = pair.getMemento();

            command.setMemento(memento); // restaurer létat
            command.execute();
        }

        replaying = false;
    }

    public int getList() {
        return liste.size();
    }

    public boolean isReplaying() {
        return replaying;
    }

}
