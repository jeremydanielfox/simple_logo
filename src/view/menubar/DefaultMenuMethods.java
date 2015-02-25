package view.menubar;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
//import javafx.event.Event;
//import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;

public final class DefaultMenuMethods {
	
	private static DefaultMenuMethods instance;

	private static final String HTML_FILE_PATH = "resources/help.html";
	
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

//	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void chooseBackgroundColor() {
		System.out.println("Not Implemented");
//		myRoot.getChildren().add(myColorPicker);
//		myColorPicker.setOnAction(new EventHandler() {
//
//			@Override
//			public void handle(Event event) {
//				myTurtleView.setBackgroundColor(myColorPicker.getValue());
//				myRoot.getChildren().remove(myColorPicker);
//			}
//
//		});
	}

	public void choosePenColor() {
		System.out.println("Not Implemented");
	}

	public void chooseLanguage() {
		System.out.println("Not Implemented");
	}

	public void showHelp() {
		System.out.println("Not Implemented");
		File htmlFile = new File(HTML_FILE_PATH);
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

	public Image chooseImage() {
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
			return image;
		} catch (IOException ex) {
			System.out.println("Error caught");
			return null;
		}
	}

}
