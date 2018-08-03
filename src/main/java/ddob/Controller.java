package ddob;

import ddob.game.Game;
import ddob.game.GameType;
import ddob.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	private Model _model;
	private View _view;
    private GameManager _manager;

	public Controller( Model model, View view ) {
		_model = model;
		_view = view;

		_view.getMainMenuPanel().getBtnExit().addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				_view.getGamePanel().stop();
				if( _manager != null )
                    _manager.stop();
				System.exit( 0 );
			}
		} );

		_view.getMainMenuPanel().getBtnNewGameFirstWaves().addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				Game game = new Game( GameType.FIRST_WAVES );
				_model.setGame( game );
				playGame();
			}
		} );

		_view.getMainMenuPanel().getBtnNewGameBeyondTheBeach().addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				Game game = new Game( GameType.BEYOND_THE_BEACH );
				_model.setGame( game );
                playGame();
			}
		} );

		_view.getMainMenuPanel().getBtnNewGameExtended().addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				Game game = new Game( GameType.EXTENDED );
				_model.setGame( game );
                playGame();
			}
		} );
	}

	private void playGame() {
        _view.show( View.GAME );
        _view.getGamePanel().start();
        //_manager = new GameManager( _model, _view );
    }

    public GameManager getGameManager(){ return _manager; }
}