package fr.istic.aco.editor;

    public class InsertCommand implements Command{
    private Engine engine;
    private String  textToInsert;
    public InsertCommand(Engine engine){
        this.engine=engine;
        
    }
     // Méthode pour définir le texte à insérer
     public void setText(String text) {
        this.textToInsert = text;
    }
        @Override
        public void execute() {
        engine.insert(textToInsert);
        }
        
    }
