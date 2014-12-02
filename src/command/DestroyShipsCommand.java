package command;

import java.util.ArrayList;

import main.Game;
import ships.Ship;

public class DestroyShipsCommand implements Command {
	
	ArrayList<Ship> ships, shipsDestroyed;
	
	public DestroyShipsCommand(ArrayList<Ship> ships, ArrayList<Ship> shipsDestroyed) {
		this.ships = ships;
		this.shipsDestroyed = shipsDestroyed;
	}
	
	@Override
	public void execute() {
		if (ships.removeAll(shipsDestroyed)) {
			// Set the changed flag to true
			Game.getGame().setChangedTrue();
		}
	}

	@Override
	public void undo() {
		if (ships.addAll(shipsDestroyed)) {
			// Set the changed flag to true
			Game.getGame().setChangedTrue();
		}
		
		shipsDestroyed.clear();
	}
	
}
