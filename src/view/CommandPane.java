package view;

import java.util.Map;

import model.writable.Writable;
/**
 * 
 * @author Peter
 *
 */
public class CommandPane extends DataPane implements Historian {

	private Feed myFeed;

	public CommandPane(Feed feed) {
		myFeed = feed;
		setInstance(this);
	}
	
	public void handleAdd(String command) {
		myFeed.addText(command);
		super.getListView().getSelectionModel().clearSelection();
	}
	
	@Override
	public void record(Map<String, Writable> history) {
		history.forEach((k, v) -> super.getMap().put(v.getName(), v.getValue()));
		setList(super.getMap().keySet());
	}

}
