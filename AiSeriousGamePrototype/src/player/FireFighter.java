package player;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FireFighter {
	private Image image;
	/**
	 * Height of a tile of the fisher's sprite.
	 */
	private final int HEIGHT = 74;
	/**
	 * Width of a tile of the fisher's sprite.
	 */
	private final int WIDTH = 52;
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
					graphics.drawImage(image, x, y, x + WIDTH, y + HEIGHT, 3 * WIDTH, 4 * HEIGHT, 4 * WIDTH, 5 * HEIGHT);
				} else {
					graphics.drawImage(image, x, y, x + WIDTH, y + HEIGHT, 2 * WIDTH, 4 * HEIGHT, 3 * WIDTH, 5 * HEIGHT);
				}
			} else {
				if (lastDirection == Player.RIGHT) {
					graphics.drawImage(image, x, y, x + WIDTH, y + HEIGHT, spritePos * WIDTH, HEIGHT,
							spritePos * WIDTH + WIDTH, 2 * HEIGHT);
				} else {
					graphics.drawImage(image, x, y, x + WIDTH, y + HEIGHT, spritePos * WIDTH, 0,
							spritePos * WIDTH + WIDTH, HEIGHT);
				}
			}
		} else {
			if (!(left || right || up || down)) {
				// standing
				if (lastDirection == Player.RIGHT) {
					graphics.drawImage(image, x, y, x + WIDTH, y + HEIGHT, WIDTH, 4 * HEIGHT, 2 * WIDTH, 5 * HEIGHT);
				} else {
					graphics.drawImage(image, x, y, x + WIDTH, y + HEIGHT, 0, 4 * HEIGHT, WIDTH, 5 * HEIGHT);
				}
			} else {
				if (lastDirection == Player.RIGHT) {
					graphics.drawImage(image, x, y, x + WIDTH, y + HEIGHT, spritePos * WIDTH, 2 * HEIGHT,
							spritePos * WIDTH + WIDTH, 3 * HEIGHT);
				} else {
					graphics.drawImage(image, x, y, x + WIDTH, y + HEIGHT, spritePos * WIDTH, 3 * HEIGHT,
							spritePos * WIDTH + WIDTH, 4 * HEIGHT);
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