package Controller;

/**
 * @file Command.java
 * @brief Contains the Command class, which represents an abstract command in the controller.
 */
import View.Editor;

/**
 * @class Command
 * @brief Represents an abstract command in the controller.
 */
public abstract class Command {
	/**
	 * The editor instance associated with the command.
	 */
	public Editor editor;
	public String backup;

	/**
	 * Constructs a Command object with the specified editor.
	 *
	 * @param editor The editor instance to associate with the command.
	 */
	public Command(Editor editor) {
		this.editor = editor;
	}

	/**
	 * Creates a backup of the editor's text field.
	 */
	public void backup() {
		backup = editor.textField.getText();
	}

	/**
	 * Restores the editor's text field to the backup state.
	 */
	public void undo() {
		editor.textField.setText(backup);
	}

	/**
	 * Executes the command.
	 *
	 * @return True if the execution is successful, false otherwise.
	 */
	public abstract boolean execute();
}