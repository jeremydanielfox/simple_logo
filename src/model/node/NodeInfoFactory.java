package model.node;

import model.database.Database;

public final class NodeInfoFactory {
    
    private static NodeInfoFactory instance;
    
    private NodeInfoFactory () {
    }

    public static synchronized NodeInfoFactory getInstance () {
        if (instance == null)
            instance = new NodeInfoFactory();
        return instance;
    }
    
}
