package model;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import model.Parser.TokenProperty;
import model.database.Database;
import model.database.OldDatabase;
import model.line.LineListCollection;
import model.node.CommandList;
import model.turtle.SingleTurtle;
import model.turtle.TurtleList;


public class Model implements Receiver, LanguageSetter {
    private List<Entry<String, Pattern>> myPatterns;
    private List<Workspace> myWorkspaces;
    private Workspace currentWorkspace;

    public Model (Point2D offset) {
        myWorkspaces = new ArrayList<Workspace>();
    }
    
    public void setLanguage (String language) {
        myPatterns = makePatterns(language);
        myPatterns.addAll(makePatterns("resources/languages/Syntax"));
    }
    

    public void giveText (String text, int id) {
        updateModel(text, id);
    }

    public void updateModel (String feed, int id) {
        currentWorkspace = myWorkspaces.get(id);
        OldDatabase.getInstance().addFeed(feed);
        Parser parser = new Parser(myPatterns);
        List<TokenProperty> feedList = parser.parse(feed);
        CommandList tree = TreeBuilder.build(currentWorkspace, feedList);
        tree.evaluate();

//        OldDatabase.getInstance().printVarsHistory(); //for testing
//        OldDatabase.getInstance().printCmdsHistory(); //for testing
    }

    private List<Entry<String, Pattern>> makePatterns (String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        List<Entry<String, Pattern>> patterns = new ArrayList<>();
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            patterns.add(new SimpleEntry<String, Pattern>(
                    key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
                                                          // THIS IS THE KEY LINE                   
        }
        return patterns;
    }

    public void setWorkspace (Workspace workspace) {
        myWorkspaces.add(workspace);
    }

    public void initalizeWorkspace (TurtleList turtles,
                                    LineListCollection linelists) {
        myWorkspaces.add(new Workspace());
    }
}
