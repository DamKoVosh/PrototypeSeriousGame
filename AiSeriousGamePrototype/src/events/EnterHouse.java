package events;

import entity.BasicEntity;
import player.Player;
import player.Villager;

public class EnterHouse extends Event {
	BasicEntity entity;

	public EnterHouse(BasicEntity entity) {
		this.entity = entity;
	}

	@Override
	public void activateEvent(Player player) {
		Villager villager = (Villager) player;
		if (player.getFootY() > (this.entity.getY() + this.entity.getHeight())) {
			villager.setVisible(false);
		}
	}
}