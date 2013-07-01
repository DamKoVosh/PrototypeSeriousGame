package player;
import godKI.StateMachine;
import main.Prototype;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Villager extends Player {

	// agent
	/**
	 * motivation determines the villager's initial state.
	 */
	private double motivation;
	/**
	 * villager's name
	 */
	private String name;
	private int state;
	private int saveState;
	private Fisher fisher;
	private FireFighter fireFighter;
	private boolean visible = true;
	private StateMachine states;

	//states
	public static final int FISHER = 0;
	public static final int PUB = 1;
	public static final int SLEEPING = 2;
	public static final int FISHING = 3;
	public static final int TALKING = 4;
	public static final int INSIDE = 5;
	public static final int FIRE = 6;
	public static final int FIREFIGHTING = 7;

	public Villager(String name, int x, int y, Prototype prototype, StateMachine states) {
		super(x, y, prototype);
		this.name = name;

		this.fisher = new Fisher();
		this.fireFighter = new FireFighter();

		this.motivation = Math.random();
		this.states = states;

		saveState = -1;

		// Determine the initial state
		state = (int) Math.floor(motivation * 3);
		setState(state);
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
			case FIREFIGHTING:
			case FIRE:
				fireFighter.renderWalking(graphics, left, right, up, down, x, y, lastDirection);
				break;
			default:
				super.render(container, graphics);
			}
		} else {
			graphics.drawImage(image, x, y, x, y, 1, 1, 1, 1);
		}

		computeState();
	}

	private void computeState() {
		if (this.state != FIRE && this.state != FIREFIGHTING && this.state != TALKING) {
			long now = System.currentTimeMillis();
			if (now - this.lastMoveUpdate >= 500) {
				if (this.state == FISHING) {
					this.motivation -= 0.02 - (int) (Math.random() * 0.01);
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

				this.lastMoveUpdate = now;
			}
		}
	}

	public int getState() {
		return this.state;
	}

	/**
	 * Call this method to change a villager's state.
	 * @param state
	 */
	public void setState(int state) {
		switch(state) {
		case FISHER:
			this.state = state;
			states.fishing(this);
			break;
		case PUB:
			this.state = state;
			states.pub(this);
			break;
		case SLEEPING:
			this.state = state;
			states.sleeping(this);
			break;
		case FIRE:
			this.saveState = this.state;
			this.state = state;
			states.fire(this);
			break;
		case TALKING:
			this.saveState = this.state;
			this.state = state;
			states.talking(this);
			break;
		case FISHING:
			this.state = state;
			break;
		case FIREFIGHTING:
			this.state = state;
			states.fireFighting(this);
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

	public FireFighter getFireFighter() {
		return this.fireFighter;
	}
}