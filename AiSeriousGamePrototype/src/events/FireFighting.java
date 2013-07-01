package events;

import player.Player;

public class FireFighting extends Event {

	@Override
	public void activateEvent(Player player) {
		player.getPrototype().getGod().getFire().registerFireFighter(player);
	}
}