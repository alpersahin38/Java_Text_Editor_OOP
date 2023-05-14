package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Model.Model;
import View.Editor;

/**
 * @file SaveListener.java
 * @brief Contains the SaveListener class, which handles saving operations in the editor.
 */

/**
 * @class SaveListener
 * @brief Handles saving operations in the editor.
 */

public class SaveListener implements ActionListener {

	private Editor view;
	private Model model;

	/**
	 * Constructs a SaveListener object with the specified view and model.
	 *
	 * @param view  The editor view instance.
	 * @param model The model instance.
	 */
	public SaveListener(Editor view, Model model) {
		this.view = view;
		this.model = model;

		view.addSaveListener(this);
		textChangeListener();
		windowCloseListener();
	}

	/**
	 * Handles the action event triggered by the save button.
	 *
	 * @param e The action event.
	 */
	public void actionPerformed(ActionEvent e) {
		File fileToWrite = null;
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			fileToWrite = fc.getSelectedFile();
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fileToWrite));
			out.println(view.getTextPane().getText());
			JOptionPane.showMessageDialog(null, "File is saved successfully...");
			out.close();
			view.isSaved = true;

		} catch (IOException ex) {
		}
	}

	/**
	 * Listens for text changes in the editor and updates the saved state.
	 */
	public void textChangeListener() {
		view.textField.getDocument().addDocumentListener((DocumentListener) new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				view.isSaved = false;
			}

			public void removeUpdate(DocumentEvent e) {
				view.isSaved = false;
			}

			public void changedUpdate(DocumentEvent e) {
			}
		});

	}

	/**
	 * Listens for the window close event and prompts to save changes before
	 * exiting.
	 */
	public void windowCloseListener() {
		view.window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (!view.isSaved) {
					int option = JOptionPane.showConfirmDialog(view.window,
							"Do you want to save changes before exiting?", "Exit", JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						SaveListener saveListener = new SaveListener(view, model);
						ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Save");
						saveListener.actionPerformed(event);
					} else if (option == JOptionPane.NO_OPTION) {
						System.exit(0);
					}
				} else {
					System.exit(0);
				}
			}
		});

	}

}