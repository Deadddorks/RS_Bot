package rs_bot.key;

public class KeyInfo
{
	
	private final char key;
	private final int keyCode;
	private final boolean shift;
	
	public KeyInfo(char key, int keyCode, boolean shift)
	{
		this.key = key;
		this.keyCode = keyCode;
		this.shift = shift;
	}
	
	public char getKey()
	{
		return key;
	}
	public int getKeyCode()
	{
		return keyCode;
	}
	public boolean useShift()
	{
		return shift;
	}
	
}
