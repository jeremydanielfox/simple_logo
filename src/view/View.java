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
        myDisplay = Display.getInstance(getLanguageSetter());
        Scene scene = myDisplay.init(myModel);
        // makeWorkspace();
        initializeWorkspace();
        myStage.setScene(scene);
        myStage.show();
    }

    private void initializeWorkspace () {
        Workspace workspace = new Workspace();
        addListeners(workspace.getTurtles(), workspace.getLines());
        myDisplay.makeWorkspaceDisplay((Receiver) myModel, workspace.getId());
        myModel.setWorkspace(workspace);
    }

    private void addListeners (TurtleList turtles, LineListCollection lineLists) {
        turtles.addChangeListener( (obs, ov, nv) -> {
            System.out.println("triggered1");
            myDisplay.getSelectedWorkspace().getTurtleView()
                    .clearTurtles();
            turtles.beDrawn(myDisplay.getSelectedWorkspace()
                    .getTurtleView());
        });
        lineLists.addListener(c -> {
            System.out.println("triggered2");
            myDisplay.getSelectedWorkspace().getTurtleView()
                    .clearTurtles();
            turtles.beDrawn(myDisplay.getSelectedWorkspace()
                    .getTurtleView());
            myDisplay.getSelectedWorkspace().getTurtleView()
                    .clearLines();
            lineLists.beDrawn(myDisplay.getSelectedWorkspace()
                    .getTurtleView());
        });
    }

    public LanguageSetter getLanguageSetter () {
        return (LanguageSetter) myModel;
    }

    @Override
    public void makeWorkspace () {
        // TODO Auto-generated method stub

    }
}
