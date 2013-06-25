package player;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Fisher {
	private static Image fisher;
	/**
	 * Height of a tile of the fisher's sprite.
	 */
	private static int fisherHeight = 75;
	/**
	 * Width of a tile of the fisher's sprite.
	 */
	private static int fisherWidth = 96;
	private int spritePos = 0;
	private int spritePosWaiting = 0;
	private long lastUpdate;

	public Fisher () {
		try {
			fisher = new Image("img/fisher.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		lastUpdate = System.currentTimeMillis();
	}

	public void renderWalkingFisher(Graphics graphics, boolean left, boolean right, boolean up, boolean down,
			int x, int y, int lastDirection, int spritePos) {
		if (!(left || right || up || down)) {
			// standing
			if (lastDirection == Player.RIGHT) {
				graphics.drawImage(fisher, x, y, x + fisherWidth, y + fisherHeight, 1344, 75, 1344 + fisherWidth, 75 + fisherHeight);
			} else {
				graphics.drawImage(fisher, x, y, x + fisherWidth, y + fisherHeight, 1344, 75, 1344 + fisherWidth, 75 + fisherHeight);
			}
		} else {
			if (lastDirection == Player.RIGHT) {
				graphics.drawImage(fisher, x, y, x + fisherWidth, y + fisherHeight, (spritePos + 7) * fisherWidth, 0,
						(spritePos + 7) * fisherWidth + fisherWidth, fisherHeight);
			} else {
				graphics.drawImage(fisher, x, y, x + fisherWidth, y + fisherHeight, spritePos * fisherWidth, 0,
						spritePos * fisherWidth + fisherWidth, fisherHeight);
			}
		}
	}

	public void renderFishingFisher(Graphics graphics, int x, int y) {
		graphics.drawImage(fisher, x, y, x + fisherWidth, y + fisherHeight, (this.spritePos) * fisherWidth, fisherHeight,
				(this.spritePos) * fisherWidth + fisherWidth, fisherHeight * 2);
		long now = System.currentTimeMillis();
		if ((now - lastUpdate) >= 90) {
			this.spritePos++;
			if (this.spritePos > 12) {
				if (this.spritePosWaiting < 8) {
					this.spritePos = 10;
					this.spritePosWaiting = this.spritePosWaiting - (int) Math.floor(Math.random() * 2) + 1;
				} else {
					this.spritePos = 0;
					spritePosWaiting = 0;
				}
			}
			lastUpdate = now;
		}
	}
}