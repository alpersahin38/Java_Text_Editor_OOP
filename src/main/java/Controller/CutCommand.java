package Controller;

/**
 * @file CutCommand.java
 * @brief Contains the CutCommand class, which represents a command for cutting selected text.
 */
import View.Editor;

/**
 * @class CutCommand
 * @brief Represents a command for cutting selected text.
 */
public class CutCommand extends Command {
	/**
	 * Constructs a CutCommand object with the specified editor.
	 *
	 * @param editor The editor instance to associate with the command.
	 */
	public CutCommand(Editor editor) {
		super(editor);
	}

	/**
	 * Executes the cut command.
	 *
	 * @return True if the execution is successful, false otherwise.
	 */
	@Override
	public boolean execute() {
		if (editor.textField.getSelectedText() == null)
			return false;

		backup();
		String source = editor.textField.getText();
		editor.clipboard = editor.textField.getSelectedText();
		editor.textField.setText(cutString(source));
		return true;
	}

	/**
	 * Cuts the selected text from the source string.
	 *
	 * @param source The original text.
	 * @return The modified text with the selected text removed.
	 */
	private String cutString(String source) {
		String start = source.substring(0, editor.textField.getSelectionStart());
		String end = source.substring(editor.textField.getSelectionEnd());
		return start + end;
	}
}