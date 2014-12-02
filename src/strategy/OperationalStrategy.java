package strategy;

import java.util.ArrayList;

import command.CommandManager;

import ships.Ship;


public interface OperationalStrategy {
	public boolean check(ArrayList<Ship> ships, CommandManager cm);
}
