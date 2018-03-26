package rs_bot.exceptions;

import rs_bot.input.ConsoleCommand;

public class CommandNotFoundException extends RuntimeException
{
	private ConsoleCommand command;
	
	public CommandNotFoundException(final ConsoleCommand command)
	{
		super("Command ["+command.getCommand()+"] does not exist in command repository.");
		this.command = command;
	}
	
	public ConsoleCommand getCommand()
	{
		return command;
	}
}
