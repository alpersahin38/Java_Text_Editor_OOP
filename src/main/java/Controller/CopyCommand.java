package Controller;

/**
 * @file CopyCommand.java
 * @brief Contains the CopyCommand class, which represents a command for copying selected text.
 */
import View.Editor;

/**
 * @class CopyCommand
 * @brief Represents a command for copying selected text.
 */
public class CopyCommand extends Command {
	/**
	 * Constructs a CopyCommand object with the specified editor.
	 *
	 * @param editor The editor instance to associate with the command.
	 */
	public CopyCommand(Editor editor) {
		super(editor);
	}

	/**
	 * Executes the copy command.
	 *
	 * @return Always returns false as this command does not modify the editor's
	 *         state.
	 */
	@Override
	public boolean execute() {
		editor.clipboard = editor.textField.getSelectedText();
		return false;
	}
}