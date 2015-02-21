package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import model.node.Node;
import model.node.NodeInfo;


public class Parser {

    private Map<String, NodeInfo> parserMap;
    private List<Entry<String, Pattern>> myPatterns;

    public Parser (List<Entry<String, Pattern>> patterns) {
        myPatterns = patterns;
    }

    public List<Node> parse (String feed) {
        List<Node> TreeList = new ArrayList<Node>();
        int childrenExpecting = 0;
        for (String token : feed.split("\\p{Z}")) {

            // read Resource Bundle
            String[] tokenInfo = getMatch(token);

            // get NodeInfo if not Variable or Command
            NodeInfo nodeInfo = parserMap.get(tokenInfo[0]);
            // construct node based on NodeInfo
            // -- contains Node type to instantiate, child #, child names
            // update childrenExpecting
            while (childrenExpecting > 0) {
                // addChildren to current Node
                // will have to use stack or recursion
            }
            // add resultant Node to TreeList somewhere
        }
        return TreeList;
    }

    private String[] getMatch (String token) {
        boolean matched = false;
        if (token.trim().length() > 0) {
            for (Entry<String, Pattern> p : myPatterns) {
                if (match(token, p.getValue())) {
                    // System.out.println(String.format("%s matches %s", token, p.getKey()));
                    matched = true;
                    return new String[] { p.getKey(), token }; // need token for Constant, Variable,
                                                               // Command
                }
            }
            if (!matched) {
                // throw no command exception
                System.out.println(String.format("%s not matched", token));
            }
        }
        return null;
    }

    private boolean match (String input, Pattern regex) {
        return regex.matcher(input).matches();
    }
}
