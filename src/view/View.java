package view;

import java.util.ResourceBundle;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.LanguageSetter;
import model.Model;
import model.Receiver;
import model.Workspace;
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
        initializeWorkspace();
        myStage.setScene(scene);
        myStage.show();
        //turtles.goHome();
    }

    private void initializeWorkspace () {
        Workspace workspace = new Workspace();
        LineListCollection lineLists = workspace.getLines();
        TurtleList turtles = workspace.getTurtles();
        lineLists
                .addListener(c -> {
                    System.out.println("triggered1");
                    myDisplay.getSelectedWorkspace().getTurtleView()
                            .clearLines();
                    myDisplay.getSelectedWorkspace().getTurtleView()
                            .clearTurtles();
                    lineLists.beDrawn(myDisplay.getSelectedWorkspace()
                            .getTurtleView());
                    turtles.beDrawn(myDisplay.getSelectedWorkspace()
                            .getTurtleView());
                });
        myDisplay.makeWorkspaceDisplay((Receiver) myModel, workspace.getId());
        myModel.setWorkspace(workspace);
    }

    public LanguageSetter getLanguageSetter () {
        return (LanguageSetter) myModel;
    }

    @Override
    public void makeWorkspace () {
        // TODO Auto-generated method stub

    }
}
