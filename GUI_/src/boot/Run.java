package boot;


import controller.MyController;
import controller.server.RegularClientHandler;
import model.MyModel;
import view.MyView;

public class Run {

	public static void main(String[] args) 
	{
		MyView v =new MyView();
		MyModel m = new MyModel();
		MyController c = new MyController(m,v);
		
		v.addObserver(c);
		m.addObserver(c);		
			
		
		c.Start();
		
	}

}