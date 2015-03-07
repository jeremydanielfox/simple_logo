package model;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.geometry.Point2D;
import model.Parser.TokenProperty;
import model.node.CommandList;
import model.turtle.TurtleList;
import model.writable.FeedWritable;


/**
 * Model is the base-level class within our SLogo's Model. Since it implements Receiver, it is
 * responsible for updating the turtle based off commands from the feed prompt. It can also change
 * the language since it is a LanguageSetter
 * 
 * 
 * @author Nathan Prabhu
 *
 */

public class Model implements Receiver, LanguageSetter {
    private List<Entry<String, Pattern>> myPatterns;
    private List<Workspace> myWorkspaces;
    private Workspace currentWorkspace;

    public Model (Point2D offset) {
        myWorkspaces = new ArrayList<Workspace>();
    }

    public void giveText (String text, int id) {
        updateModel(text, id);
    }

    /**
     * Updates all turtles in the given workspace, based off instructions from the feed.
     * @param feed Prompt text entered in the GUI
     * @param id Workspace id
     */
    public void updateModel (String feed, int id) {
        currentWorkspace = myWorkspaces.get(id);
        currentWorkspace.getWorkspaceHistory().getFeedHistory()
                .put("", new FeedWritable(feed));
        Parser parser = new Parser(myPatterns);
        List<TokenProperty> feedList = parser.parse(feed);
        CommandList tree = TreeBuilder.build(currentWorkspace, feedList);
        tree.evaluate();
    }
    

    public void setLanguage (String language) {
        myPatterns = makePatterns(language);
        myPatterns.addAll(makePatterns("resources/languages/Syntax"));
    }

    /**
     * Converts a properties file into a list of patterns used in parsing.
     * @param syntax Properties file
     * @return List of patterns
     */
    private List<Entry<String, Pattern>> makePatterns (String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        List<Entry<String, Pattern>> patterns = new ArrayList<>();
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            patterns.add(new SimpleEntry<String, Pattern>(key, Pattern
                    .compile(
                             regex, Pattern.CASE_INSENSITIVE)));
            // THIS IS THE KEY LINE
        }
        return patterns;
    }


    public void setWorkspace (Workspace workspace) {
        myWorkspaces.add(workspace);
    }

    public void initalizeWorkspace (TurtleList turtles) {
        myWorkspaces.add(new Workspace());
    }
}
