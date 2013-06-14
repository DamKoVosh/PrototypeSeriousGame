package player;
import navigation.NavigationManager;

public class Villager extends Player {
	private int motivation;

	public Villager(int x, int y) {
		super(x, y);

		motivation = (int) Math.round(Math.random()*3);

		switch(motivation) {
		case 0:
			working();
		case 1:
			pub();
		case 2:
			sleeping();
		}
	}

	private void working() {
		
	}

	private void pub(){
		
	}

	private void sleeping() {
		
	}
}
