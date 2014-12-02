package main;

import java.util.HashMap;

import javax.swing.ImageIcon;

public class ImageFactory {
	// imageMap holds the file name/path and ImageIcon
	private static final HashMap<String, ImageIcon> imageMap = new HashMap<String, ImageIcon>();

	public static ImageIcon getImageIcon(String file) {
		ImageIcon icon = (ImageIcon) imageMap.get(file);
		
		// If the image doesn't already exist, create and add it. 
		if (icon == null) {
			icon = new ImageIcon(file);
			imageMap.put(file, icon);
		}
		
		return icon;
	}
}
