package player;

import items.Item;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Inventar {
	private ArrayList<Item> items;
	
	public Inventar() {
		items = new ArrayList<>();
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}
	
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).render(container, graphics, i);
		}
	}

	public int size() {
		return items.size();
	}
}
