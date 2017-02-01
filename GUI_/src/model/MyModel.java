package model;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import controller.Exit;
import controller.RegularExit;
import model.data.Level;
import model.data.LevelLoader;
import model.data.LevelSaver;
import model.policy.MySokobanPolicyDecleration;
import model.policy.RegularMySokobanPolicy;
import view.Display2Dimention;

public class MyModel extends Observable implements Model {
	
	//data members
	private Level lvl;
	private int count;
	private HashMap<String, Exit>exit;//hash map that mapping string that represent a exit type object to the appropriate exit object 
	private MySokobanPolicyDecleration policy;
	
	//default constructor
	public MyModel()
	{
		count=0;
		exit = new HashMap<String, Exit>();
		this.lvl =new Level();	
		this.exit.put("regularexit", new RegularExit());
		this.policy = new RegularMySokobanPolicy();
	}

	//constructor with values
	public MyModel(Level lvl,MySokobanPolicyDecleration policy)
	{
		exit = new HashMap<String, Exit>();
		this.lvl =lvl;
		this.policy = policy;
	    //Hash map that contains types of Exit's objects
		exit.put("regularexit", new RegularExit());
	}

	@Override
	public void loadlevel(LevelLoader l,InputStream f) throws Exception
	{

		if(count>0)
		{
			lvl.removeallfields();
		
		}
		l.loadLevel(f,lvl);//loading the level	
		this.setChanged();
		LinkedList<Object> params = new LinkedList<Object>();
		params.add("display");
		params.add(new Display2Dimention());
		this.notifyObservers(params);
		count++;
	}

	@Override
	public void savelevel(LevelSaver s,String path) throws Exception
	{
		s.levelsave(path, lvl);//saving the level	
		this.setChanged();
		LinkedList<Object> params = new LinkedList<Object>();
		params.add("display");
		params.add(new Display2Dimention());
		this.notifyObservers(params);
	}

	@Override
	public void exitlevel(Exit ex)
	{  
		ex.exit();
	}

	@Override
	public void move(String direction) throws Exception 
	{    
		String valid=new String();
		valid="";
		direction = direction.toLowerCase();
		//activating the moves function respectively to the direction
		if(direction.equals("up"))
		{
			valid="up";
			policy.move_up(lvl);
			
		}
		
		else if(direction.equals("right"))
		{
			valid="right";
			policy.move_right(lvl);	
			
			
		}
		
		else if(direction.equals("left"))
		{
			valid="left";
			policy.move_left(lvl);	
			
		}
		
		else if(direction.equals("down"))
		{
			valid="down";
			policy.move_down(lvl);
			
		}
		
		else if(valid.equals(""))
		{	throw new Exception("invalid direction");	}//if the user entered a 	
		
		
		this.setChanged();
		LinkedList<Object> params = new LinkedList<Object>();
		params.add("display");
		params.add(new Display2Dimention());
		this.notifyObservers(params);
		

	}

	public Level getLvl() {
		return lvl;
	}

	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}

	public MySokobanPolicyDecleration getPolicy() {
		return policy;
	}

	public void setPolicy(MySokobanPolicyDecleration policy) {
		this.policy = policy;
	}

	@Override
	public Level get_current_level() {		
		return this.getLvl();
	}
    @Override
	public void CleanLevel() {
		this.lvl.removeallfields();
		
	}
}
