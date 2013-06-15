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
	private static int spritePos = 0;
	private static int spritePosWaiting = 0;
	private static boolean wait = true;

	public Fisher () {
		try {
			fisher = new Image("img/fisher.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public static void renderWalkingFisher(Graphics graphics, boolean left, boolean right, boolean up, boolean down,
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

	public static void renderFishingFisher(Graphics graphics, int x, int y) {
			graphics.drawImage(fisher, x, y, x + fisherWidth, y + fisherHeight, (Fisher.spritePos) * fisherWidth, fisherHeight,
					(Fisher.spritePos) * fisherWidth + fisherWidth, fisherHeight * 2);
			Fisher.spritePos++;
			if (Fisher.spritePos > 12) {
				/*if (Fisher.spritePosWaiting < 10) {
					Fisher.spritePos = 10;
					Fisher.spritePosWaiting++;
				} else {*/
					Fisher.spritePos = 0;
			}

	}
}