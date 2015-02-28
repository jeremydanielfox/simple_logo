package view.menubar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import view.Display;

public class WorkspaceConfigMenuMethods {
	
	private static WorkspaceConfigMenuMethods instance;
	private Display myDisplay;

	protected static WorkspaceConfigMenuMethods getInstance() {
		if (instance == null)
			instance = new WorkspaceConfigMenuMethods();
		return instance;
	}
	
	public void setParams(ArrayList<Object> params) {
		myDisplay = (Display) params.get(0);
	}
	
	public ColorPicker makeColorPicker() {
		ColorPicker myColorPicker = new ColorPicker();
		
		Stage tempStage = new Stage();
		tempStage.setMaxHeight(Double.MAX_VALUE);
		tempStage.setMaxWidth(Double.MAX_VALUE);
		Group tempGroup = new Group(myColorPicker);
		Scene tempScene = new Scene(tempGroup);
		
		tempStage.setScene(tempScene);
		tempStage.show();
		myColorPicker.show();
		return myColorPicker;
//		ColorPicker myColorPicker = new ColorPicker();
//		myDisplay.getRoot().getChildren().add(myColorPicker);
//		return myColorPicker;
	}

	public void chooseBackgroundColor() {
		ColorPicker myCP = makeColorPicker();
		myCP.setOnAction(e -> setBackgroundColor(myCP));
	}
	
	public void choosePenWidth() {
		myDisplay.getSelectedWorkspace().getTV().setPenWidth(4);
	}

	private void setBackgroundColor(ColorPicker myColorPicker) {
		myDisplay.getSelectedWorkspace().getTV()
				.setBackgroundColor(myColorPicker.getValue());
		myDisplay.getRoot().getChildren().remove(myColorPicker);
	}

	public void choosePenColor() {
		ColorPicker myCP = makeColorPicker();
		myCP.setOnAction(e -> setPenColor(myCP));
	}

	private void setPenColor(ColorPicker myColorPicker) {
		myDisplay.getSelectedWorkspace().getTV().setPenColor(myColorPicker.getValue());
		myDisplay.getRoot().getChildren().remove(myColorPicker);
	}

	public void chooseImage() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter(
				"JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter(
				"PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		File file = fileChooser.showOpenDialog(null);
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			myDisplay.getSelectedWorkspace().getTV().setTurtleImage(image);
		} catch (IOException ex) {
			System.out.println("Error in loading image");
		}
	}

}
