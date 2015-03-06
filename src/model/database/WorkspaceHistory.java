package model.database;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.MapChangeListener;
import model.writable.CommandWritable;
import model.writable.FeedWritable;
import model.writable.VariableWritable;
import model.writable.Writable;

public class WorkspaceHistory implements Database {

	// private List<String> feedHistory;
	private Map<Class<? extends Writable>, History> historiesMap;
	private History feedHistory;
	private History varsHistory;
	private History cmdsHistory;
	// private List<ObservableMap<String, Writable>> histories;
	private boolean definingSignal;

	public WorkspaceHistory() {
		initializeMaps();
		definingSignal = false;
	}

	private void initializeMaps() {
		feedHistory = new History("Feed"); // ?
		varsHistory = new History("Variables");
		cmdsHistory = new History("Commands");
		historiesMap = new HashMap<Class<? extends Writable>, History>();
		historiesMap.put(FeedWritable.class, feedHistory);
		historiesMap.put(CommandWritable.class, cmdsHistory);
		historiesMap.put(VariableWritable.class, varsHistory);
		// histories = new ArrayList<ObservableMap<String, Writable>>();
		// histories.add(feedMap);
		// histories.add(varsMap);
		// histories.add(cmdsMap);
	}

	@Override
	public void write(Writable writable) {
		// front-end will just call writable.getValue;
		History history = historiesMap.get(writable.getClass());
		history.put(writable.getName(), writable);
	}

	@Override
	public Writable getWritable(String name) {
		if (name.startsWith(":")) {
			return varsHistory.get(name);
		} else {
			return cmdsHistory.get(name);
		}
	}

	@Override
	public void setDefiningSignal(boolean bool) {
		definingSignal = bool;
	}

	public boolean getDefiningSignal() {
		return definingSignal;
	}

	public void addFeedListener(MapChangeListener listener) {
		feedHistory.addListener(listener);
	}

	public void addVarsListener(MapChangeListener listener) {
		varsHistory.addListener(listener);
	}
	
	public void addCmdsListener(MapChangeListener listener) {
		cmdsHistory.addListener(listener);
	}
	
	public History getFeedHistory() {
		return feedHistory;
	}
	
	public History getVarsHistory() {
		return varsHistory;
	}
	
	public History getCmdsHistory() {
		return cmdsHistory;
	}
}
