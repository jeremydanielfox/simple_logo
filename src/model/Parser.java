package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import Exceptions.UnrecognizedTokenException;


public class Parser {

    private List<Entry<String, Pattern>> myPatterns;

    public Parser (List<Entry<String, Pattern>> patterns) {
        myPatterns = patterns;
    }

    public List<TokenProperty> parse (String feed) {
        // gets all uncommented lines
        List<String> lines =
                Arrays.asList(feed.trim().split("\\n")).stream().filter(this::isValidLine)
                        .collect(Collectors.toList());

        List<String> tokens = new ArrayList<String>();
        for (String line : lines) {
            tokens.addAll(Arrays.asList(line.trim().split("\\p{Z}")));
        }

        // read Resource Bundle, convert tokens to TokenProperty
        List<TokenProperty> tokenList = tokens.stream()
                .map(this::getMatch)
                .collect(Collectors.toList());
        return tokenList;
    }

    // TODO: only filters out lines that begin with #, fix to accommodate inline comment
    private boolean isValidLine (String line) {
//        if (line.length() > 0){
//            String comment = line.substring(line.indexOf("#")+1); // gets substring starting after # (or whole line if without one)
//        }
        return !line.startsWith("#") && line.length() > 0;
    }

    private TokenProperty getMatch (String token) {
        boolean matched = false;
        if (token.trim().length() > 0) {
            for (Entry<String, Pattern> p : myPatterns) {
                if (match(token, p.getValue())) {
                    // System.out.println(String.format("%s matches %s", token, p.getKey()));
                    matched = true;
                    return new TokenProperty(p.getKey(), token);
                }                                    

            }
            if (!matched) { throw new UnrecognizedTokenException(token); }
        }
        // should never get to this point...
        return null;
    }

    private boolean match (String input, Pattern regex) {
        return regex.matcher(input).matches();
    }

    public class TokenProperty {
        private String myType;
        private String myToken;

        public TokenProperty (String type, String token) {
            myType = type;
            myToken = token;
        }

        public String getType () {
            return myType;
        }

        public String getToken () {
            return myToken;
        }
        
        public String toString(){
            return String.format("%s: %s", myType, myToken);
            
        }
    }
}
