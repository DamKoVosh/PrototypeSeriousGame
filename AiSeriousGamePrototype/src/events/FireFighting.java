package events;

import player.Player;
import player.Villager;

public class FireFighting extends Event {

	@Override
	public void activateEvent(Player player) {
		if (player.isVillager()) {
			Villager villager = (Villager) player;
			villager.getFireFighter().toggleBucket();
			villager.setState(Villager.FIREFIGHTING);
			System.out.println(villager.getName() + " has been to the well.");
		}
	}
}