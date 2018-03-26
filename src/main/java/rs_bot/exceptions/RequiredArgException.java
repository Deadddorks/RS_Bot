package rs_bot.exceptions;

public class RequiredArgException extends RuntimeException
{
	
	public RequiredArgException(final String arg, final String hint)
	{
		super("Required argument: ["+ arg +"] {"+ hint +"}");
	}
	
}
