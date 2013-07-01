package events;

import player.Player;
import player.Villager;

public class FireFighting extends Event {

	@Override
	public void activateEvent(Player player) {
		player.getPrototype().getGod().getFire().registerFireFighter(player);
		if (player.isVillager()) {
			Villager villager = (Villager) player;
			villager.getFireFighter().toggleBucket();
		}
	}
}