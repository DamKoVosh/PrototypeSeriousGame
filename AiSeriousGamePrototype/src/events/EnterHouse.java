package events;

import player.Player;
import player.Villager;

public class EnterHouse extends Event {

	@Override
	public void activateEvent(Player player) {
		Villager villager = (Villager) player;
		villager.setVisible(false);
	}
}