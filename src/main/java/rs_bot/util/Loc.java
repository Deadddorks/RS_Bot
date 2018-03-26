package rs_bot.util;

import java.awt.Point;

public class Loc
{
	
	private final int xPos, yPos;
	
	public Loc(final int x, final int y)
	{
		this.xPos = x;
		this.yPos = y;
	}
	public Loc(final Point p)
	{
		this.xPos = p.x;
		this.yPos = p.y;
	}
	
	public int getX()
	{
		return xPos;
	}
	public int getY()
	{
		return yPos;
	}
	
	public Loc add(final Loc otherLoc)
	{
		return new Loc(xPos + otherLoc.xPos, yPos + otherLoc.yPos);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o == this)
		{
			return true;
		}
		if (!(o instanceof Loc))
		{
			return false;
		}
		
		Loc other = (Loc) o;
		return xPos == other.xPos && yPos == other.yPos;
	}
	
	@Override
	public String toString()
	{
		return "{Loc} xPos: ["+ xPos +"], yPos: ["+ yPos +"]";
	}
}
