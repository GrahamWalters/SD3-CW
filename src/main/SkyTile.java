package main;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ships.MasterShip;
import ships.Ship;

@SuppressWarnings("serial")
public class SkyTile extends JPanel implements Observer {
	ImageIcon icon;
	
	Game game;
	Point loc;
	
	JPanel skyPanel = new JPanel();
	JLabel[] labels = new  JLabel[4];
	
	public SkyTile(Game game, Point loc) {
		this.game = game;
		this.loc = loc;
		
		// Style square
		setBorder(BorderFactory.createLineBorder(Color.white));
		setOpaque(false);
		
		// Setup the 2x2 grid
		setLayout(new GridLayout(2, 2, 0, 0));
		for (int i=0; i<4; i++) {
			labels[i] = new JLabel();
			this.add(labels[i]);
		}
	}
	
	public void update(Observable o, Object arg) {
		// Get the ships in this square
		ArrayList<Ship> ships = game.getShips(loc);
		
		// Clear the 2x2 grid
		for (int i=0; i<4; i++) {
			labels[i].setIcon(null);
		}
		
		// Add the Ships to the 2x2 grid
		for (int i=0; i<ships.size() && i<4; i++) {
			labels[i].setIcon(ships.get(i).getIcon());
		}
		
		// Always put the MasterShip in the top left square
		if (ships.contains(MasterShip.getMasterShip())) {
			labels[0].setIcon(ships.get(0).getIcon());
		}
	}
}
