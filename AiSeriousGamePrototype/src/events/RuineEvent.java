package events;

import items.Item;
import player.Player;

public class RuineEvent extends Event {
	private boolean searched;
	
	public RuineEvent() {
		searched = false;
	}
	
	@Override
	public void activateEvent(Player player) {
		if (!searched) {
			searched = true;
			player.getInventar().addItem(new Item("coin"));
		}
	}

}
