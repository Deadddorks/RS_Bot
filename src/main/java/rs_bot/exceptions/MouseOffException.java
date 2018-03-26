package rs_bot.exceptions;

import rs_bot.util.Loc;

public class MouseOffException extends RuntimeException
{
	public MouseOffException(final Loc expected, final Loc actual)
	{
		super("Mouse is off. Expected: ["+ expected.toString() +"], Actual: ["+ actual.toString() +"].");
	}
}
