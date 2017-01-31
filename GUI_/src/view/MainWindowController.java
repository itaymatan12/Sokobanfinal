package view;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.data.Level;


public class MainWindowController extends Observable implements Initializable , View {

	private int count =0;
	private File f;
	private String upkeycode;
	private  String downkeycode;
	private String rightkeycode;
	private String leftkeycode; 
	
	@FXML
	SokobanDisplayer soko;
	
	public MainWindowController() 
	{
	  this.soko = new SokobanDisplayer();
	  this.f = null;
		this.leftkeycode=new String();
		this.rightkeycode=new String();
		this.upkeycode=new String();
		this.downkeycode=new String();
	}
	
	public void cleanlevel()
	{
		
		String command = "cleanlevel";
		LinkedList<String> params = new LinkedList<String>();
		params.add(command);
		

		this.setChanged();
		this.notifyObservers(params);
		
	}
	
	public void load_file()
	{	
		FileChooser fc = new FileChooser();
		fc.setTitle("Load File");
		fc.setInitialDirectory(new File("./resources"));
		
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("OBJ files (*.obj)", "*.obj"));

		
       File chosen= fc.showOpenDialog(null);
       this.f =chosen;
       
       if(count>0)
       {
    	   this.cleanlevel();    	   
       }
       
       if (chosen!= null)
       {	
    	   ++count;
			String command = "loadfile";
			LinkedList<String> params = new LinkedList<String>();
			params.add(command);
			params.add(chosen.getPath());

			this.setChanged();
			this.notifyObservers(params);
			
			//call timer thread
			//timerFlag = false;
			//time.setText("0");
			//startTimer();
		}
	}

	public void save_file()
   	{
   		FileChooser fc = new FileChooser();
   		fc.setTitle("Load File");
   		fc.setInitialDirectory(new File("./resources"));
   		
           fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt"));
           fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
           fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("OBJ files (*.obj)", "*.obj"));
           
          File chosen= fc.showSaveDialog(null);
          if (chosen!= null)
          {
   			String command = "savefile";
   			LinkedList<String> params = new LinkedList<String>();
   			params.add(command);
   			params.add(chosen.getPath());

   			this.setChanged();
   			this.notifyObservers(params);
   			
   			//call timer thread
   			//timerFlag = false;
   			//time.setText("0");
   			//startTimer();
   		}
   	}
          
    public void exit()
    {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to exit?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
        	    String command = "exit";
        	    
        	    LinkedList<String> params = new LinkedList<String>();
        	    params.add(command);
        	    params.add("regularexit");
        	    this.setChanged();
     	
        	    this.notifyObservers(params);	  
        	    
        	    System.exit(1);
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		
		this.soko.addEventFilter(MouseEvent.MOUSE_CLICKED,(e)->soko.requestFocus());		
		
		
		this.soko.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
           
			@Override
			public void handle(KeyEvent event)
			{
				String direction = null;
				
					
				if(event.getCode() == KeyCode.getKeyCode(upkeycode))
				{
					direction ="up"; 
				}	
			
				
				if(event.getCode() == KeyCode.getKeyCode(downkeycode))
				{
					direction ="down";
				}
				
				if(event.getCode() == KeyCode.getKeyCode(rightkeycode))
				{
					
					direction ="right";	
				}
				
				if(event.getCode() == KeyCode.getKeyCode(leftkeycode))
				{
					direction ="left";
				}
				
				
					String command = "move";
					LinkedList<String> params = new LinkedList<String>();
	     			params.add(command);
	     			params.add(direction);

	     			setChanged();
	     			notifyObservers(params);
	
				}
		});
		}
	
	public void restart()
	{
	       if(count>0)
	       {
	    	   this.cleanlevel();  
	   
	    	   String command = "loadfile";
	    	   LinkedList<String> params = new LinkedList<String>();
	    	   params.add(command);
	    	   params.add(f.getPath());

	    	   this.setChanged();
	    	   this.notifyObservers(params);
	       }  
		
		
	}
	

	@Override
	public void displayLevel(Level l, Display d)
	{
		this.soko.setLevelim(l.getLevelim());
		
	}

	@Override
	public void displayMessage(String string) {
		System.out.println(string);		
	}


	@Override
	public void start() 
	{
			
			
	}



	@Override
	public void stop() {
		
			
	}

	
	@Override
	public void setcontrols(String up, String down, String left, String right) {
		
		this.rightkeycode =right;
		this.upkeycode=up;
		this.downkeycode=down;
		this.leftkeycode=left;
	}

	public String getUpkeycode() {
		return upkeycode;
	}

	public void setUpkeycode(String upkeycode) {
		this.upkeycode = upkeycode;
	}

	public String getDownkeycode() {
		return downkeycode;
	}

	public void setDownkeycode(String downkeycode) {
		this.downkeycode = downkeycode;
	}

	public String getRightkeycode() {
		return rightkeycode;
	}

	public void setRightkeycode(String rightkeycode) {
		this.rightkeycode = rightkeycode;
	}

	public String getLeftkeycode() {
		return leftkeycode;
	}

	public void setLeftkeycode(String leftkeycode) {
		this.leftkeycode = leftkeycode;
	}		
	
}