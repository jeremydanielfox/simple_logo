package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Drawable;
import model.LanguageSetter;
import model.Model;
import model.Receiver;
import model.Workspace;
import model.line.LineList;
import model.line.LineListCollection;
import model.turtle.TurtleList;


public class View implements WorkspaceCreator {

    private static final ResourceBundle myValues = ResourceBundle
            .getBundle("resources/values/view");

    private Stage myStage;
    private Model myModel;
    private Display myDisplay;

    public View () {
    }

    public void init (Stage s) {
        myStage = s;
        myStage.setTitle(myValues.getString("Title"));
        String[] offsetAR = myValues.getString("Initial_Offset").split(", ");
        myModel = new Model(new Point2D(Integer.parseInt(offsetAR[0]),
                                        Integer.parseInt(offsetAR[1])));
        myModel.setLanguage(myValues.getString("Language"));
        myDisplay = Display.getInstance();
        Scene scene = myDisplay.init(myModel);
        // makeWorkspace();
        initializeWorkspace();
        myStage.setScene(scene);
        myStage.show();
    }

    private void initializeWorkspace () {
        Workspace workspace = new Workspace();
        LineListCollection lineLists = workspace.getLines();
        TurtleList turtles = workspace.getTurtles();
        lineLists.addListener(c -> {
            System.out.println("triggered1");
            myDisplay.getSelectedWorkspace().getTurtleView().clearLines();
            myDisplay.getSelectedWorkspace().getTurtleView().clearTurtles();
            lineLists.beDrawn(myDisplay.getSelectedWorkspace().getTurtleView());
            turtles.beDrawn(myDisplay.getSelectedWorkspace().getTurtleView());
        });
//        TurtleList turtles = workspace.getTurtles();
//        turtles.addLocationListener((o) -> {
//            System.out.println("triggered2");
//        });
//        turtles.addHeadingListener((o) ->{
//            System.out.println("triggered3");
//        });
        
        myModel.setWorkspace(workspace);
    }

    public void makeWorkspace () {
        String[] drawNames = new String[] { "Lines", "Turtles", "Stamps" };
        Map<String, ObservableList<Drawable>> myDrawables = new HashMap<>();
        // for (int i = 0; i < drawNames.length; i++)
        // myDrawables.put(drawNames[i], createDrawables());
        ObservableList<String> promptHist = FXCollections.observableArrayList();
        promptHist.addListener( (ListChangeListener.Change<? extends String> c) -> {
            while (c.next()) {
                for (String addItem : c.getAddedSubList()) {
                    myDisplay.getSelectedWorkspace().getHistoryPane()
                            .add(addItem);
                }
            }
        });
        ObservableMap<String, String> varsMap = FXCollections
                .observableHashMap();
        varsMap.addListener( (
                              MapChangeListener.Change<? extends String, ? extends String> c) -> {
            myDisplay.getSelectedWorkspace().getVariablePane()
                    .put(c.getKey(), c.getValueAdded());
        });
        // myModel.initializeWorkspace(myDrawables);
        myDisplay.makeWorkspaceDisplay((Receiver) myModel);
    }

    public LanguageSetter getLanguageSetter () {
        return (LanguageSetter) myModel;
    }
}
