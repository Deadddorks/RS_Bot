package rs_bot.util;

public class Random
{
	
	public static int randomIntInRange(final int min, final int max)
	{
		if (max < min)
		{
			throw new IllegalArgumentException("max is less than min.");
		}
		else if (min == max)
		{
			return min;
		}
		else
		{
			return new java.util.Random().nextInt(max - min + 1) + min;
		}
	}
	
}
