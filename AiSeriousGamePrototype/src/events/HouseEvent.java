package events;

import player.Player;

public class HouseEvent extends Event{

	@Override
	public void activateEvent(Player player) {
		System.out.println("player entered " + this.entity.getName());		
	}

}
