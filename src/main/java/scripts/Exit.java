package scripts;

import javafx.application.Platform;
import rs_bot.gui.Console;
import rs_bot.input.ConsoleCommand;
import rs_bot.util.Script;

public class Exit extends Script
{
	
	public Exit()
	{
		super(null);
	}
	
	@Override
	public void run(ConsoleCommand command)
	{
		Platform.runLater(() -> Console.w.close());
	}
	
}
