package main;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import player.Player;

import entity.EntityHandler;



public class Prototype extends BasicGame{
	Image background;
	EntityHandler entitys;
	Player player;
	
	public Prototype(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer (new Prototype("Board Game Designer"));
        container.setDisplayMode(1280, 720, false);
        container.setClearEachFrame(true);
        container.setMinimumLogicUpdateInterval(20);
        container.start();
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.drawImage(background, 0, 0);
		entitys.render(container, graphics);
		player.render(container, graphics);
	}

	@Override
	public void init(GameContainer c) throws SlickException {
		background = new Image("img/background.jpg");
		entitys = new EntityHandler(c);
		
		player = new Player (600, 300);
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
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
		
	}
}
