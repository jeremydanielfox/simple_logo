package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import model.database.Database;
import model.node.TreeNode;

public class Model implements Receiver {
	private List<Entry<String, Pattern>> myPatterns;
	private Turtle myTurtle;
	private ScreenData myScreenData;

	public Model(ScreenData sd) {
		myScreenData = sd;
		myTurtle = new Turtle();
	}

	public void giveText(String text) {
		updateModel(text);
	}

	public ScreenData updateModel(String feed) {
		Database.getInstance().addFeed(feed);
		Parser parser = new Parser(myPatterns, myTurtle);
		List<TreeNode> tree = parser.parse(feed);
		Interpreter interpreter = new Interpreter(tree);
		interpreter.interpret();
		myScreenData.update(Arrays.asList((myTurtle))); // will eventually take in a list of all turtles
		return myScreenData;
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
}
