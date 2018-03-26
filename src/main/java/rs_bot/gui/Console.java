package rs_bot.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Console extends Application
{
	
	public static Stage w;
	
	public void start(final Stage window) throws Exception
	{
		
		w = window;
		
		Parent root = FXMLLoader.load(FXMLLoader.getDefaultClassLoader().getResource("fxml/console.fxml"));
		Scene scene = new Scene(root);
		
		window.setAlwaysOnTop(true);
		window.setTitle("Bot Console");
		window.setScene(scene);
		window.setResizable(false);
		window.show();
	
	}
	
	public static void main(String[] args)
	{
	    Console.launch(args);
	}
	
}
