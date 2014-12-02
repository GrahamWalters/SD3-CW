package main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import state.Start;
import state.State;

/*
 * MigLayout - info
 * http://www.miglayout.com
 * http://migcalendar.com/miglayout/cheatsheet.html
 */

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private State state = new Start();
	private BufferedImage skyImage;
	
	public GUI() {
		super("Sky Wars");
		
		// Read in the skyImage
		try { 
			skyImage = ImageIO.read(new File("background.jpg"));
		} catch(IOException ex) {
			System.out.println("Background error");
		}
		
		// Setup frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 400, 400);
		
        setResizable(false);
        setLocationByPlatform(true);
	}
	
	// Display the next state
	public void run() {
		setContentPane(state.run(this));
		setVisible(true);
	}
	
	// Set the next state
	public void setGameState(State state) {
		this.state = state;
	}
	
	// Get the skyImage
	public BufferedImage getSkyImage() {
		return skyImage;
	}
}
