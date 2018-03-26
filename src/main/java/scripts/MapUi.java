package scripts;

import rs_bot.bot.Bot;
import rs_bot.exceptions.MapNotFoundException;
import rs_bot.exceptions.RequiredArgException;
import rs_bot.input.ConsoleCommand;
import rs_bot.output.ConsoleOutput;
import rs_bot.rs.RsBot;
import rs_bot.rs.UiMap;
import rs_bot.util.Loc;
import rs_bot.util.Script;

import java.awt.Color;
import java.io.File;

public class MapUi extends Script
{
	
	private static final String savePath = "uiMaps/";
	private static final String defaultSaveFile = "defaultUI";
	private static final String fileExtension = ".rsuim";
	
	private RsBot bot;
	
	public MapUi(final ConsoleOutput out)
	{
		super(out);
		bot = new RsBot();
	}
	
	@Override
	public void run(final ConsoleCommand command)
	{
		
		if (command.getArgs().containsKey("-f"))
		{
			String s = command.getArgs().get("-f");
			String f = savePath + (s == null ? defaultSaveFile : s) + fileExtension;
			
			try
			{
				UiMap.loadUiMap(new File(f));
				print("Successfully loaded " + new File(f).toString());
			}
			catch (MapNotFoundException e)
			{
				print(e.getMessage());
			}
			return;
		}
		
		if (!command.getArgs().containsKey("-c"))
		{
			throw new RequiredArgException("-c", "Number of columns in Inventory");
		}
		
		print("Please clear your inventory and");
		print("set inventory opacity to 0.");
		print("\t--- Inventory ---");
		print("\t+----+----+----+");
		print("\t|A--+ +--+ +--D|");
		print("\t||  | |  | |  ||");
		print("\t|+--B +--+ +--+|");
		print("\t|+--+ +--+ +--+|");
		print("\t||  | |  | |  ||");
		print("\t|+--+ +--+ +--+|");
		print("\t|+--+ +--+ +--+|");
		print("\t||  | |  | |  ||");
		print("\t|C--+ +--+ +--+|");
		print("\t+----+----+----+");
		
		print("Move Mouse to A");
		Loc locA = bot.waitForMouseToStop(1000);
		print("Move Mouse to B");
		Loc locB = bot.waitForMouseToStop(1000);
		print("Move Mouse to C");
		Loc locC = bot.waitForMouseToStop(1000);
		print("Move Mouse to D");
		Loc locD = bot.waitForMouseToStop(1000);
		print("Move to log out button");
		Loc locE = bot.waitForMouseToStop(1000);
		
		bot.wait(100);
		
		Color c;
		int mins[] = new int[3];
		int maxs[] = new int[3];
		int sums[] = new int[3];
		int avgs[] = new int[3];
		int offs[] = new int[3];
		
		int x1 = locA.getX();
		int x2 = locB.getX();
		int x3 = locD.getX();
		
		int y1 = locA.getY();
		int y2 = locB.getY();
		int y3 = locC.getY();
		
		bot.resetMouse();
		UiMap.map = new UiMap(x1, x3, y1, y3, x2, y2, Integer.parseInt(command.getArgs().get("-c")));
		
		for (int i = 1; i <= UiMap.NUM_INV_SLOTS; i++)
		{
			c = bot.getColorOfSlot(i);
			
			mins[0] = Math.min(mins[0], c.getRed());
			maxs[0] = Math.max(maxs[0], c.getRed());
			sums[0] += c.getRed();
			
			mins[1] = Math.min(mins[1], c.getGreen());
			maxs[1] = Math.max(maxs[1], c.getGreen());
			sums[1] += c.getGreen();
			
			mins[2] = Math.min(mins[2], c.getBlue());
			maxs[2] = Math.max(maxs[2], c.getBlue());
			sums[2] += c.getBlue();
		}
		
		avgs[0] = sums[0] / UiMap.NUM_INV_SLOTS;
		avgs[1] = sums[1] / UiMap.NUM_INV_SLOTS;
		avgs[2] = sums[2] / UiMap.NUM_INV_SLOTS;
		
		Color avgC = new Color(avgs[0], avgs[1], avgs[2]);
		
		for (int i = 0; i < 3; i++)
		{
			offs[i] = Math.max(Math.abs(avgs[i] - mins[i]), Math.abs(avgs[i] - maxs[i])) + 1;
		}
		
		UiMap.map = new UiMap(UiMap.map, locE.getX(), locE.getY(), avgC, offs);
		print("UI Mapping complete!");
		
		System.out.println(UiMap.map.toString());
		
		String o;
		if (command.getArgs().containsKey("-o"))
		{
			o = command.getArgs().get("-o");
			if (o == null)
			{
				o = defaultSaveFile;
			}
		}
		else
		{
			o = defaultSaveFile;
		}
		UiMap.map.saveUiMap(new File(savePath + o + fileExtension));
		
	}
	
}
