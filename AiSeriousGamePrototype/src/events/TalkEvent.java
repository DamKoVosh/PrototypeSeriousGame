package events;

import conversation.ConversationManager;
import player.Player;
import player.Villager;

public class TalkEvent extends Event {
	private Villager talkPartner;
	
	@Override
	public void activateEvent(Player player) {
		System.out.println("starting to Talk to " + talkPartner.getName());
		ConversationManager.getInstance().initConversation(talkPartner.getName(), player);
		player.setTalking(true);
		player.increaseTalked();
	}
	
	public void setPartner(Villager villager) {
		talkPartner = villager;
	}
}