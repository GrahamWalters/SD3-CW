package strategy;

import java.awt.Point;
import java.util.ArrayList;

import ships.Ship;

import command.CommandManager;
import command.DestroyShipsCommand;


public class AggressiveOperationalStrategy implements OperationalStrategy {

	// Returns true if the game is over
	public boolean check(ArrayList<Ship> ships, CommandManager cm) {
		Point master = ships.get(0).getLoc();
		ArrayList<Ship> matches = new ArrayList<Ship>();

		// Add ships to the matches ArrayList if they are in the same square as the MasterShip
		for (int s=1; s<ships.size(); s++) {
			if (ships.get(s).getLoc().equals(master)) {
				matches.add(ships.get(s));
			}
		}
		
		if (matches.size() == 0) {
			return false;
		} else if (matches.size() <= 2) {
			// DestroyShips if matches contains 1 or 2
			cm.execute(new DestroyShipsCommand(ships, matches));
			System.out.println("Total: "+ships.size() + "\tdestroyed: " + matches.size());
			return false;
		} else {
			// More than 1 ship matched, game over
			System.out.println("GAME OVER");
			return true;
		}
	}
}
