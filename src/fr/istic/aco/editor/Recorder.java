package fr.istic.aco.editor;

public interface Recorder {

//commencer l'enregistrement d'une commande
    void start();
     //arrêter l'enregistrement d'une commande
     void stop();
    //sauvegarder l'état d'une commande
    void save(Command c);
   
    //rejouer la commande
    void replay();

}
