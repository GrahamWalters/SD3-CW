package state;

import javax.swing.JPanel;

import main.GUI;

public interface State {
	public JPanel run(GUI gui);
}
