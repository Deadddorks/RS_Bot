package rs_bot.exceptions;

public class InventoryOutOfBoundsException extends RuntimeException
{
	
	public InventoryOutOfBoundsException(final int slotNum)
	{
		super("Slot must be 1-28. ["+ slotNum +"]");
	}
}
