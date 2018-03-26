package rs_bot.key;

import javax.swing.text.Keymap;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyMap
{

	private static final KeyInfo[] keys = new KeyInfo[] {
			// Lower case
			new KeyInfo('a', KeyEvent.VK_A, false),
			new KeyInfo('b', KeyEvent.VK_B, false),
			new KeyInfo('c', KeyEvent.VK_C, false),
			new KeyInfo('d', KeyEvent.VK_D, false),
			new KeyInfo('e', KeyEvent.VK_E, false),
			new KeyInfo('f', KeyEvent.VK_F, false),
			new KeyInfo('g', KeyEvent.VK_G, false),
			new KeyInfo('h', KeyEvent.VK_H, false),
			new KeyInfo('i', KeyEvent.VK_I, false),
			new KeyInfo('j', KeyEvent.VK_J, false),
			new KeyInfo('k', KeyEvent.VK_K, false),
			new KeyInfo('l', KeyEvent.VK_L, false),
			new KeyInfo('m', KeyEvent.VK_M, false),
			new KeyInfo('n', KeyEvent.VK_N, false),
			new KeyInfo('o', KeyEvent.VK_O, false),
			new KeyInfo('p', KeyEvent.VK_P, false),
			new KeyInfo('q', KeyEvent.VK_Q, false),
			new KeyInfo('r', KeyEvent.VK_R, false),
			new KeyInfo('s', KeyEvent.VK_S, false),
			new KeyInfo('t', KeyEvent.VK_T, false),
			new KeyInfo('u', KeyEvent.VK_U, false),
			new KeyInfo('v', KeyEvent.VK_V, false),
			new KeyInfo('w', KeyEvent.VK_W, false),
			new KeyInfo('x', KeyEvent.VK_X, false),
			new KeyInfo('y', KeyEvent.VK_Y, false),
			new KeyInfo('z', KeyEvent.VK_Z, false),
			// Upper case
			new KeyInfo('a', KeyEvent.VK_A, true),
			new KeyInfo('b', KeyEvent.VK_B, true),
			new KeyInfo('c', KeyEvent.VK_C, true),
			new KeyInfo('d', KeyEvent.VK_D, true),
			new KeyInfo('e', KeyEvent.VK_E, true),
			new KeyInfo('f', KeyEvent.VK_F, true),
			new KeyInfo('g', KeyEvent.VK_G, true),
			new KeyInfo('h', KeyEvent.VK_H, true),
			new KeyInfo('i', KeyEvent.VK_I, true),
			new KeyInfo('j', KeyEvent.VK_J, true),
			new KeyInfo('k', KeyEvent.VK_K, true),
			new KeyInfo('l', KeyEvent.VK_L, true),
			new KeyInfo('m', KeyEvent.VK_M, true),
			new KeyInfo('n', KeyEvent.VK_N, true),
			new KeyInfo('o', KeyEvent.VK_O, true),
			new KeyInfo('p', KeyEvent.VK_P, true),
			new KeyInfo('q', KeyEvent.VK_Q, true),
			new KeyInfo('r', KeyEvent.VK_R, true),
			new KeyInfo('s', KeyEvent.VK_S, true),
			new KeyInfo('t', KeyEvent.VK_T, true),
			new KeyInfo('u', KeyEvent.VK_U, true),
			new KeyInfo('v', KeyEvent.VK_V, true),
			new KeyInfo('w', KeyEvent.VK_W, true),
			new KeyInfo('x', KeyEvent.VK_X, true),
			new KeyInfo('y', KeyEvent.VK_Y, true),
			new KeyInfo('z', KeyEvent.VK_Z, true),
			// Numbers
			new KeyInfo('1', KeyEvent.VK_1, false),
			new KeyInfo('2', KeyEvent.VK_2, false),
			new KeyInfo('3', KeyEvent.VK_3, false),
			new KeyInfo('4', KeyEvent.VK_4, false),
			new KeyInfo('5', KeyEvent.VK_5, false),
			new KeyInfo('6', KeyEvent.VK_6, false),
			new KeyInfo('7', KeyEvent.VK_7, false),
			new KeyInfo('8', KeyEvent.VK_8, false),
			new KeyInfo('9', KeyEvent.VK_9, false),
			new KeyInfo('0', KeyEvent.VK_0, false),
			// Shift_numbers
			new KeyInfo('!', KeyEvent.VK_1, true),
			new KeyInfo('@', KeyEvent.VK_2, true),
			new KeyInfo('#', KeyEvent.VK_3, true),
			new KeyInfo('$', KeyEvent.VK_4, true),
			new KeyInfo('%', KeyEvent.VK_5, true),
			new KeyInfo('^', KeyEvent.VK_6, true),
			new KeyInfo('&', KeyEvent.VK_7, true),
			new KeyInfo('*', KeyEvent.VK_8, true),
			new KeyInfo('(', KeyEvent.VK_9, true),
			new KeyInfo(')', KeyEvent.VK_0, true),
			// Random
			new KeyInfo(',', KeyEvent.VK_COMMA, false),
			new KeyInfo('.', KeyEvent.VK_PERIOD, false),
			new KeyInfo('/', KeyEvent.VK_SLASH, false),
			new KeyInfo(';', KeyEvent.VK_SEMICOLON, false),
			new KeyInfo('[', KeyEvent.VK_BRACELEFT, false),
			new KeyInfo(']', KeyEvent.VK_BRACERIGHT, false),
			new KeyInfo('\\', KeyEvent.VK_BACK_SLASH, false),
			new KeyInfo('\t', KeyEvent.VK_TAB, false),
			// Shift_random
			new KeyInfo('<', KeyEvent.VK_COMMA, true),
			new KeyInfo('>', KeyEvent.VK_PERIOD, true),
			new KeyInfo('?', KeyEvent.VK_SLASH, true),
			new KeyInfo(':', KeyEvent.VK_SEMICOLON, true),
			new KeyInfo('{', KeyEvent.VK_BRACELEFT, true),
			new KeyInfo('}', KeyEvent.VK_BRACERIGHT, true),
			new KeyInfo('|', KeyEvent.VK_BACK_SLASH, true),
	};
	
	private Map<Character, KeyInfo> map;
	
	public KeyMap()
	{
		map = new HashMap<Character, KeyInfo>();
		for (KeyInfo i : keys)
		{
			map.put(i.getKey(), i);
		}
	}
	
	public KeyInfo getKeyInfo(final char c)
	{
		return map.get(c);
	}
	
}
