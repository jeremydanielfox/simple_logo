package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import model.node.Constant;
import model.node.TreeNode;
import model.node.TreeNodeInfo;


public class Parser {

    private Map<String, TreeNodeInfo> parserMap;
    private List<Entry<String, Pattern>> myPatterns;
    private Deque<String[]> tokenProperties;

    // Jk!! won't work... 2 Forwards would refer to the same node... :(
    private void buildParserMap () {
        parserMap = new HashMap<String, TreeNodeInfo>();
        parserMap.put("Forward", new TreeNodeInfo("Forward", new String[] { "distance" }));
        parserMap.put("Backward", new TreeNodeInfo("Backward", new String[] { "distance" }));
        parserMap.put("Sum", new TreeNodeInfo("Sum", new String[] { "arg1", "arg2" }));
        parserMap.put("Repeat", new TreeNodeInfo("Iteration", new String[] { "max" }));
        parserMap.put("Constant", new TreeNodeInfo("Constant", new String[0]));
    }

    public Parser (List<Entry<String, Pattern>> patterns) {
        myPatterns = patterns;
        buildParserMap(); // could be called in Model if static method
    }

    public List<TreeNode> parse (String feed) {
        List<String> tokens = Arrays.asList(feed.split("\\p{Z}"));

        // read Resource Bundle, convert tokens to Deque
        tokenProperties = new LinkedList<String[]>(tokens.stream()
                .map(this::getMatch)
                .collect(Collectors.toList()));
        return buildTrees();
    }

    private List<TreeNode> buildTrees () {
        List<TreeNode> treeList = new ArrayList<TreeNode>(); // to be returned

        // build trees for all tokenProperties
        while (!tokenProperties.isEmpty()) {

            // TODO: must handle list starts/ends for iterators/conditionals
            // -- recognize them (error-check) but not put in tree

            // nodeInfo initializes appropriate node, uses tokenProperties
            TreeNodeInfo nodeInfo = getNextNodeInfo();

            // push nodeInfo onto stack
            Stack<TreeNodeInfo> nodeInfoStack = new Stack<TreeNodeInfo>();
            nodeInfoStack.push(nodeInfo);

            // continue to add children until node in nodeInfo is filled
            while (!nodeInfoStack.empty()) {
                if (nodeInfoStack.peek().allChildrenPresent()) {
                    nodeInfoStack.pop();
                    continue;
                }
                if (tokenProperties.isEmpty()) {
                    // throw "unexpected end of instructions" error
                    // -- e.g. fd sum 50
                }

                TreeNodeInfo childNodeInfo = getNextNodeInfo();
                nodeInfoStack.peek().addChild(childNodeInfo.getNode());

                if (!childNodeInfo.allChildrenPresent()) {
                    nodeInfoStack.add(childNodeInfo); // currentNodeInfo
                }
            }

            // nodeInfo will have all children
            treeList.add(nodeInfo.getNode());
        }
        return treeList;
    }

    private TreeNodeInfo getNextNodeInfo () {
        String[] tokenProp = getNextTokenProperty();
        TreeNodeInfo nodeInfo = parserMap.get(tokenProp[0]);
        // special case of creating a constant node
        if (tokenProp[0].equals("Constant")) {
            nodeInfo.setConstant(new Constant(Double.parseDouble(tokenProp[1])));
        }
        return nodeInfo;
    }

    private String[] getNextTokenProperty () {
        String[] tokenProp = tokenProperties.poll();

        if (tokenProp[0].equals("MakeVariable") || tokenProp[0].equals("MakeUserInstruction")) {
            // handle making new variable or udc
            // -- use [ ] as ending conditions
            return getNextTokenProperty();
        }
        else if (tokenProp[0].equals("Variable") || tokenProp[0].equals("Command")) {
            // check database if variable/udc exists, replace with value
            // -- use deque functionality: addFirst
            // otherwise throw command/variable not found exception
            return getNextTokenProperty();
        }
        else if (tokenProp[0].equals("ListStart")) {
            // do something... should apply to MakeUserInstructions, iterators, conditionals
            return null;
        }
        else if (tokenProp[0].equals("ListEnd")) {
            // do something... should apply to MakeUserInstructions, iterators, conditionals
            return null;
        }
        else {
            return tokenProp;
        }
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
                // throw unrecognized token exception
                System.out.println(String.format("%s not matched", token));
            }
        }
        // should never get to this point...
        return null;
    }

    private boolean match (String input, Pattern regex) {
        return regex.matcher(input).matches();
    }
}
