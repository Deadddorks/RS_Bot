package rs_bot.exceptions;

public class WaitException extends RuntimeException
{
	public WaitException()
	{
		super("Error waiting.");
	}
}
