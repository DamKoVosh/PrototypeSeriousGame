package conversation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import player.Player;

public class ConversationManager {
	
	private ArrayList<ConversationString> conversation;
	private Player player;
	private final int Y_POS = 135;
	private final int LINE_HEIGHT = 20;
	
	private boolean fireFighter;
	private boolean fisher;
	private boolean villageFriend;
	
	
	private static ConversationManager instance;
	
	public static ConversationManager getInstance() {
		if (instance == null) {
			instance = new ConversationManager();
		}
		return instance;
	}
	
	ConversationManager() {
		conversation = new ArrayList<>();
		fireFighter = false;
		fisher = false;
		villageFriend = false;
	}
	
	public void handleClick(int x, int y) {
		for (int i = 0; i < conversation.size(); i++) {
			if (y > Y_POS + LINE_HEIGHT * i + LINE_HEIGHT && y < Y_POS + LINE_HEIGHT * i + 2*LINE_HEIGHT) {
				conversation.get(i).handleClick(x, y);
				if (!fireFighter && conversation.get(i).getTitle().equals("Wie Löscht man ein Haus?")) {
					this.fireFighter = true;
					this.conversation.remove(conversation.size() - 1);
					this.loadConversation("FireFight");
					this.conversation.add(new ExitString("Bis später.", player));
				} else if (!fisher && conversation.get(i).getTitle().equals("Erzähl mir was übers Fischen")) {
					this.fisher = true;
					this.conversation.remove(conversation.size() - 1);
					this.loadConversation("Fishing");
					this.conversation.add(new ExitString("Bis später.", player));
				}
			} else {
				conversation.get(i).setClicked(false);
			}
		}
	}

	public void handleMouseMove(int newX, int newY) {
		for (int i = 0; i < conversation.size(); i++) {
			if (newY > Y_POS + LINE_HEIGHT * i + LINE_HEIGHT && newY < Y_POS + LINE_HEIGHT * i + 2*LINE_HEIGHT) {
				conversation.get(i).setFocused(true);
			} else {
				conversation.get(i).setFocused(false);
			}
		}
	}
	
	public void initConversation(String name, Player player) {
		this.player = player;
		conversation.clear();
		conversation.add(new ConversationString(name));
		loadConversation("Leon");
		if (fisher) {
			this.loadConversation("Fishing");
		}
		if (fireFighter) {
			this.loadConversation("FireFight");
		}
		conversation.add(new ExitString("Bis später.", player));
	}
	
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(new Color(255,255,255,200));
		graphics.fillRect(0, Y_POS, 1024, 500);
		graphics.setColor(Color.black);
		
		ConversationString clickedString = null;
		for (int i = 0; i < conversation.size(); i++) {
			if (conversation.get(i).isClicked()) {
				clickedString = conversation.get(i);
			}
			if (conversation.get(i).isFocused() && i > 0) {
				graphics.drawRect(0, Y_POS + i * LINE_HEIGHT + LINE_HEIGHT, 1024, LINE_HEIGHT);
			}
			conversation.get(i).render(container, graphics, Y_POS + i * LINE_HEIGHT + LINE_HEIGHT);
		}
		graphics.drawRect(0, Y_POS + conversation.size() * LINE_HEIGHT + LINE_HEIGHT, 1024, 1);
		
		ArrayList<String> text = null ;
		if (clickedString != null && (text = clickedString.getText()) != null) {
			for (int i = 0; i < text.size(); i++) {
				graphics.drawString(text.get(i), 20, Y_POS + conversation.size() * LINE_HEIGHT + 2 * LINE_HEIGHT + i * LINE_HEIGHT);
			}
		}
	}
	
	private void loadConversation(String fileName) {
		FileReader fstream = null;
		BufferedReader in = null;
		try {
			fstream = new FileReader("conversation/" + fileName + ".txt");
			in = new BufferedReader(fstream);
		} catch (IOException e) {}
		try {
			String line = null;
			String section ="";
			while((line = in.readLine()) != null) {
				if (line.length() > 0) {
					if (line.equals("Intro:")) {
						section = new String(line);
					} else if (line.equals("Title:")) {
						section = new String(line);
					} else if (line.equals("Text:")) {
						section = new String(line);
					} else if (section.equals("Intro:") && !section.equals(line)) {
						conversation.add(new ConversationString(line));
					} else if (section.equals("Title:") && !section.equals(line)) {
						conversation.add(new ConversationString(line));
					} else if (section.equals("Text:") && !section.equals(line)) {
						conversation.get(conversation.size() - 1).addToText(line);
					}
				}	
			}
			in.close();			
		} catch (IOException e) {}
	}

	public boolean isFireFighter() {
		return fireFighter;
	}

	public void setFireFighter(boolean fireFighter) {
		this.fireFighter = fireFighter;
	}

	public boolean isFisher() {
		return fisher;
	}

	public void setFisher(boolean fisher) {
		this.fisher = fisher;
	}

	public boolean isVillageFriend() {
		return villageFriend;
	}

	public void setVillageFriend(boolean villageFriend) {
		this.villageFriend = villageFriend;
	}
}
