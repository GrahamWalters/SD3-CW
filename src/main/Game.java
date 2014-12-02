package main;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import ships.MasterShip;
import ships.Ship;
import state.Start;
import strategy.OperationalStrategy;
import strategy.PassiveOperationalStrategy;

import command.CommandManager;
import command.MoveCommand;
import command.SpawnEnemyCommand;

public class Game extends Observable {
	
	private GUI gui;

	private static Game _game;
	
	private ArrayList<Ship> ships;
	private CommandManager cm;
	private OperationalStrategy strategy;
	
	public static void main(String[] args) {
		Game.getGame();
	}
	
	private Game() {
		// Open the GUI in a new thread
		new Thread(new Runnable() {
			public void run() {
				try {
					synchronized(this) {
						gui = new GUI();

						gui.setGameState(new Start());
						gui.run();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();		
	}
	
	// 
	public static synchronized Game getGame() {
		if (_game == null) {
			_game = new Game();
		}
		
		return _game;
	}
	
	
	public void play() {
		ships = new ArrayList<Ship>();
		cm = new CommandManager();
		strategy = new PassiveOperationalStrategy();
		
		ships.add(MasterShip.getMasterShip());
		
		gui.run();
		
		setChanged();
		// Draw updates
		notifyObservers("Play");
	}
	
	public void end() {
		ships = null;
		cm = null;
		strategy = null;
		
		// Next State (Play)
		gui.run();
	}
	
	public void setChangedTrue() {
		setChanged();
	}
	
	/*
	 * Move the ships.
	 * Spawn an enemy (1/3 chance).
	 * Check MasterShip square for other space ships.
	 */
	public void move() {
		cm.execute(new MoveCommand(ships));
		
		// Spawn an enemy (1/3 chance)
		Random r = new Random();
		if (r.nextInt(3) == 0)
			cm.execute(new SpawnEnemyCommand(ships));

		// Check MasterShip square for other space ships
		if (this.strategy.check(ships, cm)) {
			// Next state (End)
			gui.run();
		} else {
			// Draw updates
			notifyObservers("move");
		}
	}
	
	// Undo last commands
	public void undo() {
		cm.undo();

		// Draw updates
		notifyObservers("undo");
	}
	
	// Proxy the CommandManager isUndoAvailable method.
	public boolean isUndoAvailable() {
		return cm.isUndoAvailable();
	}
	
	// Set the OperationalStrategy - strategy
	public void setOperationalStrategy(OperationalStrategy strategy) {
		this.strategy = strategy;
	}
	
	// Get the ships
	public ArrayList<Ship> getShips() {
		return this.ships;
	}
	
	// Get the ships at a Point
	public ArrayList<Ship> getShips(Point loc) {
		ArrayList<Ship> matches = new ArrayList<Ship>();
		for (Ship s : ships) {
			if (s.getLoc().equals(loc)) {
				matches.add(s);
			}
		}
		return matches;
	}
}
