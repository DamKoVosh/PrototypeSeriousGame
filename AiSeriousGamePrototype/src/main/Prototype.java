package main;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Prototype extends BasicGame{
	Image background;
	
	public Prototype(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer (new Prototype("Board Game Designer"));
        container.setDisplayMode(1280, 720, false);
        container.setClearEachFrame(true);
        container.setMinimumLogicUpdateInterval(33);
        container.start();
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.drawImage(background, 0, 0);
	}

	@Override
	public void init(GameContainer c) throws SlickException {
		background = new Image("img/background.jpg");
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
