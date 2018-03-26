package rs_bot.util;

import javafx.application.Platform;
import rs_bot.input.ConsoleCommand;
import rs_bot.output.ConsoleOutput;

public abstract class Script
{
	
	private final ConsoleOutput out;
	
	public Script(final ConsoleOutput out)
	{
		this.out = out;
	}
	
	public abstract void run(final ConsoleCommand command);
	
	protected void print(final String text)
	{
		Platform.runLater(() -> out.print(text));
	}
	
	protected void clear()
	{
		Platform.runLater(() -> out.clear());
	}
	
}
