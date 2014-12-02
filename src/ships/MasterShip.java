package ships;
import java.awt.Point;
import java.util.Random;

import main.ImageFactory;


public class MasterShip extends Ship {
	
	private static MasterShip _MasterShip;

	private MasterShip() {
		// Get the icon reference from the ImageFactory!
		icon = ImageFactory.getImageIcon("ships/ship4-50.png");
		
		int x, y;
		
		Random r = new Random();
		x = r.nextInt(4);
		y = (x == 0 ? r.nextInt(3)+1 : r.nextInt(4)); // if column 0 skip row 0
		
		loc = new Point(x, y);
	}
	
	// Singleton MasterShip
	public static synchronized MasterShip getMasterShip() {
		if (_MasterShip == null)
			_MasterShip = new MasterShip();
		
		return _MasterShip;
	}
}
