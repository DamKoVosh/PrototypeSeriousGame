package events;

import player.Player;
import player.Villager;

public class FireFighting extends Event {

	@Override
	public void activateEvent(Player player) {
		if (player.isVillager()) {
			Villager villager = (Villager) player;
			villager.setState(Villager.FIREFIGHTING);
			villager.getFireFighter().toggleBucket();
		}
	}
}