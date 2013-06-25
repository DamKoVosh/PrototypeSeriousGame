package events;

import player.Player;
import player.Villager;

public class FisherArrives extends Event {

	@Override
	public void activateEvent(Player player) {
		Villager villager = (Villager) player;
		if (villager.getState() == Villager.FISHER) {
			villager.setState(Villager.FISHING);
		}
	}
}