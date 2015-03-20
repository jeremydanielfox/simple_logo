// This entire file is part of my masterpiece
// Jeremy Fox
package model.line;

import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import view.Drawer;

/**
 * Grouping of SingleLines. Implements Line so it can be drawn. There is one LineList per turtle.
 * @author Nathan Prabhu
 *
 */
public class LineList implements Line {

	private ObservableList<SingleLine> myList;

	public LineList() {
		myList = FXCollections.observableArrayList();
	}

	@Override
	public void beDrawn(Drawer drawer) {
		myList.forEach(line -> line.beDrawn(drawer));
	}


	public void add(SingleLine line) {
		myList.add(line);
	}

	public void clear() {
		myList.clear();
	}

	public List<SingleLine> getList() {
		return Collections.unmodifiableList(myList);
	}

	public String toString() {
		return myList.toString();
	}

	public void addListener(ListChangeListener<? super Line> listener) {
		myList.addListener(listener);
	}
}
