package boot;


import controller.MyController;
import controller.server.MyServer;
import controller.server.RegularClientHandler;
import model.MyModel;
import view.MyView;

public class Run
{
	public static void main(String[] args)
	{
		MyModel model = new MyModel();
		MyView view = new MyView();
		RegularClientHandler ch = new RegularClientHandler();

		if(args.length == 2)
		{
			if(!args[0].equals("-server"))
			{
				System.out.println("Error!");
				return;
			}

			MyServer server = new MyServer(Integer.parseInt(args[1]),ch);

			MyController controller = new MyController(model,ch,server);
			model.addObserver(controller);
			ch.addObserver(controller);
		}


		else
		{
			MyController controller = new MyController(model,view);
			model.addObserver(controller);
			view.addObserver(controller);
			view.start();
		}
	}
}
