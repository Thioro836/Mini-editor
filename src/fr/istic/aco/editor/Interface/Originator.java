    package fr.istic.aco.editor.Interface;

public interface Originator {
    //enregistrer l'état du memento
        Memento getMemento();
        //restaurer l'état du memento
        void setMemento(Memento m);
    }
