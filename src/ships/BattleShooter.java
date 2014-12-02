package ships;

import main.ImageFactory;


public class BattleShooter extends EnemyShip {
	
	public BattleShooter() {
		super(); // Initialize to 0,0

		// Get the icon reference from the ImageFactory!
		icon = ImageFactory.getImageIcon("ships/ship2-50.png");
	}
}
