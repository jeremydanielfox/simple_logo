package model.database;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import model.database.OldDatabase.CommandWrapper;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.database.Variable;

public interface IHistory {
    
    public void putVariable (String name, EvalNode node);
    
    public void putCommand (String name, List<Variable> params, CommandList list);
    
    public void setDefiningSignal (boolean bool);

    public boolean getDefiningSignal ();

    public EvalNode getVariable (String name);
    
    public CommandWrapper getCommand (String name);
    
    public ObservableList<String> getFeedHistory ();
    
    public ObservableMap<String, String> getVarsHistory ();
    
    public ObservableMap<String, String> getCmdsHistory ();

}
