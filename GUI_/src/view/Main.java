package view;
	
import controller.MyController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MyModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application


{
	
	@Override
	public void start(Stage primaryStage)
	{
		//https://github.com/itaymatan12/Sokobanfinal.git
		
		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
				BorderPane root = (BorderPane)loader.load();
				MainWindowController view =loader.getController();
				Scene scene = new Scene(root,500,500);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				
				
				
				primaryStage.setOnCloseRequest(e->
				{
					e.consume();
					
					view.exit();
				});
			
				init(view);
			
				primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void init(MainWindowController view)
	{
		MyModel model = new MyModel();
		MyController controller = new MyController(model,view);
		
		model.addObserver(controller);
		view.addObserver(controller);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}