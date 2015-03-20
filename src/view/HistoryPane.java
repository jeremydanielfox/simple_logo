package view;

import java.util.Map;

import model.Receiver;
import model.writable.Writable;

/**
 * 
 * @author Peter
 *
 */
public class HistoryPane extends DataPane implements Historian {

	private Receiver myReceiver;
	private int myID;

	public HistoryPane(Receiver receiver, int id) {
		myReceiver = receiver;
		myID = id;
		setInstance(this);
	}

	public void handleAdd(String name) {
		myReceiver.giveText(name, myID);
		super.getListView().getSelectionModel().clearSelection();
	}

	@Override
	public void record(Map<String, Writable> history) {
		history.forEach((k, v) -> super.getList().add(v.getValue()));

	}

}
