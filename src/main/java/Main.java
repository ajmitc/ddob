package ddob;

import javax.swing.JFrame;
import ddob.view.View;

public class Main {
	public static void main( String ... args ) {
		JFrame frame = new JFrame();
		frame.setTitle( "D-Day On Omaha Beach" );
		
		Model model = new Model();
		View view = new View( model, frame );
		new Controller( model, view );
		
		frame.setVisible( true );
	}
}