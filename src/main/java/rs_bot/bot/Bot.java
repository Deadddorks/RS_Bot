package rs_bot.bot;

import rs_bot.exceptions.BotCreationException;
import rs_bot.key.KeyMap;
import rs_bot.util.Loc;
import rs_bot.exceptions.MouseOffException;
import rs_bot.exceptions.WaitException;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Bot
{

	// ---------- Constants ----------
	private static final int DEFAULT_MOUSE_PRESS_DELAY = 50;
	private static final int DEFAULT_MOUSE_PRESS_VARIANCE = 10;
	
	private static final int DEFAULT_KEY_PRESS_DELAY = 50;
	private static final int DEFAULT_KEY_PRESS_VARIANCE = 10;
	
	private static final int DEFAULT_KEY_STOKE_DELAY = 50;
	private static final int DEFAULT_KEY_STOKE_VARIANCE = 10;
	
	// ---------- Variables ----------
	protected Robot robot;
	protected Loc currentLoc;
	private KeyMap keyMap;
	
	private int defaultMousePressDelay;
	private int defaultMousePressVariance;
	
	private int defaultKeyPressDelay;
	private int defaultKeyPressVariance;
	
	private int defaultKeyStrokeDelay;
	private int defaultKeyStrokeVariance;
	
	public Bot()
	{
		init();
	}
	public Bot(final int resetDelay)
	{
		init();
		
		wait(resetDelay);
		resetMouse();
	}
	
	private void init()
	{
		try
		{
			robot = new Robot();
		}
		catch (AWTException e)
		{
			throw new BotCreationException();
		}
		keyMap = new KeyMap();
		
		defaultMousePressDelay = DEFAULT_MOUSE_PRESS_DELAY;
		defaultMousePressVariance = DEFAULT_MOUSE_PRESS_VARIANCE;
		
		defaultKeyPressDelay = DEFAULT_KEY_PRESS_DELAY;
		defaultKeyPressVariance = DEFAULT_KEY_PRESS_VARIANCE;
		
		defaultKeyStrokeDelay = DEFAULT_KEY_STOKE_DELAY;
		defaultKeyStrokeVariance = DEFAULT_KEY_STOKE_VARIANCE;
	}
	
	// ---------- Mouse ----------
	public void mouseMove(final Loc loc)
	{
		mouseMove(loc.getX(), loc.getY());
	}
	public void mouseMove(final int x, final int y)
	{
		testMouse();
		
		robot.mouseMove(x, y);
		currentLoc = getMouseLoc();
	}
	
	public void mousePress(final int mouseButton)
	{
		testMouse();
		robot.mousePress(MouseEvent.getMaskForButton(mouseButton));
	}
	public void mouseRelease(final int mouseButton)
	{
		testMouse();
		robot.mouseRelease(MouseEvent.getMaskForButton(mouseButton));
	}
	public void mouseClick(final int mouseButton, final int mousePressDelay, final int mousePressVariance)
	{
		mousePress(mouseButton);
		wait(mousePressDelay, mousePressVariance);
		mouseRelease(mouseButton);
	}
	public void mouseClick(final int mouseButton, final int mousePressDelay)
	{
		mouseClick(mouseButton, mousePressDelay, defaultMousePressVariance);
	}
	public void mouseClick(final int mouseButton)
	{
		mouseClick(mouseButton, defaultMousePressDelay, defaultMousePressVariance);
	}
	public void leftClick()
	{
		mouseClick(1);
	}
	public void rightClick()
	{
		mouseClick(3);
	}
	
	// ---------- Key ----------
	public void keyPress(final int keyCode)
	{
		testMouse();
		robot.keyPress(keyCode);
	}
	public void keyRelease(final int keyCode)
	{
		testMouse();
		robot.keyRelease(keyCode);
	}
	public void keyType(final int keyCode, final int keyPressDelay, final int keyPressVariance)
	{
		keyPress(keyCode);
		wait(keyPressDelay, keyPressVariance);
		keyRelease(keyCode);
	}
	public void keyType(final int keyCode, final int keyPressDelay)
	{
		keyType(keyPressDelay, defaultKeyPressVariance);
	}
	public void keyType(final int keyCode)
	{
		keyType(keyCode, defaultKeyPressDelay, defaultKeyPressVariance);
	}
	
	// ---------- Pixels ----------
	public Color getPixelColor(final int x, final int y)
	{
		testMouse();
		return robot.getPixelColor(x, y);
	}
	public Color getPixelColor(final Loc loc)
	{
		return getPixelColor(loc.getX(), loc.getY());
	}
	
	// ---------- Utils ----------
	public void waitForMouseToMove(final int waitBetween)
	{
		Loc loc = getMouseLoc();
		while (loc.equals(getMouseLoc()))
		{
			wait(waitBetween);
		}
	}
	
	public Loc waitForMouseToStop(final int waitBetween)
	{
		Loc loc = getMouseLoc();
		Loc temp;
		
		waitForMouseToMove(waitBetween);
		
		while (!(temp = getMouseLoc()).equals(loc))
		{
			loc = temp;
			wait(waitBetween);
		}
		
		return loc;
	}
	
	public void resetMouse()
	{
		currentLoc = getMouseLoc();
	}
	
	public Loc getMouseLoc()
	{
		return new Loc(MouseInfo.getPointerInfo().getLocation());
	}
	protected boolean mouseOff()
	{
		return !currentLoc.equals(getMouseLoc());
	}
	protected void testMouse()
	{
		if (mouseOff())
		{
			throw new MouseOffException(currentLoc, getMouseLoc());
		}
	}
	
	public void wait(final int delay)
	{
		try
		{
			Thread.sleep(delay);
		}
		catch (InterruptedException e)
		{
			throw new WaitException();
		}
	}
	public void wait(final int delay, final int variance)
	{
		if (delay == 0)
		{
			throw new IllegalArgumentException("delay must be >= 0");
		}
		else if (variance < 0)
		{
			throw new IllegalArgumentException("variance must be >= 0");
		}
		else if (variance == 0)
		{
			wait(delay);
		}
		else
		{
			wait(rs_bot.util.Random.randomIntInRange(Math.max(0, delay - variance), delay + variance));
		}
	}

}
