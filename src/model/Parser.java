package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import model.node.Constant;
import model.node.TreeNode;
import model.node.TreeNodeInfo;


public class Parser {

    private Map<String, TreeNodeInfo> parserMap;
    private List<Entry<String, Pattern>> myPatterns;

    public Parser (List<Entry<String, Pattern>> patterns) {
        myPatterns = patterns;
    }

    public List<TreeNode> parse (String feed) {
        List<TreeNode> TreeList = new ArrayList<TreeNode>();
        List<String> tokens = Arrays.asList(feed.split("\\p{Z}"));
        
        // read Resource Bundle
        List<String[]> tokenProperties = tokens.stream()
                                               .map(this::getMatch)
                                               .collect(Collectors.toList());
        
        for (String[] tokenProp : tokenProperties) {

            if (tokenProp[0].equals("MakeVariable") || tokenProp[0].equals("MakeUserInstruction")){
                // handle making new variable or udc. will involve accessing the split feed...
            }

            else if (tokenProp[0].equals("Variable") || tokenProp[0].equals("Command")){
                // check database if it exists, replace with value
                // otherwise throw command/variable not found exception
            }
            
            
            
            // get NodeInfo if not Variable or Command
            else{
                TreeNodeInfo nodeInfo = parserMap.get(tokenProp[0]);
                // construct node based on NodeInfo
                // -- contains Node type to instantiate, child #, child names
                // update childrenExpecting somehow with stack
                Stack<TreeNodeInfo> nodeInfoStack = new Stack<TreeNodeInfo>();
                Stack<TreeNode> unfilledNodes = new Stack<TreeNode>();
                nodeInfoStack.push(nodeInfo);
                unfilledNodes.push(new Constant(9)); // push appropriate node from nodeInfo
                
                // addChildren to current Node
                // will have to use stack or recursion
                while (!nodeInfoStack.empty()){
                    if (nodeInfoStack.peek().allChildrenPresent()){
                        nodeInfoStack.pop();
                        unfilledNodes.pop();
                        continue;
                    }
                    TreeNode currentChild = new Constant(9);
                    unfilledNodes.peek().addChild("key", currentChild); // key and value from nodeinfo stack
                    if (!nodeInfoStack.peek().allChildrenPresent()){
                        nodeInfoStack.add(null); // currentNodeInfo
                    }
                    // addChildren to current Node
                    // will have to use stack or recursion
                }
                // add resultant Node to TreeList somewhere
            }
           
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
