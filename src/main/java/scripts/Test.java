package scripts;

import javafx.application.Platform;
import rs_bot.bot.Bot;
import rs_bot.input.ConsoleCommand;
import rs_bot.output.ConsoleOutput;
import rs_bot.rs.RsBot;
import rs_bot.util.Script;

public class Test extends Script
{
	
	private RsBot bot;
	
	public Test(final ConsoleOutput out)
	{
		super(out);
		bot = new RsBot();
	}
	
	@Override
	public void run(final ConsoleCommand command)
	{
		bot.wait(2500);
		bot.resetMouse();

		print("Starting inv search");
		for (int i = 1; i <= 28; i++)
		{
			print(i + ": " + (bot.isSlotEmpty(i) ? "" : "<--->"));
		}
		
		bot.leftClick();
		bot.logOut();
	}
	
}
