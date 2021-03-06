package entity;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

import events.EmptyEvent;
import events.FishingEvent;
import events.HouseEvent;
import events.RuineEvent;

public class EntityHandler {
	private ArrayList<BasicEntity> entitys;

	public EntityHandler(GUIContext container) {
		entitys = new ArrayList<>();
		addEntitys();
	}

	private void addEntitys() {
		try {
			this.entitys.add(new BasicEntity("houseBlack", new Image("img/house1.png"), new HouseEvent(), 155, 279, 0));
			this.entitys.add(new BasicEntity("houseRed", new Image("img/house2.png"), new HouseEvent(), 318, 79, 0));
			this.entitys.add(new BasicEntity("mine", new Image("img/mine.png"), new EmptyEvent(), 550, -11, 0));
			this.entitys.add(new BasicEntity("sawmill", new Image("img/sawmill.png"), new HouseEvent(), 825, 279, 0));
			this.entitys.add(new BasicEntity("mill", new Image("img/mill.png"), new EmptyEvent(), 320, 470, 0));
			this.entitys.add(new BasicEntity("pub", new Image("img/pub.png"), new HouseEvent(), 705, 89, 0));
			this.entitys.add(new BasicEntity("sea", new Image("img/sea.png"), new FishingEvent(), 0, -768, 0));
			this.entitys.add(new WellEntity("well", new Image("img/wellfix.png"), new EmptyEvent(), 530, 349, 0));
			this.entitys.add(new BasicEntity("ruine", new Image("img/ruine.png"), new RuineEvent(), 755, 500, 0));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void addEntity(BasicEntity entity) {
		this.entitys.add(entity);
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

	public BasicEntity getEntity(String name) {
		BasicEntity entity = null;
		for (int i = 0; i < entitys.size(); i++) {
			if (entitys.get(i).getName().equals(name)) {
				entity = entitys.get(i);
				break;
			}
		}
		return entity;
	}

	public void removeEntity(String name) {
		for (int i = 0; i < this.entitys.size(); i++) {
			if (this.entitys.get(i).getName().equals(name)) {
				this.entitys.remove(i);
			}
		}
	}
}