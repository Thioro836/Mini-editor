        package fr.istic.aco.editor;

        import java.util.Map;
        import java.util.HashMap; 
        public class Invoker {
           // Map to store commands associated with an identifier (String)
            private Map <String, Command> map;
            private Engine engine;
            private Selection selection;
            private InsertCommand insertCommand; //   Specific command for inserting text
            private String textToInsert; //text to insert
            int begin,end; //  Indices for text selection

        public Invoker(Engine engine, Selection selection){
            map=new HashMap<>();
            this.engine = engine;
            this.selection = selection;
            this.textToInsert="";
            this.begin=0;
            this.end=0;
            //  Initializing the different commands with Engine and/or Selection
            insertCommand = new InsertCommand(engine, this); // Crée l'instance ici
            map.put("insert", insertCommand);
            map.put("cut", new CutCommand(engine));
            map.put("copy", new CopyCommand(engine));
            map.put("delete", new DeleteCommand(engine));
            map.put("paste", new PasteCommand(engine));
            map.put("selection", new SelectionCommand(selection, this));
        
        }
        // Getter to retrieve the text to insert
        public String getTextToInsert(){
            return textToInsert;
        }
        //Setter to define the text to insert before executing the insert command
        public void setTextToInsert(String text) {
            this.textToInsert = text;
        }
        // getteurs et setteurs pour la selection
      
        public int getBeginIndex(){
            return begin;
        }
        public int getEndIndex(){
            return end;
        }
       /**
     * Defines a new selection and checks that the indices are valid.
     * @param begin the begin index of the selection
     * @param end the end index of the selection
     * @throws IllegalArgumentException if the indices are out of bounds or inconsistent
     */
        public void setSelection(int begin, int end){
            if (begin < 0 || end < 0 || begin > selection.getBufferEndIndex() || end > selection.getBufferEndIndex()) {
                throw new IllegalArgumentException("selection Indices are out of bounds");
            }
            if (begin > end) {
                throw new IllegalArgumentException("Begin index cannot be greater than End index");
            }
            //  Update the selection indices in the Selection class
           selection.setBeginIndex(begin);
            selection.setEndIndex(end);
        }

        /**
     * Executes a command based on the given identifier.
     * @param id the identifier of the command to execute
     */
        public void playCommand(String id){
            // Check if the command exists in the map before executing it
            if(map.containsKey(id)){
                map.get(id).execute();
            }
            else{
                // If the command does not exist, print an error message
                System.out.println("la clé spécifié n'existe pas ");
            }
        
        
            
        }




        }
