package rs_bot.rs;

import java.awt.Color;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import rs_bot.exceptions.InventoryOutOfBoundsException;
import rs_bot.exceptions.MapNotFoundException;
import rs_bot.util.Loc;

public class UiMap
{
	
	public static UiMap map = null;
	
	public static final int NUM_INV_SLOTS = 28;
	
	// ----- ----- ----- -----
	
	private final int inventoryLeft;
	private final int inventoryRight;
	private final int inventoryTop;
	private final int inventoryBottom;
	
	private final int slot1Right;
	private final int slot1Bottom;
	
	private final int numCols;
	
	// ----- ----- ----- -----
	
	private final int numRows;
	
	private final int slot1CenterX;
	private final int slot1CenterY;
	private final int slotWidth;
	private final int slotHeight;
	
	private final int invWidth;
	private final int invHeight;
	private final int xGap;
	private final int yGap;
	
	// ----- ----- ----- -----
	
	private final Loc logOutLoc;
	private final Color invColor;
	private final int[] invColorVariance;
	
	public UiMap(final int inventoryLeft, final int inventoryRight,
				 final int inventoryTop, final int inventoryBottom,
				 final int slot1Right, final int slot1Bottom,
				 final int numCols)
	{
		this.inventoryLeft = inventoryLeft;
		this.inventoryRight = inventoryRight;
		this.inventoryTop = inventoryTop;
		this.inventoryBottom = inventoryBottom;
		this.slot1Right = slot1Right;
		this.slot1Bottom = slot1Bottom;
		
		this.numCols = numCols;
		this.numRows = (int) Math.ceil(NUM_INV_SLOTS / numCols);
		
		this.slotWidth = slot1Right - inventoryLeft;
		this.slotHeight = slot1Bottom - inventoryTop;
		
		this.slot1CenterX = inventoryLeft + slotWidth / 2;
		this.slot1CenterY = inventoryTop + slotHeight / 2;
		
		this.invWidth = inventoryRight - inventoryLeft;
		this.invHeight = inventoryBottom - inventoryTop;
		
		this.xGap = (invWidth - numCols * slotWidth) / (numCols - 1);
		this.yGap = (invHeight - numRows * slotHeight) / (numRows - 1);
		
		logOutLoc = null;
		invColor = null;
		invColorVariance = null;
		
	}
	
	public UiMap(final UiMap parent,
				 final int logOutX, final int logOutY,
				 final Color invColor, final int[] invColorVariance)
	{
		this.inventoryLeft = parent.inventoryLeft;
		this.inventoryRight = parent.inventoryRight;
		this.inventoryTop = parent.inventoryTop;
		this.inventoryBottom = parent.inventoryBottom;
		this.slot1Right = parent.slot1Right;
		this.slot1Bottom = parent.slot1Bottom;
		
		this.numCols = parent.numCols;
		this.numRows = parent.numRows;
		
		this.slotWidth = parent.slotWidth;
		this.slotHeight = parent.slotHeight;
		
		this.slot1CenterX = parent.slot1CenterX;
		this.slot1CenterY = parent.slot1CenterY;
		
		this.invWidth = parent.invWidth;
		this.invHeight = parent.invHeight;
		
		this.xGap = parent.xGap;
		this.yGap = parent.yGap;
		
		this.logOutLoc = new Loc(logOutX, logOutY);
		
		this.invColor = invColor;
		this.invColorVariance = invColorVariance;
		
	}
	
	private Loc locInInv(final int invSlotNum)
	{
		return new Loc((invSlotNum - 1) % numCols, (invSlotNum - 1) / numCols);
	}
	
	public Loc getInvLocOfSlot(final int invSlotNum)
	{
		if (invSlotNum < 1 || invSlotNum > UiMap.NUM_INV_SLOTS)
		{
			throw new InventoryOutOfBoundsException(invSlotNum);
		}
		
		Loc locInInv = locInInv(invSlotNum);
		return new Loc(slot1CenterX + locInInv.getX() * (slotWidth + xGap), slot1CenterY + locInInv.getY() * (slotHeight + yGap));
	}
	
	public Loc getLogOutLoc()
	{
		return logOutLoc;
	}
	
	public boolean colorMatchBackground(final Color c)
	{
		int error[] = new int[3];
		
		error[0] = Math.abs(c.getRed() - invColor.getRed());
		error[1] = Math.abs(c.getGreen() - invColor.getGreen());
		error[2] = Math.abs(c.getBlue() - invColor.getBlue());
		
		return error[0] <= invColorVariance[0] && error[1] <= invColorVariance[1] && error[2] <= invColorVariance[2];
	}
	
	public void saveUiMap(final File file)
	{
		if (!file.exists())
		{
			file.getParentFile().mkdirs();
		}
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
		{
			
			writer.write("inv_left: " + inventoryLeft);
			writer.newLine();
			writer.write("inv_right: " + inventoryRight);
			writer.newLine();
			writer.write("inv_top: " + inventoryTop);
			writer.newLine();
			writer.write("inv_bottom: " + inventoryBottom);
			writer.newLine();
			writer.write("slot_1_right: " + slot1Right);
			writer.newLine();
			writer.write("slot_1_bottom: " + slot1Bottom);
			writer.newLine();
			writer.write("num_cols: " + numCols);
			writer.newLine();
			
			writer.write("log_out_x: " + logOutLoc.getX());
			writer.newLine();
			writer.write("log_out_y: " + logOutLoc.getY());
			writer.newLine();
			
			writer.write("inv_color_red: " + invColor.getRed());
			writer.newLine();
			writer.write("inv_color_green: " + invColor.getGreen());
			writer.newLine();
			writer.write("inv_color_blue: " + invColor.getBlue());
			writer.newLine();
			
			writer.write("color_variance_red: " + invColorVariance[0]);
			writer.newLine();
			writer.write("color_variance_green: " + invColorVariance[1]);
			writer.newLine();
			writer.write("color_variance_blue: " + invColorVariance[2]);
			writer.newLine();
			
		}
		catch (IOException e)
		{
		
		}
	}
	
	public static void loadUiMap(final File file)
	{
		if (!file.exists())
		{
			throw new MapNotFoundException(file);
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			int[] ints = new int[15];
			for (int i = 0; i < 15; i++)
			{
				ints[i] = Integer.parseInt(reader.readLine().split(": ")[1]);
			}
			UiMap.map = new UiMap(new UiMap(ints[0], ints[1], ints[2], ints[3], ints[4], ints[5], ints[6]),
					ints[7], ints[8], new Color(ints[9], ints[10], ints[11]), new int[] {ints[12], ints[13], ints[14]});
		}
		catch (IOException e)
		{
		
		}
	}
	
	@Override
	public String toString()
	{
		return "UiMap{" +
				"inventoryLeft=" + inventoryLeft +
				", inventoryRight=" + inventoryRight +
				", inventoryTop=" + inventoryTop +
				", inventoryBottom=" + inventoryBottom +
				", slot1Right=" + slot1Right +
				", slot1Bottom=" + slot1Bottom +
				", numCols=" + numCols +
				", numRows=" + numRows +
				", slot1CenterX=" + slot1CenterX +
				", slot1CenterY=" + slot1CenterY +
				", slotWidth=" + slotWidth +
				", slotHeight=" + slotHeight +
				", invWidth=" + invWidth +
				", invHeight=" + invHeight +
				", xGap=" + xGap +
				", yGap=" + yGap +
				", logOutLoc=" + logOutLoc +
				", invColor=" + invColor +
				", invColorVariance=" + Arrays.toString(invColorVariance) +
				'}';
	}
	
}
