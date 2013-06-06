package entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class BasicEntity{
	int x, y, width, height;
	Image image;
	String name;
	
	public BasicEntity(String name, Image image, int x, int y, float rotation) {	
		this.x = x;
		this.y = y;
		this.name = name;
		this.image = image;	
		this.width = image.getWidth();
		this.height = image.getHeight();
		image.setRotation(rotation);
	}

	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void render(GUIContext c, Graphics g) throws SlickException {
			g.drawImage(image, x, y);
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;		
	}
	
	public boolean checkForCollission(BasicEntity entity) {
		return false;
	}
}
