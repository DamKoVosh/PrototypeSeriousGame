package conversation;

import player.Player;

public class ExitString extends ConversationString {
	
	private Player player;

	public ExitString(String title, Player player) {
		super(title);
		this.player = player;
	}
	
	@Override
	public boolean handleClick(int x, int y) {
		if (super.handleClick(x, y)) {
			player.setTalking(false);
		}
		return super.handleClick(x, y);
	}
	
	@Override
	public void setFocused(boolean b) {
		this.focused = b;
	}
}
