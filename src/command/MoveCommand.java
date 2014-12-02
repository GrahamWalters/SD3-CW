package command;
import java.awt.Point;
import java.util.ArrayList;

import main.Game;
import ships.Ship;


public class MoveCommand implements Command {
	
	private ArrayList<Ship> ships;
	private ArrayList<Point> lastLoc;
	
	public MoveCommand(ArrayList<Ship> ships) {
		this.ships = ships;
		this.lastLoc = new ArrayList<Point>();
	}
 
	public void execute() {
		for (int i=0; i<ships.size(); i++) {
			// Save a clone of the last location
			this.lastLoc.add(i, (Point) ships.get(i).getLoc().clone());
			
			// Call move on the ship
			this.ships.get(i).move();

			// Set the changed flag to true
			Game.getGame().setChangedTrue();
		}
	}
	
	public void undo() {
		for (int i=0; i<ships.size(); i++) {
			// Set the location back using the location saved in execute
			this.ships.get(i).setLoc(lastLoc.get(i));
			
			// Set the changed flag to true
			Game.getGame().setChangedTrue();
		}
	}
}
