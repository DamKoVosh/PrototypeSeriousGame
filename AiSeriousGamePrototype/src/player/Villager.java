package player;
import main.Prototype;
import navigation.NavigationManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entity.EntityHandler;
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
	private static Fisher fisher;

	//states
	public static final int FISHER = 0;
	public static final int PUB = 1;
	public static final int SLEEPING = 2;
	public static final int FISHING = 3;
	public static final int TALKING = 4;

	private EntityHandler entities;

	public Villager(int x, int y, NavigationManager navManager) {
		super(x, y);
		Villager.fisher = new Fisher();

		this.motivation = (Math.random()*3);
		this.navManager = navManager;

		saveState = -1;
		
		// Determine the initial state
		state = (int) Math.round(motivation);
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
				super.render(container, graphics);
		}
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
}
