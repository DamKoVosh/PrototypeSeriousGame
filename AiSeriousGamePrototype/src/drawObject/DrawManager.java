package drawObject;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class DrawManager {
	private ArrayList<DrawAbleObject> drawObjects;
	
	private static DrawManager instance;
	
	public static DrawManager getInstanceOf() {
		if (instance == null) {
			instance = new DrawManager();
		}
		
		return instance;
	}
	
	
	private DrawManager() {
		drawObjects = new ArrayList<>();
	}
	
	public void addDrawObject(DrawAbleObject drawObject) {
		if (!this.drawObjects.contains(drawObject)) {
			for (int i = 0; i < this.drawObjects.size(); i++) {
				if (this.drawObjects.get(i).getZBuffer() > drawObject.getZBuffer()) {
					this.drawObjects.add(i, drawObject);
					return;
				}
			}
			this.drawObjects.add(drawObject);
		}
	}
	
	public void ChangeZBuffer(DrawAbleObject drawObject) {
		int index = this.drawObjects.indexOf(drawObject);
		if (index != -1) {
			this.drawObjects.remove(index);
			
			for (int i = 0; i < this.drawObjects.size(); i++) {
				if (this.drawObjects.get(i).getZBuffer() > drawObject.getZBuffer()) {
					this.drawObjects.add(i, drawObject);
					return;
				}
			}
			this.drawObjects.add(drawObject);
		}
	}
	
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		for (int i = 0; i < this.drawObjects.size(); i++) {
			drawObjects.get(i).render(container, graphics);
		}
	}
}
