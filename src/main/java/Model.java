package ddob;

import ddob.game.Game;

public class Model {
	private Game _game;
	
	public Model() {
		_game = null;
	}
	
	public Game getGame(){ return _game; }
}