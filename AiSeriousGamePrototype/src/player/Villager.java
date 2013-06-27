package player;
import main.Prototype;
import navigation.NavigationManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import events.EnterHouse;
import events.FisherArrives;

public class Villager extends Player {
	// movement
	private NavigationManager navManager;

	// agent
	/**
	 * motivation determines the villager's state.
	 */
	private double motivation;
	/**
	 * villager's name
	 */
	private String name;
	private int state;
	private int saveState;
	private Fisher fisher;
	private boolean visible = true;

	//states
	public static final int FISHER = 0;
	public static final int PUB = 1;
	public static final int SLEEPING = 2;
	public static final int FISHING = 3;
	public static final int TALKING = 4;
	public static final int INSIDE = 5;
	public static final int FIRE = 6;

	public Villager(int x, int y, NavigationManager navManager) {
		super(x, y);
		this.fisher = new Fisher();

		this.motivation = Math.random();
		this.navManager = navManager;

		saveState = -1;

		// Determine the initial state
		state = (int) Math.floor(motivation * 3);
		setState(state);
	}

	private void talking() {
		this.navManager.deleteMovement(this);
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
		int x = 775;
		int y = 255;
		this.navManager.addMovement(this, new EnterHouse(), x, y);
	}

	/**
	 * The villager goes to one of the houses and enters them.
	 */
	private void sleeping() {
		int x, y;
		if (Math.random() < 0.5) {
			// dark roof
			x = 220;
			y = 470;
		} else {
			// red roof
			x = 370;
			y = 290;
		}

		this.navManager.addMovement(this, new EnterHouse(), x, y);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		if (this.visible) {
			switch (state) {
			case FISHER:
				fisher.renderWalkingFisher(graphics, left, right, up, down, x, y, lastDirection, spritePos);
				break;
			case FISHING:
				fisher.renderFishingFisher(graphics, x, y);
				break;
			case TALKING:
				if (saveState == FISHER || saveState == FISHING) {
					fisher.renderTalkingFisher(graphics, x, y, lastDirection);
				} else {
					super.render(container, graphics);
				}
				break;
			default:
				super.render(container, graphics);
			}
		} else {
			renderInside(graphics);
		}

		computeState();
	}

	public void computeState() {
		long now = System.currentTimeMillis();
		if (now - this.lastMoveUpdate >= 500) {
			if (this.state != FIRE && this.state != TALKING) {
				if (this.state == FISHING) {
					this.motivation -= 0.02;
					if (this.motivation < 0) {
						this.setState(PUB);
					}
				} else {
					if (this.visible == false) {
						// villager is in a pub or house and recovers
						this.motivation += 0.02;
					}

					if (this.motivation > 1) {
						this.setState(FISHER);
						this.setVisible(true);
					} else if (this.motivation > 0.4 && this.state == PUB) {
						this.setState(SLEEPING);
						this.setVisible(true);
					}
				}
			}

			this.lastMoveUpdate = now;
		}
	}

	public void renderInside(Graphics graphics) {
		graphics.drawImage(image, x, y, x, y, 1, 1, 1, 1);
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		if (state == TALKING) {
			saveState = this.state;
		}
		this.state = state;

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
		case TALKING:
			talking();
			break;
		}
	}

	public void resetState() {
		if (saveState != -1) {
			this.setState(saveState);
		}
	}

	public boolean checkColission(int x, int y) {
		if (x >= this.x && x <= this.x + this.getWidth() && y >= this.y && y <= this.y + this.getHeight()) {
			return true;
		}
		return false;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return this.visible;
	}

	@Override
	public boolean isVillager() {
		return true;
	}

	public long getLastUpdate() {
		return this.lastMoveUpdate;
	}

	public void setLastUpdate(long timestamp) {
		this.lastMoveUpdate = timestamp;
	}
}