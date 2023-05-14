package View;

import Controller.SaveListener;
import Model.Model;

/**
 * @file App.java
 * @brief The entry point of the application.
 */

/**
 * @class App
 * @brief The main class that initializes the application.
 */
public class App {
	/**
	 * The main method that serves as the entry point of the application.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		// Create a new instance of the Model class
		Model model = new Model();
		// Create a new instance of the Editor class
		Editor editor = new Editor();
		// Create a new instance of the SaveListener class and pass the editor and model
		// as parameters
		SaveListener saveListener = new SaveListener(editor, model);
	}
}
