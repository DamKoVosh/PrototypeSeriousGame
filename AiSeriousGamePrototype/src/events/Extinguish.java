package events;

import player.Player;
import player.Villager;

public class Extinguish extends Event {

	@Override
	public void activateEvent(Player player) {
		if (player.isVillager()) {
			Villager villager = (Villager) player;
			villager.getFireFighter().toggleBucket();
		}
	}
}