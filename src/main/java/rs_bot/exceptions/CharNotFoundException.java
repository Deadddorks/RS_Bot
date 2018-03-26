package rs_bot.exceptions;

public class CharNotFoundException extends RuntimeException
{
	public CharNotFoundException(final char c)
	{
		super("char ["+ Character.toString(c) +"] not found.");
	}
}
