package ships;
import java.awt.Point;
import java.util.Random;

import javax.swing.ImageIcon;


public abstract class Ship {
	
	protected Point loc;
	protected ImageIcon icon;

	// Move Method
	public void move() {
		Random r = new Random();
		int x, y;
		
		do {
			x = r.nextInt(3)-1;
			y = r.nextInt(3)-1;
		} while (0 == x && 0 == y || !(loc.x + x >= 0 && loc.x + x <= 3 && loc.y + y >= 0 && loc.y + y <= 3));
		
		loc.translate(x,  y);
	}

	// Get current location
	public Point getLoc() {
		return loc;
	}

	// Get current location
	public void setLoc(Point loc) {
		this.loc = loc;
	}

	// Get icon - ImageIcon
	public ImageIcon getIcon() {
		return icon;
	}

	// Set icon - ImageIcon 
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
}
