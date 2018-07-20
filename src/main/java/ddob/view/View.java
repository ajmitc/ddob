package ddob.view;

import ddob.Model;
import ddob.game.card.Card;

import javax.swing.JFrame;
import java.awt.*;

public class View {
    public static final String MAINMENU = "MainMenu";
    public static final String GAME = "Game";

	private Model _model;
	private JFrame _frame;

	private GamePanel _gamePanel;

	public View( Model model, JFrame frame ) {
		_model = model;	
		_frame = frame;

		//JPanel mainmenu = ;
        _gamePanel = new GamePanel( _model, this );

		_frame.getContentPane().setLayout( new CardLayout() );
        _frame.getContentPane().add( _gamePanel, GAME );
	}

	public void show( String card ) {
	    CardLayout cardLayout = (CardLayout) _frame.getContentPane().getLayout();
	    cardLayout.show( _frame.getContentPane(), card );

	    if( card.equals( GAME )) {
            _gamePanel.start();
        }
        else {
            _gamePanel.stop();
        }
    }
	
	public Model getModel() { return _model; }
	public JFrame getFrame(){ return _frame; }
	public GamePanel getGamePanel(){ return _gamePanel; }
}