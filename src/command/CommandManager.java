package command;
import java.util.Stack;


public class CommandManager {
	private Stack<Command> history = new Stack<Command>();
	
	public void execute(Command command) {
		// Add the command to the history and execute it.
		this.history.push(command).execute();
	}
	
	// Undo method
	public void undo() {
		if (isUndoAvailable()) {
			// If the SpawnEnemyCommand is on top of the stack, undo it
			if (this.history.peek() instanceof SpawnEnemyCommand)
				this.history.pop().undo();

			// If the DestroyShipsCommand is on top of the stack, undo it
			if (this.history.peek() instanceof DestroyShipsCommand)
				this.history.pop().undo();
			
			// Undo the Ship moves
			this.history.pop().undo();
		}
	}
	
	// Does the history stack have commands to undo
	public boolean isUndoAvailable() {
        return !history.empty();
    }
}
