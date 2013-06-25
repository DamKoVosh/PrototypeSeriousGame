package items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Item {

	Image image;
	
	public Item(String name) {
		try {
			image = new Image("img/" + name + ".png");
		} catch (SlickException e) {
		}
	}
	
	public void render(GameContainer container, Graphics graphics, int i) {
		graphics.drawImage(image, i * 55 + 180, 710);
	}

}
