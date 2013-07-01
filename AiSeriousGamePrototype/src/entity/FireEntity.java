package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import events.Event;

public class FireEntity extends BasicEntity {
	private int spritePos = (int) (Math.random() * 19);
	private Image image;
	private long lastUpdate;

	public FireEntity(String name, Image imageFix, Event event, int x, int y, float rotation) {
		super(name, imageFix, event, x, y, rotation);

		try {
			this.image = new Image("img/flames.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.drawImage(image, x, y, x + 48, y + 64, spritePos * 48, 0, spritePos * 48 + 48, 64);
		long now = System.currentTimeMillis();
		if (now - lastUpdate > 150) {
			if (spritePos > 18) {
				spritePos = 0;
			} else {
				spritePos++;
			}
			lastUpdate = now;
		}
	}
}