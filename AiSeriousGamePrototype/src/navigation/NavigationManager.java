package navigation;

import java.util.ArrayList;

import org.lwjgl.util.Point;
import org.newdawn.slick.Graphics;

import entity.EntityHandler;
import player.Player;

public class NavigationManager {
	private EntityHandler entitys;
	private ArrayList<ArrayList<Boolean>> navMesh;
	private int mapWidth, mapHeight;
	private ArrayList<Player> playerList;
	private ArrayList<ArrayList<Point>> playerPathes;
	private final int GRID_SIZE = 20;
	
	public NavigationManager(EntityHandler entitys, int mapWidth, int mapHeight) {
		this.entitys = entitys;
		this.navMesh = new ArrayList<>();
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.playerList = new ArrayList<>();
		this.playerPathes = new ArrayList<>();
		
		createNavMesh();
	}
	
	public void addMovement(Player player, int x, int y) {
		ArrayList<Point> path = findPath(new Point(player.getFootX() / GRID_SIZE, player.getFootY() / GRID_SIZE), new Point (x / GRID_SIZE, y / GRID_SIZE));
		if (this.playerList.indexOf(player) != -1) {
			this.playerPathes.set(this.playerList.indexOf(player), path);
		} else if (path.size() > 1) {
			this.playerList.add(player);
			this.playerPathes.add(path);
		}
	}
	
	public void update() {
		for (int i = 0; i < playerList.size(); i++) {			
			Player player = playerList.get(i);
			
			if (player.getFootX() == playerPathes.get(i).get(0).getX() * GRID_SIZE 
					&& player.getFootY() == playerPathes.get(i).get(0).getY() * GRID_SIZE) {
				this.playerPathes.get(i).remove(0);

				
				if (this.playerPathes.get(i).isEmpty()) {
					this.playerList.remove(i);
					this.playerPathes.remove(i);
					
					player.releaseDirection(Player.RIGHT);
					player.releaseDirection(Player.LEFT);
					player.releaseDirection(Player.UP);
					player.releaseDirection(Player.DOWN);
					
					break;
				}
			}
			
			if (player.getFootX() > playerPathes.get(i).get(0).getX() * GRID_SIZE) {
				player.setDirection(Player.LEFT);
			} else if (player.getFootX() < playerPathes.get(i).get(0).getX() * GRID_SIZE) {
				player.setDirection(Player.RIGHT);
			} else if (player.getFootX() == playerPathes.get(i).get(0).getX() * GRID_SIZE) {
				player.releaseDirection(Player.RIGHT);
				player.releaseDirection(Player.LEFT);
			}
			
			if (player.getFootY() > playerPathes.get(i).get(0).getY() * GRID_SIZE) {
				player.setDirection(Player.UP);
			} else if (player.getFootY() < playerPathes.get(i).get(0).getY() * GRID_SIZE) {
				player.setDirection(Player.DOWN);
			} else if (player.getFootY() == playerPathes.get(i).get(0).getY() * GRID_SIZE) {
				player.releaseDirection(Player.UP);
				player.releaseDirection(Player.DOWN);
			}
		}
	}
	
	private void createNavMesh() {
		
		for (int i = 0; i < mapWidth / GRID_SIZE; i++) {
			ArrayList<Boolean> row = new ArrayList<>();
			for (int j = 0; j < mapHeight / GRID_SIZE; j++) {
				if (entitys.getEntity(i * GRID_SIZE, j * GRID_SIZE) != null || i == 0 || j == 0) {
					row.add(false);
				} else {
					row.add(true);
				}
			}
			navMesh.add(row);
		}
	}
	
	public void rendermesh(Graphics g) {
		for (int i = 0; i < navMesh.size(); i++) {
			for (int j = 0; j < navMesh.get(i).size(); j++) {
				if (navMesh.get(i).get(j)) {
					g.fillOval(i * GRID_SIZE, j * GRID_SIZE, 2, 2);
				}
			}
		}
		if (!this.playerPathes.isEmpty()) {
			for (int i = 0; i < playerPathes.get(0).size(); i++) {
				g.fillOval(playerPathes.get(0).get(i).getX() * GRID_SIZE, playerPathes.get(0).get(i).getY() * GRID_SIZE, 5, 5);
			}
		}
	}
	
	private ArrayList<Point> findPath(Point start, Point end) {
		ArrayList<Point> path = new ArrayList<>();
		path.add(start);
		findPathHelp(path, end);
		return path;
	}
	
	private ArrayList<Point> findPathHelp(ArrayList<Point> path, Point end) {
		Point lastItem = path.get(path.size() - 1);
		if (lastItem.getX() == end.getX() && lastItem.getY() == end.getY()) {
			return path;
		}
		int newPointX = 0;
		int newPointY = 0;
		if (lastItem.getX() > end.getX()) {
			newPointX = lastItem.getX() - 1;
		} else if (lastItem.getX() == end.getX()) {
			newPointX = lastItem.getX();
		} else {
			newPointX = lastItem.getX() + 1;
		}
		
		if (lastItem.getY() > end.getY()) {
			newPointY = lastItem.getY() - 1;
		} else if (lastItem.getY() == end.getY()) {
			newPointY = lastItem.getY();
		} else {
			newPointY = lastItem.getY() + 1;
		}
		if (navMesh.get(newPointX).get(newPointY)) {
			path.add(new Point(newPointX, newPointY));
			findPathHelp(path, end);
		} else {
			
		}
		return path;
	}
}
