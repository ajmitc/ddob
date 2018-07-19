package ddob.view;

import ddob.Model;

import javax.swing.JFrame;

public class View {
	private Model _model;
	private JFrame _frame;
	
	public View( Model model, JFrame frame ) {
		_model = model;	
		_frame = frame;
	}
	
	public Model getModel() { return _model; }
	public JFrame getFrame(){ return _frame; }
}