package rs_bot.output;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ConsoleOutput
{

	private final VBox vBox;
	
	public ConsoleOutput(final VBox vBox)
	{
		this.vBox = vBox;
	}
	
	public void print(final String string)
	{
		vBox.getChildren().add(new Label(string));
	}
	
	public void clear()
	{
		vBox.getChildren().removeIf(i -> true);
	}
	
}
