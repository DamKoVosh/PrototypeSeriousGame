package navigation;

import java.util.ArrayList;

import player.Player;

public class Fire {

	int fireX, fireY;
	ArrayList<Player> fireFighters = new ArrayList<Player>();

	public Fire(int fireX, int fireY) {
		this.fireX = fireX;
		this.fireY = fireY;

	}

	public void registerFireFighter(Player player) {
		fireFighters.add(player);
	}

	public ArrayList<Player> getFireFighters() {
		return this.fireFighters;
	}
}