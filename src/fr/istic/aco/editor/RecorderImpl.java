package fr.istic.aco.editor;

import java.util.ArrayList;
import java.util.List;


public class RecorderImpl implements Recorder{
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


}

    private List<Pair> liste;
    private boolean recording=false, replaying=false;
    public RecorderImpl(Command c, Memento m){
        liste = new ArrayList<Pair>();
    }
   
    
    @Override
    public void start() {
        recording=true;
       
    }

    @Override
    public void stop() {
        recording=false;
       
    }

    @Override
    public void save(Command c) {
        if(recording){
            liste.add(new Pair(c, null));

        }
       
       
    }

   

    @Override
    public void replay() {
        replaying=true;
    }

}
