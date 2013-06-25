package events;

import player.Player;
import player.Villager;

public class HouseEvent extends Event {

	@Override
	public void activateEvent(Player player) {
		Villager villager = (Villager) player;
		System.out.println("foot: " + player.getFootY() + " and " + this.entity.getName() + " " + (this.entity.getY() + this.entity.getWidth()));
		if (player.getFootY() > this.entity.getY() + this.entity.getWidth()) {
			System.out.println("player entered " + this.entity.getName());
			villager.setState(Villager.INSIDE);
		} else {
			System.out.println(villager.getName() + " is not at the entrance.");
		}
	}
}