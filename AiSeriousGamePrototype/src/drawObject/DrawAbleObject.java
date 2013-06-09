package drawObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class DrawAbleObject {
	private int zBuffer;
	
	public DrawAbleObject(int zBuffer) {
		this.zBuffer = zBuffer;
		DrawManager.getInstanceOf().addDrawObject(this);
	}

	public int getZBuffer() {
		return zBuffer;
	}
	
	public void setZBuffer(int z) {
		this.zBuffer = z;
	}
	
	public abstract void render(GameContainer container, Graphics graphics) throws SlickException;
}
