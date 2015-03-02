package model.node;

public class ChildBuilder {

    private String myName;
    private Class<? extends TreeNode> myType;

    public ChildBuilder (String name, Class<? extends TreeNode> type) {
        myName = name;
        myType = type;
    }
    
    public String getName () {
        return myName;
    }

    public Class<? extends TreeNode> getType () {
        return myType;
    }
}
