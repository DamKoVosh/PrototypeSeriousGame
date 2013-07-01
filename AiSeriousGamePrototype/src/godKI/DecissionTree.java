package godKI;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import navigation.Fire;
import main.Prototype;
import conversation.ConversationManager;
import entity.EntityHandler;
import entity.FireEntity;
import events.EmptyEvent;
import player.Player;
import player.Villager;

public class DecissionTree {
	private long lastMoveUpdate;
	private Player player;
	private ConversationManager conMan;
	private boolean fireSet;
	private Fire fire;
	private EntityHandler entities;
	
	public DecissionTree(Player player, EntityHandler entities) {
		lastMoveUpdate = System.currentTimeMillis();
		this.player = player;
		conMan = ConversationManager.getInstance();
		fireSet = false;
		this.entities = entities;
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

	public Fire setFire() {
		setFire(165, 299);

		return this.fire;
	}

	public Fire setFire(int fireX, int fireY) {
		fire = new Fire(fireX, fireY);
		try {
			this.entities.addEntity(new FireEntity("fire1",  new Image("img/flamesFix.png"), new EmptyEvent(), 158, 380, 0));
			this.entities.addEntity(new FireEntity("fire2",  new Image("img/flamesFix.png"), new EmptyEvent(), 230, 380, 0));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		fireSet = true;
		System.out.println("FIRE!!");
		for (Villager villager : Prototype.getVillagers()) {
			villager.setState(Villager.FIRE);
		}

		return this.fire;
	}

	public Fire getFire() {
		return this.fire;
	}

	public boolean getIsBurning() {
		return this.fireSet;
	}
}