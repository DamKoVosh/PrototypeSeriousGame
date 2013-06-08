package events;

import entity.BasicEntity;
import player.Player;

public abstract class Event {
	protected BasicEntity entity;
	
	public void setEntity(BasicEntity entity) {
		this.entity = entity;
	}
	
	public abstract void activateEvent(Player player);
}
