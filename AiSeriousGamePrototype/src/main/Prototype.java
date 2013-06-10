package main;
import navigation.NavigationManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import drawObject.DrawManager;

import player.Player;

import entity.BasicEntity;
import entity.EntityHandler;
import events.Event;



public class Prototype extends BasicGame{
	private Image background;
	private EntityHandler entitys;
	private Player player;
	private NavigationManager navigationManager;
	
	public Prototype(String title) {
		super(title);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer (new Prototype("Board Game Designer"));
        container.setDisplayMode(1280, 720, false);
        container.setClearEachFrame(true);
        container.setMinimumLogicUpdateInterval(15);
        container.start();
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.drawImage(background, 0, 0);
		
		DrawManager.getInstanceOf().render(container, graphics);
	}

	@Override
	public void init(GameContainer c) throws SlickException {
		background = new Image("img/background.jpg");
		entitys = new EntityHandler(c);
		navigationManager = new NavigationManager(entitys, 1280, 720);
		
		player = new Player (600, 300);
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		navigationManager.update();
		player.update();
	}
	
	@Override
	public void keyPressed(int key, char c) {
		switch(key) {
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
		if (button == 0) {
			Event event = null;
			BasicEntity entity = entitys.getEntity(x, y);
			if (entity != null) {
				event = entity.getEvent();
			}
			navigationManager.addMovement(player, event, x, y);
		}
	}
}
