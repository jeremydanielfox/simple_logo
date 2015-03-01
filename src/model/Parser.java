package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import Exceptions.UnrecognizedTokenException;
import model.node.CommandList;
import model.node.NodeFactory;
import model.node.TreeNode;


public class Parser {

    private List<Entry<String, Pattern>> myPatterns;
    private Turtle myTurtle;

    public Parser (List<Entry<String, Pattern>> patterns, Turtle turtle) {
        myPatterns = patterns;
        myTurtle = turtle;
    }

    public CommandList parse (String feed) {
        // gets all uncommented lines
        List<String> lines =
                Arrays.asList(feed.trim().split("\\n")).stream().filter(this::isValidLine)
                        .collect(Collectors.toList());
        
        List<String> tokens = new ArrayList<String>();
        for (String line : lines) {
            tokens.addAll(Arrays.asList(line.trim().split("\\p{Z}")));
        }

        // read Resource Bundle, convert tokens to queue
        Queue<TreeNode> nodeList = new LinkedList<TreeNode>(tokens.stream()
                .map(this::getMatch)
                .collect(Collectors.toList()));
        return TreeBuilder.build(nodeList);
    }

    // TODO: only filters out lines that begin with #, fix to accomodate inline comment
    private boolean isValidLine (String line) {
        return !line.startsWith("#") && line.length()>0;
    }

    private TreeNode getMatch (String token) {
        boolean matched = false;
        if (token.trim().length() > 0) {
            for (Entry<String, Pattern> p : myPatterns) {
                if (match(token, p.getValue())) {
                    // System.out.println(String.format("%s matches %s", token, p.getKey()));
                    matched = true;
                    String[] tokenProperty = new String[] { p.getKey(), token };
                    return NodeFactory.get(tokenProperty, myTurtle); // need token for Constant,
                                                                     // Variable,
                }                                     // Command

            }
            if (!matched) {
                throw new UnrecognizedTokenException(token);
            }
        }
        // should never get to this point...
        return null;
    }

    private boolean match (String input, Pattern regex) {
        return regex.matcher(input).matches();
    }

    // TODO: refactor so not to have so many if-statements
    // private String[] getNextTokenProperty () {
    // String[] tokenProp = nodeList.poll();
    // System.out.println(tokenProp[0]);
    // if (tokenProp[0].equals("Comment")) {
    // // TODO: recognize comments, must know when lines end
    // return getNextTokenProperty();
    // }
    // else if (tokenProp[0].equals("MakeUserInstruction")) {
    // // handle making new udc
    // // -- use [ ] as ending conditions
    //
    // // TODO: error check tokens to be expected
    //
    // String name = nodeList.poll()[1]; // name of command
    // nodeList.poll(); // ListStart
    //
    // List<String> vars = new ArrayList<String>();
    // while (!nodeList.peek().equals("ListEnd")){
    // vars.add(nodeList.poll()[1]); // store each variable of the command
    // }
    //
    // nodeList.poll(); // ListEnd
    // nodeList.poll(); // ListStart
    // while (!nodeList.peek().equals("ListEnd")){
    // vars.add(nodeList.poll()[1]); // now get useful tokenProperties
    // }
    //
    //
    // return getNextTokenProperty();
    // }
    //
    // else if (tokenProp[0].equals("MakeVariable")){
    // // handle making new variable
    // List<String> vars = new ArrayList<String>();
    // vars.add(nodeList.poll()[1]); //should add until the current expression is ended (what is
    // there now is just for testing)
    // Database.getInstance().putVariable(tokenProp[1], vars.toArray(new String[vars.size()]));
    //
    // return getNextTokenProperty();
    // }
    //
    // else if (tokenProp[0].equals("Variable")) {
    // // check database if variable/udc exists, replace with value
    //
    // if (Database.getInstance().getVariable(tokenProp[1]) == null) {
    // throw new VariableNotFoundException();
    // }
    // else {
    // // -- use deque functionality: addFirst
    // testForVar();
    // return Database.getInstance().getVariable(tokenProp[1]);
    // // TODO refactor how we testForVars within brackets
    //
    // }
    // }
    // else if (tokenProp[0].equals("Command")) {
    // // check database if variable/udc exists, replace with value
    // // -- use deque functionality: addFirst
    // // otherwise throw new CommandNotFoundException();
    // if (Database.getInstance().getVariable(tokenProp[1]) == null) {
    // throw new VariableNotFoundException();
    // }
    // else {
    // // -- use deque functionality: addFirst
    // testForVar();
    // return Database.getInstance().getVariable(tokenProp[1]);
    // // TODO refactor how we testForVars within brackets
    // }
    // }
    // else if (tokenProp[0].equals("ListStart")) {
    // // do something... should apply to MakeUserInstructions, iterators, conditionals
    // testForOpenBracket();
    // openBrackets.push("Open");
    // return null;
    // }
    // else if (tokenProp[0].equals("ListEnd")) {
    // // do something... should apply to MakeUserInstructions, iterators, conditionals
    // testForClosedBracket();
    // if (!openBrackets.empty()){
    // openBrackets.pop();
    // }
    // else {
    // throw new IncorrectNumberOfBracketsException();
    // }
    // return null;
    // }
    // else {
    // return tokenProp;
    // }
    // }
}
