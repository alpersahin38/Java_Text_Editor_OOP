package View;

/**
 * @file Editor.java
 * @brief This file contains the Editor class which represents a text editor application.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.text.StyledDocument;

import javax.swing.event.DocumentListener;
import javax.swing.*;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;

import javax.swing.text.AbstractDocument.DefaultDocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.undo.UndoableEdit;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import java.awt.Color;

import java.awt.event.KeyAdapter;

import Controller.Command;
import Controller.CommandHistory;
import Controller.CopyCommand;
import Controller.CutCommand;
import Controller.PasteCommand;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import Highlighter.InterfaceSyntaxHighlighter;
import Highlighter.SyntaxHighlighterFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @class Editor
 * @brief Represents a text editor application.
 */
public class Editor {

	public JFrame window;
	public JTextPane textField;
	public JMenuBar menuBar;
	public JMenu menuFile;
	public JComboBox comboBox;
	public JMenuItem saveMenu, undoMenu, redoMenu, freeMenu, javaMenu, cSharpMenu, cppMenu;
	public JButton undoButton, redoButton, copyButton, cutButton, pasteButton;
	public JLabel lblNewLabel;
	private JFileChooser fileChooser;
	public String clipboard;

	private CommandHistory history = new CommandHistory();
	Editor editor = this;

	public boolean isSaved = true;

	public Style keywordStyleVariables;
	public Style keywordStyleLoops;
	public Style keywordStyleIdentifiers;
	private Style functionStyle;
	private Timer highlightTimer;
	private TimerTask highlightTask;
	InterfaceSyntaxHighlighter editText;

	/**
	 * Constructs a new instance of the Editor class.
	 */
	public Editor() {

		// controller = new Controller(this);

		createWindow();
		createTextArea();
		createMenuBar();
		KeyShortcuts();
		textundo();
		highligtText();
		window.setVisible(true);
		languageRead();

	}

	/**
	 * Creates the main application window.
	 */
	public void createWindow() {
		window = new JFrame("TEXT EDITOR ALPER SAHIN - HUSEYIN YASAR");
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Creates the text area for editing the text.
	 */
	public void createTextArea() {

		textField = new JTextPane();

		JScrollPane scrollPane = new JScrollPane(textField, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		window.getContentPane().add(scrollPane);
	}

	/**
	 * Creates the menu bar and initializes its components.
	 */
	public void createMenuBar() {

		menuBar = new JMenuBar();
		window.setJMenuBar(menuBar);

		menuFile = new JMenu("File");
		menuBar.add(menuFile);

		saveMenu = new JMenuItem("Save");
		menuFile.add(saveMenu);
		saveMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

		copyButton = new JButton("Copy");
		menuBar.add(copyButton);
		copyButton.setBackground(Color.cyan);

		copyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				executeCommand(new CopyCommand(editor));
			}
		});

		pasteButton = new JButton("Paste");
		menuBar.add(pasteButton);
		pasteButton.setBackground(Color.cyan);

		pasteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				executeCommand(new PasteCommand(editor));
			}
		});

		cutButton = new JButton("Cut");
		menuBar.add(cutButton);
		cutButton.setBackground(Color.cyan);

		cutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				executeCommand(new CutCommand(editor));
			}
		});

		undoButton = new JButton("Undo");
		menuBar.add(undoButton);
		undoButton.setBackground(Color.cyan);

		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				undo();
			}
		});

		redoButton = new JButton("Redo");
		menuBar.add(redoButton);
		redoButton.setBackground(Color.cyan);

		redoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				redo();
			}
		});

	}

	/**
	 * Executes a command and adds it to the command history.
	 *
	 * @param command The command to execute.
	 */
	public void executeCommand(Command command) {

		if (command.execute()) {
			history.push(command);
		}
	}

	/**
	 * Undoes the last command or reverts text field changes if the history is
	 * empty.
	 */
	public void undo() {
		if (!history.isEmpty()) {
			Command command = history.pop();
			if (command != null) {
				command.undo();
			}
		} else {
			String currentText = textField.getText();
			if (textBeforeEdit != null && !currentText.equals(textBeforeEdit.toString())) {
				textField.setText(textBeforeEdit.toString());
			} else {
				int length = currentText.length();
				if (length > 0) {
					textField.setText(currentText.substring(0, length - 1));
				}
			}
		}
	}

	/**
	 * Redoes the last undone command.
	 */
	public void redo() {
		if (history.isEmpty())
			return;
		Command command = history.redo();
		if (command != null) {
			command.execute();
		}
	}

	private StringBuilder textBeforeEdit;

	private void textundo() {
		textField.getDocument().addUndoableEditListener(e -> {
			UndoableEdit edit = e.getEdit();
			if (edit instanceof DefaultDocumentEvent) {
				DefaultDocumentEvent documentEvent = (DefaultDocumentEvent) edit;
				if (documentEvent.getType() == DocumentEvent.EventType.CHANGE) {
					textBeforeEdit = new StringBuilder(textField.getText());
				}
			}
		});

	}

	private void KeyShortcuts() {

		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
					executeCommand(new CopyCommand(editor));
				}
			}
		});

		textField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK), "paste");
		textField.getActionMap().put("paste", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				executeCommand(new PasteCommand(editor));
			}
		});

		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_X) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
					executeCommand(new CutCommand(editor));
				}
			}
		});

		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
					undo();
				}
			}
		});

		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_Y) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
					redo();
				}
			}
		});

	}

	/**
	 * Adds a save listener to the save menu item.
	 *
	 * @param save The ActionListener for the save menu item.
	 */
	public void addSaveListener(ActionListener save) {
		saveMenu.addActionListener(save);

	}

	/**
	 * Retrieves the text pane component.
	 *
	 * @return The JTextPane component.
	 */
	public JTextPane getTextPane() {
		return this.textField;
	}

	/**
	 * Highlights the text in the text field based on the selected language.
	 */
	public void highligtText() {
		String[] languages = { "Java", "C++", "C#" };
		JComboBox<String> comboBox = new JComboBox(languages);

		SyntaxHighlighterFactory deneme = new SyntaxHighlighterFactory();
		editText = deneme.switcthLanguage("java");
		textField.setBackground(Color.BLACK);
		textField.setForeground(Color.WHITE);
		keywordStyleVariables = textField.addStyle("keywordStyleVariables", null);
		StyleConstants.setForeground(keywordStyleVariables, editText.ColorVariables());

		// Create the style for keywords2
		keywordStyleLoops = textField.addStyle("keywordStyleLoops", null);
		StyleConstants.setForeground(keywordStyleLoops, editText.ColorLoops());

		keywordStyleIdentifiers = textField.addStyle("keywordStyleIdentifiers", null);
		StyleConstants.setForeground(keywordStyleIdentifiers, editText.ColorIdentifiers());
		// Create the style for functions
		functionStyle = textField.addStyle("function", null);
		StyleConstants.setForeground(functionStyle, editText.ColorFunction());

		textField.getDocument().addUndoableEditListener(e -> {
			UndoableEdit edit = e.getEdit();
			if (edit instanceof DefaultDocumentEvent) {
				DefaultDocumentEvent documentEvent = (DefaultDocumentEvent) edit;
				if (documentEvent.getType() == DocumentEvent.EventType.CHANGE) {
					textBeforeEdit = new StringBuilder(textField.getText());
				}
			}
		});
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				scheduleHighlighting();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				scheduleHighlighting();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				scheduleHighlighting();
			}
		});

		highlightTimer = new Timer();
		highlightTask = null;

	}

	private void scheduleHighlighting() {
		if (highlightTask != null) {
			highlightTask.cancel();
		}

		highlightTask = new TimerTask() {
			@Override
			public void run() {
				highlight();
			}
		};

		// Schedule the highlight task with a delay of 300 milliseconds
		highlightTimer.schedule(highlightTask, 10);
	}

	private boolean isSurroundedByWhitespace(String text, int index, int length) {
		// Check if the word is surrounded by whitespace
		if ((index == 0 || !Character.isLetterOrDigit(text.charAt(index - 1)))
				&& (index + length == text.length() || !Character.isLetterOrDigit(text.charAt(index + length)))) {
			return true;
		}
		return false;
	}

	private void highlightWord(StyledDocument doc, String text, String word, Style style) throws BadLocationException {
		int start = 0;
		int index = text.indexOf(word, start);
		while (index >= 0) {
			if (isSurroundedByWhitespace(text, index, word.length())) {
				doc.setCharacterAttributes(index, word.length(), style, false);
			}
			start = index + word.length();
			index = text.indexOf(word, start);
		}
	}

	private void highlightKeywords(StyledDocument doc, String text) throws BadLocationException {
		// Highlight keywords

		for (String word : editText.KeywordVariables()) {
			highlightWord(doc, text, word, keywordStyleVariables);
		}

		// Highlight additional keywords
		for (String word : editText.KeywordLoops()) {
			highlightWord(doc, text, word, keywordStyleLoops);
		}

		for (String word : editText.KeywordIdentifiers()) {
			highlightWord(doc, text, word, keywordStyleIdentifiers);
		}
	}

	private void highlightFunctions(StyledDocument doc, String text) throws BadLocationException {

		Style functionStyle = textField.addStyle("function", null);
		StyleConstants.setForeground(functionStyle, editText.ColorFunction());

		Pattern functionPattern = Pattern.compile("(\\w+)\\s*\\(");
		Matcher matcher = functionPattern.matcher(text);

		while (matcher.find()) {
			String functionName = matcher.group(1);
			highlightWord(doc, text, functionName, functionStyle);
		}
	}

	public void highlight() {
		SwingUtilities.invokeLater(() -> {
			try {
				Document doc = textField.getDocument();
				String text = doc.getText(0, doc.getLength());

				StyledDocument styledDoc = textField.getStyledDocument();
				styledDoc.setCharacterAttributes(0, text.length(), textField.getStyle("default"), true);

				highlightFunctions(styledDoc, text);
				highlightKeywords(styledDoc, text);

			} catch (BadLocationException ex) {
				ex.printStackTrace();
			}
		});
	}

	/**
	 * Reads the contents of a file and sets it in the text field.
	 *
	 * @param textName The name of the file to read.
	 */
	private void readFile(String textName) {
		textField.setText("");
		try (BufferedReader br = new BufferedReader(new FileReader(textName))) {
			String line;
			while ((line = br.readLine()) != null) {
				textField.getDocument().insertString(textField.getDocument().getLength(), line + "\n", null);
			}
		} catch (IOException | BadLocationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the selected language from the combo box and updates the text field
	 * accordingly.
	 */
	public void languageRead() {
		SyntaxHighlighterFactory editColorSyntax = new SyntaxHighlighterFactory();

		JComboBox comboBox = new JComboBox();
		menuBar.add(comboBox);
		comboBox.setBackground(Color.MAGENTA);
		comboBox.addItem("Java");
		comboBox.addItem("C#");
		comboBox.addItem("C++");

		readFile("SampleJava.txt");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String selectedItem = (String) cb.getSelectedItem();

				// if selected language is C++, convert to C++ color.
				if (selectedItem.equals("C++")) {
					textField.setBackground(Color.DARK_GRAY);
					textField.setForeground(Color.WHITE);

					readFile("SampleCplusplus.txt");

					editText = editColorSyntax.switcthLanguage("cplusplus");

					keywordStyleVariables = textField.addStyle("keywordStyleVariables", null);
					StyleConstants.setForeground(keywordStyleVariables, editText.ColorVariables());

					// Create the style for keywords2
					keywordStyleLoops = textField.addStyle("keywordStyleLoops", null);
					StyleConstants.setForeground(keywordStyleLoops, editText.ColorLoops());

					keywordStyleIdentifiers = textField.addStyle("keywordStyleIdentifiers", null);
					StyleConstants.setForeground(keywordStyleIdentifiers, editText.ColorIdentifiers());
					// Create the style for functions
					functionStyle = textField.addStyle("function", null);
					StyleConstants.setForeground(functionStyle, editText.ColorFunction());

				} else if (selectedItem.equals("C#")) {
					textField.setBackground(Color.WHITE);
					textField.setForeground(Color.BLACK);
					readFile("SampleCsharp.txt");

					editText = editColorSyntax.switcthLanguage("csharp");

					keywordStyleVariables = textField.addStyle("keywordStyleVariables", null);
					StyleConstants.setForeground(keywordStyleVariables, editText.ColorVariables());

					// Create the style for keywords2
					keywordStyleLoops = textField.addStyle("keywordStyleLoops", null);
					StyleConstants.setForeground(keywordStyleLoops, editText.ColorLoops());

					keywordStyleIdentifiers = textField.addStyle("keywordStyleIdentifiers", null);
					StyleConstants.setForeground(keywordStyleIdentifiers, editText.ColorIdentifiers());
					// Create the style for functions
					functionStyle = textField.addStyle("function", null);
					StyleConstants.setForeground(functionStyle, editText.ColorFunction());
				} else {
					textField.setForeground(Color.WHITE);
					textField.setBackground(Color.BLACK);
					readFile("SampleJava.txt");

					editText = editColorSyntax.switcthLanguage("java");

					keywordStyleVariables = textField.addStyle("keywordStyleVariables", null);
					StyleConstants.setForeground(keywordStyleVariables, editText.ColorVariables());

					// Create the style for keywords2
					keywordStyleLoops = textField.addStyle("keywordStyleLoops", null);
					StyleConstants.setForeground(keywordStyleLoops, editText.ColorLoops());

					keywordStyleIdentifiers = textField.addStyle("keywordStyleIdentifiers", null);
					StyleConstants.setForeground(keywordStyleIdentifiers, editText.ColorIdentifiers());
					// Create the style for functions
					functionStyle = textField.addStyle("function", null);
					StyleConstants.setForeground(functionStyle, editText.ColorFunction());
				}

				lblNewLabel = new JLabel(
						"                                                                                                                                                ");
				lblNewLabel.setForeground(new Color(255, 255, 255));
				menuBar.add(lblNewLabel);

			}

		});
	}

}