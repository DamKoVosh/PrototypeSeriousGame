package entity;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

import events.EmptyEvent;
import events.HouseEvent;

public class EntityHandler {
	private ArrayList<BasicEntity> entitys;
	
	public EntityHandler(GUIContext container) {
		entitys = new ArrayList<>();
		addEntitys(container);
	}
	
	private void addEntitys(GUIContext container) {
		try {
			this.entitys.add(new BasicEntity("house1", new Image("img/house.png"), new HouseEvent(), 150, 270, 0));
			this.entitys.add(new BasicEntity("house2", new Image("img/house.png"), new HouseEvent(), 320, 80, 0));
			this.entitys.add(new BasicEntity("house3", new Image("img/house.png"), new HouseEvent(), 550, 30, 0));
			this.entitys.add(new BasicEntity("house4", new Image("img/house.png"), new HouseEvent(), 550, 500, 0));
			this.entitys.add(new BasicEntity("house5", new Image("img/house.png"), new HouseEvent(), 320, 450, 0));
			this.entitys.add(new BasicEntity("house6", new Image("img/house.png"), new HouseEvent(), 780, 80, 0));
			this.entitys.add(new BasicEntity("house7", new Image("img/house.png"), new HouseEvent(), 950, 270, 0));
			this.entitys.add(new BasicEntity("house8", new Image("img/house.png"), new HouseEvent(), 780, 450, 0));
			this.entitys.add(new BasicEntity("sea", new Image("img/sea.png"), new EmptyEvent(), 0, -768, 0));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	

	public void render(GameContainer container, Graphics graphics) throws SlickException {
		for (int i = 0; i < entitys.size(); i++) {
			entitys.get(i).render(container, graphics);
		}
	}

	public BasicEntity getEntity(int x, int y) {
		BasicEntity entity = null;
		for (int i = 0; i < entitys.size(); i++) {
			if (entitys.get(i).checkForCollission(x, y)) {
				entity = entitys.get(i);
				break;
			}
		}
		return entity;
	}
}
