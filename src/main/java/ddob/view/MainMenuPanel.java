package ddob.view;

import ddob.Model;
import ddob.util.ImageFactory;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    public static final String COVER_IMAGE = "1-29";

    private Model _model;
    private View _view;

    private JButton _btnNewGameFirstWaves;
    private JButton _btnNewGameBeyondTheBeach;
    private JButton _btnNewGameExtended;
    private JButton _btnExit;

    public MainMenuPanel( Model model, View view ) {
        super();
        _model = model;
        _view = view;

        _btnNewGameFirstWaves = new JButton( "New Game (First Waves)" );
        _btnNewGameBeyondTheBeach = new JButton( "New Game (Beyond the Beach)" );
        _btnNewGameExtended = new JButton( "New Game (Extended)" );
        _btnExit = new JButton( "Quit" );

        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout( new BoxLayout( buttonpanel, BoxLayout.PAGE_AXIS ) );
        buttonpanel.add( _btnNewGameFirstWaves );
        buttonpanel.add( _btnNewGameBeyondTheBeach );
        buttonpanel.add( _btnNewGameExtended );
        buttonpanel.add( _btnExit );

        setLayout( new GridLayout( 1, 2 ) );
        JLabel coverImage = new JLabel( ImageFactory.getIcon( COVER_IMAGE ) );
        add( coverImage );
    }

    public JButton getBtnNewGameFirstWaves() {
        return _btnNewGameFirstWaves;
    }

    public JButton getBtnNewGameBeyondTheBeach() {
        return _btnNewGameBeyondTheBeach;
    }

    public JButton getBtnNewGameExtended() {
        return _btnNewGameExtended;
    }

    public JButton getBtnExit() {
        return _btnExit;
    }
}