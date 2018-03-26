package scripts;

import rs_bot.exceptions.RequiredArgException;
import rs_bot.input.ConsoleCommand;
import rs_bot.output.ConsoleOutput;
import rs_bot.rs.RsBot;
import rs_bot.util.Random;
import rs_bot.util.Script;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class Alch extends Script
{
	
	RsBot bot;
	
	public Alch(final ConsoleOutput out)
	{
		super(out);
		bot = new RsBot();
	}
	
	@Override
	public void run(final ConsoleCommand command)
	{
		if (!command.getArgs().containsKey("-n"))
		{
			throw new RequiredArgException("-n", "Slot containing nature runes");
		}
		
		int n = Integer.parseInt(command.getArgs().get("-n"));
		Set<Integer> slots = new HashSet<>();
		
		if (command.getArgs().containsKey("-s"))
		{
			for (String s : command.getArgs().get("-s").split(","))
			{
				slots.add(Integer.parseInt(s));
			}
		}
		else
		{
			for (int i = 1; i <= 28; i++)
			{
				slots.add(i);
			}
		}
		slots.remove(n);
		
		bot.wait(2500);
		bot.resetMouse();
		for (int s : slots)
		{
			bot.moveToInvLoc(s);
			bot.wait(500, 100);
			
			randomActions();
			
			int total;
			int stage1;
			int stage2;
			while (!bot.isSlotEmpty(s))
			{
				total = Random.randomIntInRange(2000, 3000);
				stage1 = Random.randomIntInRange(150, 750);
				stage2 = total - stage1;
				
				bot.keyType(KeyEvent.VK_Q, 150, 100);
				bot.wait(stage1);
				bot.mouseClick(1, 100, 50);
				bot.wait(stage2);
			}
		}
		
		print("Alching completed.");
		
	}
	
	private static void randomActions()
	{
	
	}
	
}
