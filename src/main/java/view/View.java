package ddob.view;

import ddob.Model;

public class View {
	private Model _model;
	
	public View( Model model ) {
		_model = model;	
	}
	
	public Model getModel() { return _model; }
}