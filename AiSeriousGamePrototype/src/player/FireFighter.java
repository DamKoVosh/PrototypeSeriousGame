package player;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FireFighter {
	private Image image;
	/**
	 * Height of a tile of the fisher's sprite.
	 */
	private int imageHeight = 74;
	/**
	 * Width of a tile of the fisher's sprite.
	 */
	private int imageWidth = 52;
	private int spritePos = 0;
	private long lastUpdate;
	private boolean bucketFilled = false;

	public FireFighter () {
		try {
			image = new Image("img/bucketCarrier.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		lastUpdate = System.currentTimeMillis();
	}

	public void renderWalking(Graphics graphics, boolean left, boolean right, boolean up, boolean down, int x, int y, int lastDirection) {
		if (bucketFilled) {
			if (!(left || right || up || down)) {
				// standing
				if (lastDirection == Player.RIGHT) {
					graphics.drawImage(image, x, y, x + imageWidth, y + imageHeight, 3 * imageWidth, 4 * imageHeight, 4 * imageWidth, 5 * imageHeight);
				} else {
					graphics.drawImage(image, x, y, x + imageWidth, y + imageHeight, 2 * imageWidth, 4 * imageHeight, 3 * imageWidth, 5 * imageHeight);
				}
			} else {
				if (lastDirection == Player.RIGHT) {
					graphics.drawImage(image, x, y, x + imageWidth, y + imageHeight, spritePos * imageWidth, imageHeight,
							spritePos * imageWidth + imageWidth, 2 * imageHeight);
				} else {
					graphics.drawImage(image, x, y, x + imageWidth, y + imageHeight, spritePos * imageWidth, 0,
							spritePos * imageWidth + imageWidth, imageHeight);
				}
			}
		} else {
			if (!(left || right || up || down)) {
				// standing
				if (lastDirection == Player.RIGHT) {
					graphics.drawImage(image, x, y, x + imageWidth, y + imageHeight, imageWidth, 4 * imageHeight, 2 * imageWidth, 5 * imageHeight);
				} else {
					graphics.drawImage(image, x, y, x + imageWidth, y + imageHeight, 0, 4 * imageHeight, imageWidth, 5 * imageHeight);
				}
			} else {
				if (lastDirection == Player.RIGHT) {
					graphics.drawImage(image, x, y, x + imageWidth, y + imageHeight, spritePos * imageWidth, 2 * imageHeight,
							spritePos * imageWidth + imageWidth, 3 * imageHeight);
				} else {
					graphics.drawImage(image, x, y, x + imageWidth, y + imageHeight, spritePos * imageWidth, 3 * imageHeight,
							spritePos * imageWidth + imageWidth, 4 * imageHeight);
				}
			}
		}

		long now = System.currentTimeMillis();
		if (now - this.lastUpdate > 120) {
			if (spritePos < 7) {
				spritePos++;
			} else {
				spritePos = 0;
			}
			this.lastUpdate = now;
		}
	}

	public void toggleBucket() {
		this.bucketFilled = !this.bucketFilled;
	}
}