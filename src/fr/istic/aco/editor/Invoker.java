    package fr.istic.aco.editor;

    import java.util.Map;
    import java.util.HashMap; 
    public class Invoker {
        private Map <String, Command> map;
        private Engine engine;
        private Selection selection;
        private InsertCommand insertCommand; //  variable pour la commande d'insertion
        String testToInsert;
        int begin,end;

    public Invoker(Engine engine, Selection selection){
        map=new HashMap<>();
        this.engine = engine;
        this.selection = selection;
        this.testToInsert="";
        this.begin=0;
        this.end=0;

        insertCommand = new InsertCommand(engine); // Crée l'instance ici
        map.put("insert", insertCommand);
        map.put("cut", new CutCommand(engine));
        map.put("copy", new CopyCommand(engine));
        map.put("delete", new DeleteCommand(engine));
        map.put("paste", new PasteCommand(engine));
        map.put("selection", new SelectionCommand(selection, begin, end));
       
    }
    // Méthode pour définir le texte à insérer avant d'exécuter la commande
    public void setTextToInsert(String text) {
        this.testToInsert = text;
        insertCommand.setText(text); // Met à jour le texte dans InsertCommand
    }


    public void playCommand(String id){
        if(map.containsKey(id)){
            map.get(id).execute();
        }
        else{
            System.out.println("la clé spécifié n'existe pas ");
        }
     
       
        
    }




    }
