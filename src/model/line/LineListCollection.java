package model.line;

import java.util.Collections;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Clearable;
import model.turtle.SingleTurtle;
import model.turtle.TurtleList;
import view.Clearer;
import view.Drawer;


// would belong to a workspace
public class LineListCollection implements Line, Clearable,ChangeListener<LineList>,ObservableValue<LineListCollection> {

    private int myId;
    private ObservableList<LineList> myLineLists;

    public LineListCollection () {
        myId = 0;
    }

    public LineListCollection (int id, TurtleList turtles) {
        myId = id;
        myLineLists = FXCollections.observableArrayList();
        for (SingleTurtle turtle : turtles.getAllTurtles()) {
            myLineLists.add(turtle.getLines());
            turtle.getLines().addListener(this);
        }
    }

    @Override
    public void beDrawn (Drawer drawer) {
        myLineLists.forEach(linelist -> linelist.beDrawn(drawer));
    }

    public List<LineList> getLineLists () {
        return Collections.unmodifiableList(myLineLists);
    }

    public int getId () {
        return myId;
    }

    public String toString () {
        return myLineLists.toString();
    }

    @Override
    public void beCleared (Clearer clearer) {
        myLineLists.forEach(linelist -> linelist.beCleared(clearer));

    }

//    public void addListener (ListChangeListener<? super Line> listener) {
//        myLineLists.addListener(listener);
//        myLineLists.forEach(lineList -> lineList.addListener(listener));
//        // addListener((ListChangeListener<? super LineList>) listener);
//    }

	@Override
	public void changed(ObservableValue<? extends LineList> observable,
			LineList oldValue, LineList newValue) {
		// TODO Auto-generated method stub
		myLineLists.remove(oldValue);
		myLineLists.add(newValue);
		
	}

@Override
public void addListener(InvalidationListener listener) {
	// TODO Auto-generated method stub
	
}

@Override
public void removeListener(InvalidationListener listener) {
	// TODO Auto-generated method stub
	
}

@Override
public void addListener(ChangeListener<? super LineListCollection> listener) {
	// TODO Auto-generated method stub
	
}

@Override
public void removeListener(ChangeListener<? super LineListCollection> listener) {
	// TODO Auto-generated method stub
	
}

@Override
public LineListCollection getValue() {
	// TODO Auto-generated method stub
	return this;
}

//
//    @Override
//    public void propertyChange (PropertyChangeEvent evt) {
//        Object source = evt.getSource();
//        if (source.equals(myLineLists)){
//            
//        }
//        
//    }


    // @Override
    // public int size () {
    // return myLineLists.size();
    // }
    //
    // @Override
    // public boolean isEmpty () {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public boolean contains (Object o) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public Iterator<LineList> iterator () {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public Object[] toArray () {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public <T> T[] toArray (T[] a) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public boolean add (LineList e) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public boolean remove (Object o) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public boolean containsAll (Collection<?> c) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public boolean addAll (Collection<? extends LineList> c) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public boolean addAll (int index, Collection<? extends LineList> c) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public boolean removeAll (Collection<?> c) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public boolean retainAll (Collection<?> c) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public void clear () {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public LineList get (int index) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public LineList set (int index, LineList element) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public void add (int index, LineList element) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public LineList remove (int index) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public int indexOf (Object o) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public int lastIndexOf (Object o) {
    // return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public ListIterator<LineList> listIterator () {
    // }
    //
    // @Override
    // public ListIterator<LineList> listIterator (int index) {
    //
    // }
    //
    // @Override
    // public List<LineList> subList (int fromIndex, int toIndex) {
    // }
    //
    // @Override
    // public void addListener (InvalidationListener listener) {
    //
    // }
    //
    // @Override
    // public void removeListener (InvalidationListener listener) {
    //
    // }
    //
    // @Override
    // public void addListener (ListChangeListener<? super LineList> listener) {
    // //return myLineLists.isEmpty();
    // }
    //
    // @Override
    // public void removeListener (ListChangeListener<? super LineList> listener) {
    // // TODO Auto-generated method stub
    //
    // }
    //
    // @Override
    // public boolean addAll (LineList ... elements) {
    // return myLineLists.addAll(elements);
    // }
    //
    // @Override
    // public boolean setAll (LineList ... elements) {
    // return myLineLists.setAll(elements);
    // }
    //
    // @Override
    // public boolean setAll (Collection<? extends LineList> col) {
    // return myLineLists.setAll(col);
    // }
    //
    // @Override
    // public boolean removeAll (LineList ... elements) {
    // return myLineLists.removeAll(elements);
    // }
    //
    // @Override
    // public boolean retainAll (LineList ... elements) {
    // myLineLists.retainAll(elements);
    // }
    //
    // @Override
    // public void remove (int from, int to) {
    // myLineLists.remove(from, to);
    // }

}
