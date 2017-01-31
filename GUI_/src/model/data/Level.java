package model.data;
import java.io.Serializable;

import java.util.ArrayList;

import java.util.HashMap;

//class that defines a level in the game
public class Level  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;//making level serializable
	
	//data members
	private ArrayList<ArrayList<GameObject>> levelim ;//ArrayList of ArrayList of GameObject that describes the level and give us like an image of the level
	private int time;
	private ArrayList<Box> boxes;//ArrayList that contains all the boxes in the level 
	private ArrayList<Figure> figures;//ArrayList that contains all the figures in the level 
	private ArrayList<Wall> walls;//ArrayList that contains all the walls in the level 
	private ArrayList<DestofBox> destinations;//ArrayList that contains all the destinations in the level 
	private ArrayList<Transperent> transperents;//ArrayList that contains all the transperents in the level 
	private HashMap<String,DestofBox> targets;//hash map that get a string that represent a coordinate and tell us if in this coordinate ther is a destination object
	
	
	//default constructor
	public Level() {
				
		levelim=new ArrayList<ArrayList<GameObject>>();
		boxes=new ArrayList<Box>();
		figures=new ArrayList<Figure>();
		walls=new ArrayList<Wall>();
		destinations= new ArrayList<DestofBox>();
		transperents=new ArrayList<Transperent>();
		//HASH MAP that mapping strings to DestofBox objects		
		targets = new HashMap<String,DestofBox>();		
	}
	
	//removes function that clears the array lists and hashmap and allocated them again
	public void removelistfig()
	{
		this.figures.clear();
		this.figures=new ArrayList<Figure>();
		
	}
	public void removelistwall()
	{
		this.walls.clear();
		this.walls= new ArrayList<Wall>();
	}
	public void removelistfbox()
	{
		this.boxes.clear();
		this.boxes=new ArrayList<Box>();
	}
	public void removelistfdest()
	{
		this.destinations.clear();
		this.destinations=new ArrayList<DestofBox>();
	}
	public void removelisttran()
	{
		this.transperents.clear();
		this.transperents=new ArrayList<Transperent>();
	}
	public void removehash()
	{
		targets.clear();
	}
	
	public void removelevelim()
	 {
	  this.levelim.clear();
	  this.levelim=new ArrayList<ArrayList<GameObject>>();	  
	 }
	
	public void  removeallfields()
	{
		this.removehash();
		this.removelistfbox();
		this.removelistfdest();
		this.removelisttran();
		this.removelistwall();
		this.removelistfig();
		this.removelevelim();
	}
	//Getters and Setters
	public ArrayList<ArrayList<GameObject>> getLevelim() {
		return levelim;
	}

	public void setLevelim(ArrayList<ArrayList<GameObject>> levelim) {
		this.levelim = levelim;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public ArrayList<Box> getBoxes() {
		return boxes;
	}

	public void setBoxes(ArrayList<Box> boxes) {
		this.boxes = boxes;
	}

	public ArrayList<Figure> getFigures() {
		return figures;
	}

	public void setFigures(ArrayList<Figure> characters) {
		this.figures = characters;
	}

	public ArrayList<Wall> getWalls() {
		return walls;
	}

	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}

	public ArrayList<DestofBox> getDestinations() {
		return destinations;
	}

	public void setDestinations(ArrayList<DestofBox> destinations) {
		this.destinations = destinations;
	}

	public ArrayList<Transperent> getTransperents() {
		return transperents;
	}

	public void setTransperents(ArrayList<Transperent> transperents) {
		this.transperents = transperents;
	}

	public HashMap<String, DestofBox> getTargets() {
		return targets;
	}

	public void setTargets(HashMap<String, DestofBox> targets) {
		this.targets = targets;
	}
}
