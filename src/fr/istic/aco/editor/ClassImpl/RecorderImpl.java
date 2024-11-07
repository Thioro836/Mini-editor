        package fr.istic.aco.editor.ClassImpl;

        import java.util.ArrayList;
        import java.util.List;

        import fr.istic.aco.editor.CommandOriginator.InsertCommand;
        import fr.istic.aco.editor.Interface.Command;
        import fr.istic.aco.editor.Interface.CommandOriginator;
        import fr.istic.aco.editor.Interface.Memento;
        import fr.istic.aco.editor.Interface.Recorder;


        public class RecorderImpl implements Recorder {

            private class Pair {

                private CommandOriginator command;
                private Memento memento;

             Pair(CommandOriginator c, Memento m) {
                    this.command = c;
                    this.memento = m;
                }
            private CommandOriginator getCommandOriginator() {
                    return command;
                }

                private Memento getMemento() {
                    return memento;
                }
                
            

        }

            private List<Pair> liste;

            private boolean recording=false, replaying=false;

            public RecorderImpl(){
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
            public void save(CommandOriginator c) {
                    
              if(recording){
                liste.add(new Pair(c, c.getMemento()));
              }
            
            
            }

                @Override
                public void replay() {

                for(int i=0; i<liste.size();i++){

                Pair pair= liste.get(i);
                CommandOriginator command= pair.getCommandOriginator();
                Memento memento =pair.getMemento();
                    command.setMemento(memento);  //restaurer létat
                    command.execute();

                }

                
                }

                public int getList(){
                    return liste.size();
                }
                


        

        

        }
