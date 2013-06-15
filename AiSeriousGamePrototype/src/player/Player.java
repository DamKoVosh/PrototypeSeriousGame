package player;

import main.Prototype;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import drawObject.DrawAbleObject;
import drawObject.DrawManager;

public class Player extends DrawAbleObject{
	private Image image;
	private boolean left, right, up, down;
	private int lastDirection;
	private int spritePos;
	private int x, y;
	private long lastSpriteUpdate;
	private long lastMoveUpdate;

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;

	public Player(int x, int y) {
		super(y + 75);
		try {
			this.image = new Image("img/player.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		lastDirection = RIGHT;
		left = right = up = down = false;
		this.x = x;
		this.y = y;

		spritePos = 0;

		lastSpriteUpdate = System.currentTimeMillis();
		lastMoveUpdate = System.currentTimeMillis();
	}

	public void render(GameContainer container, Graphics graphics) throws SlickException {
		if (!(left || right || up || down)) {
			if (lastDirection == RIGHT) {
				graphics.drawImage(image, x, y, x + 52, y + 75, 0, 151, 52, 225);
			} else {
				graphics.drawImage(image, x, y, x + 52, y + 75, 53, 151, 105, 225);
			}
		} else {
			if (lastDirection == RIGHT) {
				graphics.drawImage(image, x, y, x + 52, y + 75, spritePos * 52, 0, spritePos * 52 + 52, 75);
			} else {
				graphics.drawImage(image, x, y, x + 52, y + 75, spritePos * 52, 76, spritePos * 52 + 52, 150);
			}
		}
	}

	public void setDirection(int direction) {
		if (direction == LEFT && !left) {
			spritePos = 0;
			left = true;
			right = false;
			lastDirection = LEFT;
		} else if (direction == RIGHT && ! right) {
			spritePos = 0;
			right = true;
			left = false;
			lastDirection = RIGHT;
		} else if (direction == UP && !up) {
			up = true;
			down = false;
		} else if (direction == DOWN && !down) {
			down = true;
			up = false;
		}
	}
	
	public void releaseDirection(int direction) {
		if (direction == LEFT) {
			left = false;
		} else if (direction == RIGHT) {
			right = false;
		} else if (direction == UP) {
			up = false;
		} else if (direction == DOWN) {
			down = false;
		}
		if (!(left || right || up || down)) {
			spritePos = 0;		
		}
	}
	
	public void update() {
		if (left || right || up || down) {
			long now = System.currentTimeMillis();
			if ((now - lastMoveUpdate) >= 10) {
				lastMoveUpdate = now;
				if (left && x > 0) {
					x--;
				}
				if (right && x < Prototype.getWidth()) {
					x++;
				} 
				if (up && y > 0) {
					y--;
				} 
				if (down && y < Prototype.getHeight()) {
					y++;
				} 
			}

			if ((now - lastSpriteUpdate) >= 150) {
				lastSpriteUpdate = now;
				spritePos ++;
				if (spritePos > 5) {
					spritePos = 0;
				}
			}
			this.setZBuffer(getFootY());
			DrawManager.getInstanceOf().ChangeZBuffer(this);
		}
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return 52;
	}
	
	public int getHeight() {
		return 75;
	}
	
	public int getFootX() {
		return this.getX() + this.getWidth() / 2;
	}
	
	public int getFootY() {
		return this.getY() + this.getHeight();
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
