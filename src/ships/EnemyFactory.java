package ships;
import java.util.Random;


public class EnemyFactory {
	public static EnemyShip createEnemy() {
		
		EnemyShip s = null;
		Random r = new Random();
		
		// Randomly select the EnemyShip
		switch(r.nextInt(3)) {
			case 0: s = new BattleCruiser(); break;
			case 1: s = new BattleShooter(); break;
			case 2: s = new BattleStar(); break;
		}
		
		return s;
	}
}
