package navigation;

import java.util.ArrayList;

import player.Player;

public class Fire {

	int fireX, fireY;
	int strength = (int) (Math.random() * 100);
	ArrayList<Player> fireFighters = new ArrayList<Player>();

	public Fire(int fireX, int fireY) {
		this.fireX = fireX;
		this.fireY = fireY;
	}

	public int getFireX() {
		return this.fireX;
	}

	public int getFireY() {
		return this.fireY;
	}
}