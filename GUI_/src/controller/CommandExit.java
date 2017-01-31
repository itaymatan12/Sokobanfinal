package controller;

import java.util.HashMap;
import java.util.List;
import controller.server.MyServer;
import model.Model;

//command that responsible exit the game
public class CommandExit implements Command {

	//data members
	private Model m;
	private String exit_type;
	private HashMap<String, Exit>exit;//hash map that mapping string that represent a exit type object to the appropriate exit object 
	private MyServer s;
	//default constructor
	public CommandExit()
	{
		exit = new HashMap<String, Exit>();
		this.exit_type = new String();
		//Hash map that contains Exit objects
		exit.put("regularexit", new RegularExit());
	}
	
	//constructor with values
	public CommandExit(Model m,String exit_type) 
	{
		//Hash map that contains Exit objects
		exit.put("regularexit", new RegularExit());
		this.exit_type = exit_type;
		this.m =m;
	}

	public CommandExit(Model m,String exit_type,MyServer s) 
	{
		//Hash map that contains Exit objects
		exit.put("regularexit", new RegularExit());
		this.exit_type = exit_type;
		this.m =m;
	}
	//function that go to the appropriate function in the model that will exit the game
	@Override
	public void execute() throws Exception
	{
		//this.s.stop();
		//this.s.getServer().close();
		//we get the exit type object from the hash map and send it to the appropriate function in the model 
		m.exitlevel(exit.get(this.exit_type));
	}

	//function that dismantles the input to pieces and setting the to the class data members
	@Override
	public void setParams(List<Object> params)
	{
		this.m = (Model)params.get(0);
		this.exit_type = (String)params.get(1);	
		
		/*if(params.get(2)!=null)
		{
			this.s = (MyServer)params.get(2);
		} */
	}

	//Getters and Setters
	public String getExit_type() {
		return exit_type;
	}


	public void setExit_type(String exit_type) {
		this.exit_type = exit_type;
	}
}
