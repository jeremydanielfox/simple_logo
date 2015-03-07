package model.database;

import java.util.HashMap;
import java.util.Map;
import javafx.collections.MapChangeListener;
import model.writable.CommandWritable;
import model.writable.ConsoleWritable;
import model.writable.FeedWritable;
import model.writable.VariableWritable;
import model.writable.Writable;

/**
 * Keeps track of various histories throughout the program, such as feed, console, variables, and commands.
 * @author Nathan Prabhu
 *
 */
public class WorkspaceHistory implements Database {

    private Map<Class<? extends Writable>, History> historiesMap;
    private History feedHistory;
    private History consoleHistory;
    private History varsHistory;
    private History cmdsHistory;

    private boolean definingSignal;

    public WorkspaceHistory () {
        initializeMaps();
        definingSignal = false;
    }

    private void initializeMaps () {
        feedHistory = new History("Feed"); // ?
        varsHistory = new History("Variables");
        cmdsHistory = new History("Commands");
        consoleHistory = new History("Console");
        historiesMap = new HashMap<Class<? extends Writable>, History>();
        historiesMap.put(FeedWritable.class, feedHistory);
        historiesMap.put(ConsoleWritable.class, consoleHistory);
        historiesMap.put(CommandWritable.class, cmdsHistory);
        historiesMap.put(VariableWritable.class, varsHistory);
    }

    @Override
    public void write (Writable writable) {
        // front-end will just call writable.getValue;
        History history = historiesMap.get(writable.getClass());
        history.put(writable.getName(), writable);
    }

    @Override // only called for variables and commands
    public Writable getWritable (String name) {
        return name.startsWith(":") ? varsHistory.get(name) : cmdsHistory.get(name);
    }

    @Override
    public void setDefiningSignal (boolean bool) {
        definingSignal = bool;
    }

    public boolean getDefiningSignal () {
        return definingSignal;
    }

    public void addFeedListener (MapChangeListener listener) {
        feedHistory.addListener(listener);
    }

    public void addVarsListener (MapChangeListener listener) {
        varsHistory.addListener(listener);
    }

    public void addCmdsListener (MapChangeListener listener) {
        cmdsHistory.addListener(listener);
    }

    public void addConsoleListener (MapChangeListener listener) {
        consoleHistory.addListener(listener);
    }

    public History getFeedHistory () {
        return feedHistory;
    }

    public History getVarsHistory () {
        return varsHistory;
    }

    public History getCmdsHistory () {
        return cmdsHistory;
    }

    public History getConsoleHistory () {
        return consoleHistory;
    }
}
