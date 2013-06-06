package Entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class BasicEntity extends AbstractComponent{
	int x, y, width, height;
	Image image;
	
	public BasicEntity(GUIContext container, Image image, int x, int y) {
		super(container);	
		this.x = x;
		this.y = y;
		this.image = image;	
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void render(GUIContext c, Graphics g) throws SlickException {
			g.drawImage(image, x, y);
	}

	@Override
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;		
	}
	
	public boolean checkForCollission(BasicEntity entity) {
		return false;
	}
}
