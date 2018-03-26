import rs_bot.bot.Bot;

import java.awt.event.KeyEvent;
import java.util.Random;

public class Test
{
	
	public static void main(String[] args)
	{
		
		Bot bot = new Bot();
		bot.wait(500);
		bot.resetMouse();
		
		System.out.println(bot.getPixelColor(bot.getMouseLoc()));
		
	}
	
}
