package model;

/**
 * Something that can receive text from a feed prompt.
 * @author Nate
 *
 */
public interface Receiver {
	public void giveText(String text, int ID);
}
