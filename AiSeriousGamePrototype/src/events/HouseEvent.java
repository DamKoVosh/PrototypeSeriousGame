package events;

import player.Player;
import player.Villager;

public class HouseEvent extends Event {

	@Override
	public void activateEvent(Player player) {
		if (player.isVillager()) {

		} else {
			if (player.getFootY() > this.entity.getY() + this.entity.getWidth()) {
				System.out.println("Player entered " + this.entity.getName());
			} else {
				System.out.println("Player is not at the entrance.");
			}
		}
	}
}