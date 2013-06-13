package events;

import player.Player;

public class HouseEvent extends Event{

	@Override
	public void activateEvent(Player player) {
		if (player.getFootY() > this.entity.getY() + this.entity.getHeight()) {
			System.out.println("player entered " + this.entity.getName());
		} else {
			System.out.println("player is not at the entrance");
		}
	}
}