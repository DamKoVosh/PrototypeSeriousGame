package events;

import player.Player;
import navigation.Fire;

public class FireFighting extends Event {

	@Override
	public void activateEvent(Player player) {
		Fire.registerFireFighter(player);
	}

}
