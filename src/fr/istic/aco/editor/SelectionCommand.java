package fr.istic.aco.editor;

public class SelectionCommand implements Command {
private Selection selection;
private int begin, end;
public SelectionCommand (Selection selection, int begin,int end ){
    this.selection=selection;
    this.begin=begin;
    this.end=end;
}
    @Override
    public void execute() {
        selection.setBeginIndex(begin);
        selection.setEndIndex(end);
    }
    
}
