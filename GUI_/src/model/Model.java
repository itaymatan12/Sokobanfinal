package model;

import java.io.InputStream;

import controller.Exit;
import model.data.Level;
import model.data.LevelLoader;
import model.data.LevelSaver;

public interface Model {
	
	public void loadlevel(LevelLoader l,InputStream f) throws Exception;
	
	public void savelevel(LevelSaver s,String path) throws Exception; 
	
	public void exitlevel(Exit ex);
	
	public void  move(String direction) throws Exception;
	
	public void CleanLevel();

	public Level get_current_level();
	
} 