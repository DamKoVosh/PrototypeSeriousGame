package events;

import player.Player;
import player.Villager;

public class AgentArrives extends Event{

	@Override
	public void activateEvent(Player player) {
		System.out.println(((Villager) player).getName() + " arrives.");
	}
}