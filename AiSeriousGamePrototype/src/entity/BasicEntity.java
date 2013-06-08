package entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

import player.Player;

public class BasicEntity{
	private int x, y, width, height;
	private Image image;
	private String name;
	
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
	
	public boolean checkForCollission(Player player) {
		int x1 = player.getX();
		int y1 = player.getY() + player.getHeight() / 2;
		int x2 = player.getX() + player .getWidth();
		int y2 = player.getY() + player.getHeight();
		
		if (checkForCollission(x1, y1) || checkForCollission(x1, y2) 
				|| checkForCollission(x2, y1) || checkForCollission(x2, y2)) {
			return true;
		}
		return false;
	}

	public boolean checkForCollission(int x, int y) {
		if (x >= this.x && x <= this.x + this.width 
				&& y >= this.y +  this.height / 2 && y <= this.y + this.height) {
			return true;
		}
		return false;
	}
}
