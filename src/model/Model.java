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
import model.line.LineListCollection;
import model.node.CommandList;
import model.turtle.SingleTurtle;
import model.turtle.TurtleList;

public class Model implements Receiver {
	private List<Entry<String, Pattern>> myPatterns;
	private SingleTurtle myTurtle;
	private List<Workspace> myWorkspaces;

	public Model(Point2D offset) {
		myTurtle = new SingleTurtle();
		//myScreenData.update(Arrays.asList((myTurtle)));
	}

	public void giveText(String text) {
		updateModel(text);
	}

	public void updateModel(String feed) {
            Database.getInstance().addFeed(feed);
            Parser parser = new Parser(myPatterns);
            List<TokenProperty> feedList = parser.parse(feed);
            CommandList tree = TreeBuilder.build(myTurtle, feedList);
            //CommandList tree = parser.parse(feed);
            tree.evaluate();
            
            //Database.getInstance().printVarsHistory(); //for testing
            
//            myScreenData.update(Arrays.asList(myTurtle));
//            return myScreenData;
        }

	public void setLanguage(String language) {
		myPatterns = makePatterns(language);
		myPatterns.addAll(makePatterns("resources/languages/Syntax"));
	}

	private List<Entry<String, Pattern>> makePatterns(String syntax) {
		ResourceBundle resources = ResourceBundle.getBundle(syntax);
		List<Entry<String, Pattern>> patterns = new ArrayList<>();
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			patterns.add(new SimpleEntry<String, Pattern>(key,
			// THIS IS THE KEY LINE
					Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
		return patterns;
	}

//	public void setScreenData(ScreenData sd) {
//		myScreenData = sd;
//		myScreenData.update(Arrays.asList((myTurtle)));
//
////		myTurtle = myScreenData.getTurtle();
//	}
	
	public void initializeNewWorkspace(Map<String,ObservableList<Drawable>> workspaceParams) {
		//TODO: Implement this method
	}
	
	public void initalizeWorkspace(TurtleList turtles, LineListCollection linelists) {
	    myWorkspaces.add(new Workspace(turtles, linelists));
	}
}
