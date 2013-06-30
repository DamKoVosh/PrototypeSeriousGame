package godKI;

import main.Prototype;
import conversation.ConversationManager;
import player.Player;
import player.Villager;

public class DecissionTree {
	private long lastMoveUpdate;
	private Player player;
	private ConversationManager conMan;
	private boolean fireSet;
	
	public DecissionTree(Player player) {
		lastMoveUpdate = System.currentTimeMillis();
		this.player = player;
		conMan = ConversationManager.getInstance();
		fireSet = false;
	}
	
	public void update() {
		long now = System.currentTimeMillis();
		if ((now - lastMoveUpdate) >= 10000) {
			lastMoveUpdate = now;
			if (player.getTalked() >= 3 || player.getInventar().size() >= 2) {
				if (conMan.getThreadsRead() > 10 || player.getInventar().size() >= 2) {
					if (conMan.isFireFighter() || player.getInventar().size() >= 2) {
						if (!fireSet) {
							setFire();
						} else {
							enableNewTalkStrings();
						}
					}
				}
			} else {
				if (player.getMouseClicked() > 30) {
					giveAction();
				} else {
					giveHint();
				}
			}
		}
	}

	private void giveAction() {
		System.out.println("Action!!");
	}

	private void giveHint() {
		System.out.println("Hint!!");
	}

	private void enableNewTalkStrings() {
		System.out.println("new Content!!");
	}

	public void setFire() {
		fireSet = true;
		System.out.println("FIRE!!");
		for (Villager villager : Prototype.getVillagers()) {
			villager.setState(Villager.FIRE);
		}
	}
}