package rs_bot.exceptions;

import java.io.File;

public class MapNotFoundException extends RuntimeException
{
	public MapNotFoundException(final File file)
	{
		super("File at ["+file.getPath()+"] not found.");
	}
}