package fr.istic.aco.editor;

import java.util.ArrayList;
import java.util.List;

import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;


public class Recorder {

    private class Pair {

        private Command command;
        private Memento memento;

      public Pair(Command c, Memento m) {
            this.command = c;
            this.memento = m;
        }
        private Command getCommand() {
            return command;
        }

        private Memento getMemento() {
            return memento;
        }
//rajouter les commandes start,stop,replay

}

    private List<Pair> liste;
    private boolean recording=false, replaying=false;
    public Recorder(CommandOriginator c, Memento m){
        liste = new ArrayList<Pair>();
    }


    public void save(CommandOriginator c) {
       
       
    }

   

  

}
