package godKI;

import player.Villager;
import events.EnterHouse;
import events.Extinguish;
import events.FireFighting;
import events.FisherArrives;
import main.Prototype;
import navigation.NavigationManager;

public class StateMachine {

	private NavigationManager navManager;
	
	public StateMachine (NavigationManager navManager) {
		this.navManager = navManager;
	}

	public void talking(Villager villager) {
		this.navManager.deleteMovement(villager);
	}

	/**
	 * State fishing. Changes the agent's appearance and makes him move to the shore.
	 */
	public void fishing(Villager villager) {
		// go to fishing spot
		int x = 65;
		int y = (int) (Math.random() * Prototype.getHeight());
		this.navManager.addMovement(villager, new FisherArrives(), x, y);
	}

	public void pub(Villager villager) {
		// go to the pub
		int x = 775;
		int y = 255;
		this.navManager.addMovement(villager, new EnterHouse(), x, y);
	}

	/**
	 * The villager goes to one of the houses and enters them.
	 */
	public void sleeping(Villager villager) {
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
		this.navManager.addMovement(villager, new EnterHouse(), x, y);
	}

	public void fire(Villager villager) {
		int x, y;
		this.navManager.deleteMovement(villager);
		villager.setVisible(true);
		if (villager.getFootY() < 320) {
			// from north
			x = 580;
			y = 390;
		} else if (villager.getFootY() < 550) {
			// from west
			x = 530;
			y = 430;
		} else {
			// from south
			x = 580;
			y = 480;
		}

		this.navManager.addMovement(villager, new FireFighting(), x, y);
	}

	public void fireFighting(Villager villager) {
		this.navManager.addMovement(villager, new Extinguish(), 210, 470);
		System.out.println(villager.getName() + " is going to fight the fire now.");
	}
}