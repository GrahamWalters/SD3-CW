package ships;

import main.ImageFactory;


public class BattleCruiser extends EnemyShip {
	
	public BattleCruiser() {
		super(); // Initialize to 0,0

		// Get the icon reference from the ImageFactory!
		icon = ImageFactory.getImageIcon("ships/ship1-50.png");
	}
}
