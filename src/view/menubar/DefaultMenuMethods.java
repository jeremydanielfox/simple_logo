package view.menubar;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;

import view.Display;
import view.View;

public final class DefaultMenuMethods {

	private static DefaultMenuMethods instance;

	protected static DefaultMenuMethods getInstance() {
		if (instance == null)
			instance = new DefaultMenuMethods();
		return instance;
	}

	public void loadCommand() {
		System.out.println("Not Implemented");
	}

	public void saveCommand() {
		System.out.println("Not Implemented");
	}

	public void quit() {
		System.exit(0);
	}

	public void undo() {
		System.out.println("Not Implemented");
	}

	public void redo() {
		System.out.println("Not Implemented");
	}

	public void cut() {
		System.out.println("Not Implemented");
	}

	public void copy() {
		System.out.println("Not Implemented");
	}

	public void paste() {
		System.out.println("Not Implemented");
	}

	public ColorPicker makeColorPicker() {
		ColorPicker myColorPicker = new ColorPicker();
		Display.getRoot().getChildren().add(myColorPicker);
		return myColorPicker;
	}

	public void chooseBackgroundColor() {
		ColorPicker myCP = makeColorPicker();
		myCP.setOnAction(e -> setBackgroundColor(myCP));
	}
	
	public void choosePenWidth() {
		Display.getSelectedWorkspace().getTV().setPenWidth(4);
	}

	private void setBackgroundColor(ColorPicker myColorPicker) {
		Display.getSelectedWorkspace().getTV()
				.setBackgroundColor(myColorPicker.getValue());
		Display.getRoot().getChildren().remove(myColorPicker);
	}

	public void choosePenColor() {
		ColorPicker myCP = makeColorPicker();
		myCP.setOnAction(e -> setPenColor(myCP));
	}

	private void setPenColor(ColorPicker myColorPicker) {
		Display.getSelectedWorkspace().getTV().setPenColor(myColorPicker.getValue());
		Display.getRoot().getChildren().remove(myColorPicker);
	}

	public void chooseLanguage() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"Resource files (*.properties)", "*.PROPERTIES");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(null);
		View.getModel().setLanguage(
				file.getPath().replaceAll("^.*/src/", "")
						.replaceAll(".properties", ""));
	}

	public void showHelp() {
		File htmlFile = new File("help.html");
		try {
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch (java.io.IOException e) {
			System.out.println("error caught");
			try {
				Desktop.getDesktop().open(htmlFile);
			} catch (java.io.IOException e1) {
				System.out.println("really caught");
			}
		}
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
			Display.getSelectedWorkspace().getTV().setTurtleImage(image);
		} catch (IOException ex) {
			System.out.println("Error caught");
		}
	}

}
