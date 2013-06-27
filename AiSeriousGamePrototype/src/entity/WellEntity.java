package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import events.Event;

public class WellEntity extends BasicEntity {
	private int spritePos = 4;
	private Image image;
	private long lastUpdate;

	public WellEntity(String name, Image imageFix, Event event, int x, int y, float rotation) {
		super(name, imageFix, event, x, y, rotation);

		try {
			this.image = new Image("img/well.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.drawImage(image, x, y, x + 116, y + 130, spritePos * 116, 0, spritePos * 116 + 116, 130);
		long now = System.currentTimeMillis();
		if (now - lastUpdate > 150) {
			if (spritePos >= 7) {
				spritePos = 4;
			} else {
				spritePos++;
			}
			lastUpdate = now;
		}
	}
}