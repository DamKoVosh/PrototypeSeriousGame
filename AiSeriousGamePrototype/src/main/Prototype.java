package main;
import java.util.ArrayList;

import navigation.NavigationManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import conversation.ConversationManager;

import drawObject.DrawManager;
import player.Inventar;
import player.Player;
import player.Villager;
import entity.BasicEntity;
import entity.EntityHandler;
import events.Event;
import events.TalkEvent;
import godKI.DecissionTree;

public class Prototype extends BasicGame {
	private Image background;
	private EntityHandler entitys;
	private Player player;
	private DecissionTree god;
	
	private Villager talkPartner;
	private static ArrayList<Villager> villagers = new ArrayList<Villager>();
	private NavigationManager navigationManager;
	private boolean showNavMash;
	
	private static AppGameContainer container;
	
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 768;
	private final static boolean FULLSCREEN = false;

	public Prototype(String title) {
		super(title);
	}

	public static void main(String[] args) throws SlickException {
		container = new AppGameContainer (new Prototype("Board Game Designer"));
        container.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
        container.setClearEachFrame(true);
        container.setMinimumLogicUpdateInterval(15);
        container.start();
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.drawImage(background, 0, 0);
		
		DrawManager.getInstanceOf().render(container, graphics);
		player.getInventar().render(container, graphics);
		if (showNavMash) {
			navigationManager.rendermesh(graphics);
		}
		
		if (player.getTalking()) {
			ConversationManager.getInstance().render(container, graphics);
		}
	}

	@Override
	public void init(GameContainer c) throws SlickException {
		background = new Image("img/background.png");
		entitys = new EntityHandler(c);
		navigationManager = new NavigationManager(entitys, WIDTH, HEIGHT);

		player = new Player (500, 600);
		player.addInventar(new Inventar());
		
		String[] names = {"Bernd", "Frank", "Michael", "Tina", "Leon", "Abraham"};
		for (int i = 0; i < 6; i++) {
			villagers.add(new Villager(300 + i*10, 300 + i*10, this.navigationManager));
			villagers.get(i).setName(names[i]);
		}
		god = new DecissionTree(player);
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		if (!player.getTalking()) {
			navigationManager.update();
			player.update();
			for (Villager villager : villagers) {
				villager.update();
			}
		}
		god.update();
	}
	
	@Override
	public void keyPressed(int key, char c) {
		switch(key) {
			case 1:
				if (container.isFullscreen()) {
					try {
						container.setFullscreen(false);
					} catch (SlickException e) {
						e.printStackTrace();
					}
				}
				break;
			case 37:
				showNavMash = !showNavMash;
				break;
			case 87:
				if (!container.isFullscreen()) {
					try {
						container.setFullscreen(true);
					} catch (SlickException e) {
						e.printStackTrace();
					}
				}
				break;
			case 203:
				player.setDirection(Player.LEFT);
				break;
			case 205:
				player.setDirection(Player.RIGHT);
				break;
			case 200:
				player.setDirection(Player.UP);
				break;
			case 208:
				player.setDirection(Player.DOWN);
				break;
			case 38:
				this.god.setFire();
				break;
			default:
				System.out.println(key);
		}
	}
	
	@Override
	public void keyReleased(int key, char c) {
		switch(key) {
			case 203:
				player.releaseDirection(Player.LEFT);
				break;
			case 205:
				player.releaseDirection(Player.RIGHT);
				break;
			case 200:
				player.releaseDirection(Player.UP);
				break;
			case 208:
				player.releaseDirection(Player.DOWN);
				break;
		}
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int count) {
		player.increaseMouseClick();
		if (!player.getTalking()) {
			if (button == 0) {
				if (talkPartner != null) {
					talkPartner.resetState();
				}
				
				Event event = null;
				BasicEntity entity = entitys.getEntity(x, y);
				if (entity != null) {
					event = entity.getEvent();
				} else {
					for (int i = 0; i < villagers.size(); i++) {
						if (villagers.get(i).checkColission(x, y) && villagers.get(i).isVisible()) {
							System.out.println("gehe zu " + villagers.get(i).getName() + " um mit ihm zu sprechen");
							villagers.get(i).setState(Villager.TALKING);
							talkPartner = villagers.get(i);
							TalkEvent talkEvent = new TalkEvent();
							talkEvent.setPartner(talkPartner);
							event = talkEvent;
							break;
						}
					}
				}
				navigationManager.addMovement(player, event, x, y);
			}
		} else {
			if (button == 0 && count == 1) {
				ConversationManager.getInstance().handleClick(x, y);
			}
		}
	}
	
	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		if (player.getTalking()) {
			ConversationManager.getInstance().handleMouseMove(newX, newY);
		}
	}

	public static int getHeight() {
		return Prototype.HEIGHT;
	}

	public static int getWidth() {
		return Prototype.WIDTH;
	}

	public static ArrayList<Villager> getVillagers() {
		return Prototype.villagers;
	}
}