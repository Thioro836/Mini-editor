package fr.istic.aco.editor.Interface;

public interface Recorder {

    // commencer l'enregistrement
    void start();

    // stoper l'enregistrement
    void stop();

    // enregistrer la commande
    void save(CommandOriginator c);

    // rejouer la commande
    void replay();

    int getList();

    public boolean isReplaying();

}
