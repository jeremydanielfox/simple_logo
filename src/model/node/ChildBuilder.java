package model.node;

/**
 * A wrapper object that contains the expected name and class-type for a node's child. It
 * helps keep track of the number of required children as well as error checking each type.
 * 
 * @author Nathan Prabhu
 *
 */
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
