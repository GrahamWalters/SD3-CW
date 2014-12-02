package ships;

import main.ImageFactory;


public class BattleStar extends EnemyShip {

	public BattleStar() {
		super(); // Initialize to 0,0

		// Get the icon reference from the ImageFactory!
		icon = ImageFactory.getImageIcon("ships/ship3-50.png");
	}
}
