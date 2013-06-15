package player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import drawObject.DrawManager;
import entity.EntityHandler;
import events.FisherArrives;
import main.Prototype;
import navigation.NavigationManager;

public class Villager extends Player {

	// movement
	private boolean left, right, up, down;
	private int lastDirection;
	private int spritePos;
	/**
	 * x and y position on the map starting with 0,0 in the top-left corner.
	 */
	private int x, y;
	private long lastSpriteUpdate;
	private long lastMoveUpdate;
	private NavigationManager navManager;

	// agent
	/**
	 * normal agent.
	 */
	private Image image;
	/**
	 * motivation determines the villager's state.
	 */
	private double motivation;
	/**
	 * villager's name
	 */
	private String name;
	private int state;
	private Fisher fisher;

	//states
	public static final int FISHER = 0;
	public static final int PUB = 1;
	public static final int SLEEPING = 2;
	public static final int FISHING = 3;

	private EntityHandler entities;

	public Villager(int x, int y, NavigationManager navManager) {
		super(x, y);

		try {
			this.image = new Image("img/player.png");
			this.fisher = new Fisher();
		} catch (SlickException e) {
			e.printStackTrace();
		}

		// movement
		lastDirection = RIGHT;
		left = right = up = down = false;
		this.x = x;
		this.y = y;

		spritePos = 0;

		lastSpriteUpdate = System.currentTimeMillis();
		lastMoveUpdate = System.currentTimeMillis();

		this.motivation = (Math.random()*3);
		this.navManager = navManager;

		// Determine the initial state
		state = (int) Math.round(motivation);
		switch(state) {
		case FISHER:
			fishing();
			break;
		case PUB:
			pub();
			break;
		case SLEEPING:
			sleeping();
			break;
		}
	}

	/**
	 * State fishing. Changes the agent's appearance and makes him move to the shore.
	 */
	private void fishing() {
		// go to fishing spot
		int x = 65;
		int y = (int) (Math.random() * Prototype.getHeight());
		this.navManager.addMovement(this, new FisherArrives(), x, y);
	}

	private void pub() {
		// go to the pub
		int x = 770;
		int y = 260;
		this.navManager.addMovement(this, null, x, y);
		System.out.println(this.name + " is going to the pub now.");
	}

	private void sleeping() {
		System.out.println("sleeping, " + motivation);
		System.out.println(this.name + " is going to sleep now.");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		switch (state) {
			case FISHER:
				Fisher.renderWalkingFisher(graphics, left, right, up, down, x, y, lastDirection, spritePos);
				break;
			case FISHING:
				Fisher.renderFishingFisher(graphics, x, y);
				break;
			default:
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
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}
}