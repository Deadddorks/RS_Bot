package rs_bot.input;

import rs_bot.gui.Console;

import java.util.HashMap;
import java.util.Map;

public class ConsoleCommand
{
	
	private final String command;
	private final Map<String, String> args;
	
	private ConsoleCommand(final String command, final Map<String, String> args)
	{
		this.command = command;
		this.args = args;
	}
	
	public String getCommand()
	{
		return command;
	}
	public Map<String, String> getArgs()
	{
		return args;
	}
	
	public static ConsoleCommand parse(final String text)
	{
		String cmd;
		Map<String, String> map = new HashMap<String, String>();
		
		String[] split1 = text.split(" ", 2);
		cmd = split1[0];
		switch (split1.length)
		{
			case 1:
				break;
			case 2:
				String arg;
				arg = split1[1];
				String[] split2 = arg.split(" ");
				String[] split3;
				for (String s : split2)
				{
					split3 = s.split(":", 2);
					if (split3.length == 2)
					{
						map.put(split3[0], split3[1]);
					}
					else
					{
						map.put(split3[0], null);
					}
				}
				break;
			default:
				throw new RuntimeException("Error parsing ConsoleCommand ["+ text +"].");
		}
		
		return new ConsoleCommand(cmd, map);
	}
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		
		string.append("{ConsoleCommand} command: [");
		string.append(command);
		string.append("], args: [");
		
		for (Map.Entry e : args.entrySet())
		{
			string.append(e.getKey());
			string.append(" -> ");
			string.append(e.getValue());
			string.append(", ");
		}
		
		string.append("].");
		
		return string.toString();
	}
}
