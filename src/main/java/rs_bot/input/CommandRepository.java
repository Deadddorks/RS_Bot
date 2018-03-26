package rs_bot.input;

import rs_bot.exceptions.CommandNotFoundException;
import rs_bot.util.Script;

import java.util.HashMap;
import java.util.Map;

public class CommandRepository
{
	
	private Map<String, Script> commandMap;
	
	public CommandRepository()
	{
		commandMap = new HashMap<>();
	}
	
	public void add(final Script script)
	{
		commandMap.put(script.getClass().getSimpleName().toLowerCase(), script);
	}
	
	public void run(final ConsoleCommand command)
	{
		Script script = commandMap.get(command.getCommand());
		if (script == null)
		{
			throw new CommandNotFoundException(command);
		}
		else
		{
			script.run(command);
		}
	}
	
}
