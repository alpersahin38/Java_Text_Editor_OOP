package Controller;

/**
 * @file CommandHistory.java
 * @brief Contains the CommandHistory class, which manages the history of commands.
 */
import java.util.Stack;

/**
 * @class CommandHistory
 * @brief Manages the history of commands.
 */
public class CommandHistory {
	/**
	 * The stack to store executed commands.
	 */
	private Stack<Command> history = new Stack<>();
	/**
	 * The stack to store redo commands.
	 */
	private Stack<Command> redoStack = new Stack<>();

	/**
	 * Pushes a command onto the history stack.
	 *
	 * @param c The command to push.
	 */
	public void push(Command c) {
		history.push(c);
		redoStack.clear();
	}

	/**
	 * Pops and returns the most recent command from the history stack.
	 *
	 * @return The most recent command, or null if the history is empty.
	 */
	public Command pop() {
		Command c = history.pop();
		redoStack.push(c);
		return c;
	}

	/**
	 * Redoes the most recently undone command.
	 *
	 * @return The command that was redone, or null if there are no commands to
	 *         redo.
	 */
	public Command redo() {
		if (redoStack.isEmpty())
			return null;
		Command c = redoStack.pop();
		history.push(c);
		return c;
	}

	/**
	 * Checks if the command history is empty.
	 *
	 * @return True if the command history is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return history.isEmpty();
	}

}