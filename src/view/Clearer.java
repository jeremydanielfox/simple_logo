package view;

import model.Clearable;

public interface Clearer {

    public void clear (Clearable clearable);

	public void clearLines();

	public void clearTurtles();
}
