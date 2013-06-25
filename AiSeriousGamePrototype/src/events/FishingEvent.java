package events;

import items.Item;
import player.Player;

public class FishingEvent extends Event {
private boolean searched;
	
	public FishingEvent() {
		searched = false;
	}
	
	@Override
	public void activateEvent(Player player) {
		if (!searched && !player.isVillager()) {
			searched = true;
			player.getInventar().addItem(new Item("feuerzeug"));
		}
	}
}
