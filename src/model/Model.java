package model;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Model {
    private List<Entry<String, Pattern>> myPatterns;
    
    public Model (){
    }
    
    public void updateModel(String feed){
        Parser parser = new Parser(myPatterns);
        parser.parse(feed);
    }
    
    public void setLanguage(String language){
        myPatterns = makePatterns(language);
        myPatterns.addAll(makePatterns("resources/languages/Syntax"));
    }

    private List<Entry<String, Pattern>> makePatterns (String syntax) {
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
