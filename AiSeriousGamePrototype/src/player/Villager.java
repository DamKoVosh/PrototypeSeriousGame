package player;
import entity.EntityHandler;
import events.AgentArrives;
import main.Prototype;
import navigation.NavigationManager;

public class Villager extends Player {
	private double motivation;
	private NavigationManager navManager;
	private String name;
	private EntityHandler entities;
	
	public Villager(int x, int y, NavigationManager navManager) {
		super(x, y);

		this.motivation = (Math.random()*3);
		this.navManager = navManager;

		switch((int) Math.round(Math.random()*3)) {
		case 0:
			fishing();
			break;
		case 1:
			pub();
			break;
		case 2:
			sleeping();
			break;
		}
	}

	private void fishing() {
		// go to fishing spot
		int x = 50;
		int y = (int) Math.random()*Prototype.getHeight();
		this.navManager.addMovement(this, new AgentArrives(), x, y);
		System.out.println(this.name + " is going to catch some fish now.");
	}

	private void pub(){
		// go to the pub
		int x = 770;
		int y = 260;
		this.navManager.addMovement(this, new AgentArrives(), x, y);
		System.out.println(this.name + " is going to the pub now.");
	}

	private void sleeping() {
		System.out.println("sleeping, " + motivation);
		System.out.println(this.name + " is going to sleep now.");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
