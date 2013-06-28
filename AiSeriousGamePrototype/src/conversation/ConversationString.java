package conversation;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class ConversationString {
	private String title;
	protected boolean focused;
	private ArrayList<String> text;
	private boolean clicked;

	public ConversationString(String title) {
		this.title = title;
		text = new ArrayList<>();
	}

	public void render(GameContainer container, Graphics graphics, int y) throws SlickException {
		graphics.drawString(title, 25, y);
	}
	
	public boolean handleClick(int x, int y) {
		this.clicked = true;
		return true;
	}

	public void setFocused(boolean b) {
		if (!text.isEmpty())
		this.focused = b;
	}
	
	public boolean isFocused() {
		return this.focused;
	}
	
	public ArrayList<String> getText() {
		return text;
	}
	
	public void addToText(String line) {
		text.add(line);
	}
	
	public boolean isClicked() {
		return clicked;
	}
	
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public String getTitle() {
		return title;
	}
}
