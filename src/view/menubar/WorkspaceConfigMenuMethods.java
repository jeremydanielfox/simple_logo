package view.menubar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import view.Display;

public class WorkspaceConfigMenuMethods {

	private static WorkspaceConfigMenuMethods instance;
	private Display myDisplay;
	private static final int PEN_MIN = 1;
	private static final int PEN_MAX = 10;

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
		Scene tempScene = new Scene(myColorPicker);

		tempStage.setScene(tempScene);
		tempStage.show();
		myColorPicker.show();
		return myColorPicker;
	}

	public void chooseBackgroundColor() {
		ColorPicker myCP = makeColorPicker();
		myCP.setOnAction(e -> setBackgroundColor(myCP));
	}

	public Slider makeSlider(int min, int max) {
		Slider slider = new Slider();
		slider.setMin(min);
		slider.setMax(max);
		slider.setValue(max / 2);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(50);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(10);
		Stage tempStage = new Stage();
		tempStage.setMaxHeight(Double.MAX_VALUE);
		tempStage.setMaxWidth(Double.MAX_VALUE);
		Scene tempScene = new Scene(slider);
		tempStage.setScene(tempScene);
		tempStage.show();
		return slider;
	}

	public void choosePenWidth() {
		Slider mySlider = makeSlider(PEN_MIN, PEN_MAX);
		mySlider.valueProperty().addListener(
				(observable, oldValue, newValue) -> {
					myDisplay.getSelectedWorkspace().getTurtleView()
							.setPenWidth(newValue.doubleValue());
				});
	}

	private void setBackgroundColor(ColorPicker myColorPicker) {
		myDisplay.getSelectedWorkspace().getTurtleView()
				.setBackgroundColor(myColorPicker.getValue());
		myDisplay.getRoot().getChildren().remove(myColorPicker);
	}

	public void choosePenColor() {
		ColorPicker myCP = makeColorPicker();
		myCP.setOnAction(e -> setPenColor(myCP));
	}

	private void setPenColor(ColorPicker myColorPicker) {
		myDisplay.getSelectedWorkspace().getTurtleView()
				.setPenColor(myColorPicker.getValue());
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
			myDisplay.getSelectedWorkspace().getTurtleView().setTurtleImage(image);
		} catch (IOException ex) {
			System.out.println("Error in loading image");
		}
	}

}
