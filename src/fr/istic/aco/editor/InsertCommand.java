package fr.istic.aco.editor;

    public class InsertCommand implements Command{
    private Engine engine;
    private String  testToInsert;
    public InsertCommand(Engine engine , String testToInsert){
        this.testToInsert=testToInsert;
        this.engine=engine;
    }
        @Override
        public void execute() {
        engine.insert(testToInsert);
        }
        
    }
