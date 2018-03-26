package rs_bot.rs;

import rs_bot.bot.Bot;
import rs_bot.exceptions.InventoryOutOfBoundsException;
import rs_bot.util.Loc;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RsBot extends Bot
{

	public RsBot()
	{
		super();
	}
	
	public void moveToInvLoc(final int slotNum)
	{
		mouseMove(UiMap.map.getInvLocOfSlot(slotNum));
	}
	
	public Color getColorOfSlot(final int slotNum)
	{
		return getPixelColor(UiMap.map.getInvLocOfSlot(slotNum));
	}
	
	public boolean isSlotEmpty(final int slotNum)
	{
		return UiMap.map.colorMatchBackground(getColorOfSlot(slotNum));
	}
	
	public void logOut()
	{
		keyType(KeyEvent.VK_ESCAPE);
		wait(1500, 500);
		mouseMove(UiMap.map.getLogOutLoc());
		wait(250,50);
		leftClick();
	}

}
