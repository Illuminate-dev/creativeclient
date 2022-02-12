package creativeclient.util;

import java.awt.Color;

public class RainbowColor {
	
	public static Color getColor(float seconds, float saturation, float brightness) {
		float hue = (System.currentTimeMillis() % (int) (seconds * 1000)) / (float) (seconds * 1000);
		Color color = new Color(Color.HSBtoRGB(hue, saturation, brightness));
		return color;

	}
	
	public static Color getColor(float seconds, float saturation, float brightness, long index) {
		float hue = ((System.currentTimeMillis() + index) % 1000) / 1000f;
		Color color = new Color(Color.HSBtoRGB(hue, 1, 1));
		return color;

	}
	
	public static Color getColor() {
		float hue = (System.currentTimeMillis() % (int) 1000) / (float) 1000;
		Color color = new Color(Color.HSBtoRGB(hue, 1, 1));
		return color;

	}

}
