package state;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import main.GUI;
import main.Game;
import main.SkyTile;
import net.miginfocom.swing.MigLayout;
import strategy.AggressiveOperationalStrategy;
import strategy.PassiveOperationalStrategy;

public class Play implements State {
	
	private JPanel contentPane, controlPanel, skyPanel;
	private JButton btnMove, btnUndo;
	private JRadioButton rdbtnPassive, rdbtnAggressive;
	private ButtonGroup buttonGroup;
	
	private SkyTile[][] skyTiles = new SkyTile[4][4];
	
	public Play() {}
	
	@Override
	public JPanel run(final GUI gui) {
		// Set the next State to End
		gui.setGameState(new End());
		
		contentPane = new JPanel();
		controlPanel = new JPanel();
		skyPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
	    	protected void paintComponent(Graphics g) {
	    		super.paintComponent(g);
	    		
	    		// Draw the background image
	    		g.drawImage(gui.getSkyImage(), 0, 0, null);
	    	}
	    };
	    
	    // Setup the contentPane
	    contentPane.setSize(670, 470);
	    gui.setSize(670, 470);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new MigLayout());
        contentPane.add(controlPanel, "aligny top");
        contentPane.add(skyPanel, "gapx 30px, aligny top");
		
        // Move Button
        btnMove = new JButton("Move");
        btnMove.addActionListener(moveClick());
        
        // Undo Button
        btnUndo = new JButton("Undo");
        btnUndo.addActionListener(undoClick());
        btnUndo.setEnabled(false);
        
        // Passive Radio Button
        rdbtnPassive = new JRadioButton("Passive");
        rdbtnPassive.addItemListener(passiveSelected());
        rdbtnPassive.setSelected(true);
        
        // Aggressive Radio Button
        rdbtnAggressive = new JRadioButton("Aggressive");
        rdbtnAggressive.addItemListener(aggressiveSelected());
        
        // ButtonGroup for Passive & Aggressive Radio Buttons
        buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnPassive);
        buttonGroup.add(rdbtnAggressive);        
        
        // Setup the controlPanel
        controlPanel.setLayout(new MigLayout());
        controlPanel.add(btnMove, "wrap, grow, gapy 20px");
        controlPanel.add(btnUndo, "wrap, grow");
        controlPanel.add(new JLabel("Select Operational Mode"), "wrap, gapy 20px");
        controlPanel.add(rdbtnPassive, "wrap");
        controlPanel.add(rdbtnAggressive);
        
        
        // Setup Sky
        skyPanel.setLayout(new GridLayout(4, 4, 0, 0));
        skyPanel.setPreferredSize(new Dimension(400, 400));
        for (int x=0; x<4; x++) {
        	for (int y=0; y<4; y++) {
        		skyTiles[x][y] = new SkyTile(Game.getGame(), new Point(x, y));
        		Game.getGame().addObserver(skyTiles[x][y]);
        		skyPanel.add(skyTiles[x][y]);
        	}
        }

        return contentPane;
	}
	
	// Passive Radio Button itemStateChanged
	private ItemListener passiveSelected() {
		return new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					System.out.println("Passive checked");
					Game.getGame().setOperationalStrategy(new PassiveOperationalStrategy());
				}
			}
		};
	}
	
	// Aggressive Radio Button itemStateChanged
	private ItemListener aggressiveSelected() {
		return new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					System.out.println("Aggressive checked");
					Game.getGame().setOperationalStrategy(new AggressiveOperationalStrategy());
				}
			}
		};
	}
	
	// Move ActionListener
	private ActionListener moveClick() {
		return new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Game.getGame().move();
        		btnUndo.setEnabled(true);
        	}
        };
	}
	
	// Undo ActionListener
	private ActionListener undoClick() {
		return new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Game.getGame().undo();
        		
        		// Check if undo commands are available
        		if (!Game.getGame().isUndoAvailable())
        			btnUndo.setEnabled(false);
        	}
        };
	}

}
