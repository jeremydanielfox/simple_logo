package model.node;

import java.util.List;

public class ChildBuilder {

    private String myName;
    private Class<? extends TreeNode> myType;

    public ChildBuilder(String childName, Class<? extends TreeNode> nodeType){
       myName = childName;
       myType = nodeType;
    }
    
    public String getName(){
        return myName;
    }
    
    public Class<? extends TreeNode> getType(){
        return myType;
    }
    
    
    public String verifyChild(EvalNode node){
        // ensure appropriate number of children (from polling the queues)
        
        // get name from childNames
        // ensure node is correct type
        
        // TODO: report when too little children added
        // also when too many added
        return "";
    }
    
    // another method that checks if queues are empty
}
