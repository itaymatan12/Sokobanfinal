package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.data.Box;
import model.data.GameObject;
import model.data.Transperent;

public class SokobanDisplayer extends Canvas {
	
	private ArrayList<ArrayList<GameObject>> levelim ;//ArrayList of ArrayList of GameObject that describes the level and give us like an image of the level
	
	private StringProperty wallfilename;
	private StringProperty boxfilename;
	private StringProperty destinationfilename;
	private StringProperty transperentfilename;
	private StringProperty figurefilename;
	private StringProperty boxtargetfilename;
	
	private int count =0;
	private int num_of_targets;
	private int num_box_on_targets;
	
	private HashMap<String, Image> objtoim;
	
	Image wall;
	Image box;
	Image destination;
	Image transperent;
	Image figure;
	Image boxtarget;
	
	GraphicsContext gc;
	
	public SokobanDisplayer() 
	
	{
		
		wallfilename = new SimpleStringProperty();
		boxfilename = new SimpleStringProperty();
		destinationfilename = new SimpleStringProperty();
		transperentfilename = new SimpleStringProperty();
		figurefilename = new SimpleStringProperty();
		boxtargetfilename = new SimpleStringProperty();

		this.levelim = null;
		gc = getGraphicsContext2D();
		

		
		objtoim =new HashMap<String ,Image>();
	
	}
	 public void initimage()
	   {
		   
		    try{
		    
				wall = new Image(new FileInputStream(wallfilename.get()));
				box = new Image(new FileInputStream(boxfilename.get()));
				destination = new Image(new FileInputStream(destinationfilename.get()));
				transperent = new Image(new FileInputStream(transperentfilename.get()));
				figure = new Image(new FileInputStream(figurefilename.get()));
				boxtarget = new Image(new FileInputStream(boxtargetfilename.get()));
			
				objtoim.put("Wall", wall);
				objtoim.put("Box", box);
				objtoim.put("DestofBox", destination);
				objtoim.put("Figure", figure);
				objtoim.put("Transperent", transperent);
				
			    }
			    catch(FileNotFoundException EX)
			    {
			    	System.out.println("the image in the resources unopen");
			    }
	   }
   
	public void redraw()
	{
		if(levelim != null)
		{
			int max = levelim.get(0).size();
			
			for (int i = 1; i < levelim.size(); i++)
			{
				max = Math.max(max, levelim.get(i).size());
			}
			
			double W = getWidth();
			double H = getHeight();
			double w = W/max;
			double h = H/levelim.size();
			
			
			gc.clearRect(0, 0, W, H);
			
			
			for(int i=0;i<levelim.size();i++)
			{
				int count_trans =0;
				
				for(int j=0;j<levelim.get(i).size();j++)
				{
					if(levelim.get(i).get(j)!= null)
					{
						if(this.getNum_box_on_targets()==this.getNum_of_targets())
						{
							
							
							
						}
						
						if(levelim.get(i).get(j) instanceof Transperent && count_trans ==0)
						{
							gc.setFill(Color.ALICEBLUE);
							gc.fillRect(j*w, i*h, w, h);
							continue;
						}
						
						else if(this.objtoim.get(levelim.get(i).get(j).getClass().getSuperclass().getSimpleName()) == null)
						{
							count_trans=1;
							gc.drawImage(transperent,j*w, i*h, w, h);
							continue;
						}
						
						else if(this.objtoim.get(levelim.get(i).get(j).getClass().getSuperclass().getSimpleName()) != null)
						{
							
							if(levelim.get(i).get(j) instanceof Box && ((Box)(levelim.get(i).get(j))).getFlag() == 1)
							{
									gc.drawImage(boxtarget,j*w, i*h, w, h);
									count_trans=1;
							}
			
							else
							{			
								gc.drawImage(this.objtoim.get(levelim.get(i).get(j).getClass().getSuperclass().getSimpleName()),j*w, i*h, w, h);	
								count_trans =1;
							}
	
						}
						
					}
								
				}
				
			}
			
		}
		
	}
	
	public ArrayList<ArrayList<GameObject>> getLevelim() {
		
		return levelim;
	}

	public void setLevelim(ArrayList<ArrayList<GameObject>> levelim) {
		this.levelim = levelim;
		if(count ==0)
		{
			this.initimage();
		}
		this.redraw();
		
	}



	public String getWallfilename() {
		return wallfilename.get();
	}



	public void setWallfilename(String wallfilename) {
		this.wallfilename.set(wallfilename);
	}



	public String getBoxfilename() {
		return boxfilename.get();
	}



	public void setBoxfilename(String boxfilename) {
		this.boxfilename.set(boxfilename);
	}



	public String getDestinationfilename() {
		return destinationfilename.get();
	}



	public void setDestinationfilename(String destinationfilename) {
		this.destinationfilename.set(destinationfilename);
	}



	public String getTransperentfilename() {
		return transperentfilename.get();
	}



	public void setTransperentfilename(String transperentfilename) {
		this.transperentfilename.set(transperentfilename);
	}



	public String getFigurefilename() {
		return figurefilename.get();
	}



	public void setFigurefilename(String figurefilename) {
		this.figurefilename.set(figurefilename);
	}
	
	public String getBoxtargetfilename() {
		return boxtargetfilename.get();
	}
	
	public void setBoxtargetfilename(String boxtargetfilename) {
		this.boxtargetfilename.set(boxtargetfilename);
	}
	public int getNum_of_targets() {
		return num_of_targets;
	}
	public void setNum_of_targets(int num_of_targets) {
		this.num_of_targets = num_of_targets;
	}
	public int getNum_box_on_targets() {
		return num_box_on_targets;
	}
	public void setNum_box_on_targets(int num_box_on_targets) {
		this.num_box_on_targets = num_box_on_targets;
	}

}