package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import drawObject.DrawAbleObject;

import events.Event;

import player.Player;

public class BasicEntity extends DrawAbleObject {
	public int x, y, width, height;
	public Image image;
	public String name;
	public Event event;

	public BasicEntity(String name, Image image, Event event, int x, int y, float rotation) {	
		super(y + image.getHeight() / 2);
		this.x = x;
		this.y = y;
		this.name = name;
		this.event = event;
		this.event.setEntity(this);

		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
		image.setRotation(rotation);
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public String getName() {
		return name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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

	public Event getEvent() {
		return event;
	}

	@Override
	public void render(GameContainer container, Graphics graphics)
			throws SlickException {
		graphics.drawImage(image, x, y);
	}
}