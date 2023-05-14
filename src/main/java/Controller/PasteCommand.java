package Controller;

/**
 * @file PasteCommand.java
 * @brief Contains the PasteCommand class, which represents a command for pasting text from the clipboard.
 */
import View.Editor;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 * @class PasteCommand
 * @brief Represents a command for pasting text from the clipboard.
 */
public class PasteCommand extends Command {
	/**
	 * Constructs a PasteCommand object with the specified editor.
	 *
	 * @param editor The editor instance to associate with the command.
	 */
	public PasteCommand(Editor editor) {
		super(editor);
	}

	/**
	 * Executes the paste command.
	 *
	 * @return True if the execution is successful, false otherwise.
	 */
	@Override
	public boolean execute() {
		if (editor.clipboard == null || editor.clipboard.isEmpty())
			return false;

		backup();

		StyledDocument doc = editor.textField.getStyledDocument();
		int caretPosition = editor.textField.getCaretPosition();

		try {
			doc.insertString(caretPosition, editor.clipboard, null);
			return true;
		} catch (BadLocationException e) {
			e.printStackTrace();
			return false;
		}
	}
}
