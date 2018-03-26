package scripts;

import rs_bot.input.ConsoleCommand;
import rs_bot.output.ConsoleOutput;
import rs_bot.util.Script;

public class Clear extends Script
{
	
	public Clear(final ConsoleOutput out)
	{
		super(out);
	}
	
	@Override
	public void run(final ConsoleCommand command)
	{
		clear();
	}
	
}
