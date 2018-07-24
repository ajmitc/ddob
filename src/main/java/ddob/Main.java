package ddob;

import javax.swing.JFrame;

import ddob.util.Util;
import ddob.view.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
	public static void main( String ... args ) {
		JFrame frame = new JFrame();
		frame.setTitle( "D-Day On Omaha Beach" );
		//frame.setSize( Util.getScreenSize() );
		frame.setSize( frame.getGraphicsConfiguration().getBounds().getSize() );
		Model model = new Model();
		View view = new View( model, frame );
		Controller controller = new Controller( model, view );

        frame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e ) {
                view.getGamePanel().stop();
                if( controller.getGameManager() != null )
                    controller.getGameManager().stop();
            }

            @Override
            public void windowClosed( WindowEvent e ) {
                System.exit( 0 );
            }

            @Override
            public void windowIconified( WindowEvent e ) {

            }

            @Override
            public void windowDeiconified( WindowEvent e ) {

            }

            @Override
            public void windowActivated( WindowEvent e ) {

            }

            @Override
            public void windowDeactivated( WindowEvent e ) {

            }
        } );


        frame.setVisible( true );
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}
}