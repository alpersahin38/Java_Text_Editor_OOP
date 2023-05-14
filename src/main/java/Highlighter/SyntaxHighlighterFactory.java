package Highlighter;
/**
 * @file SyntaxHighlighterFactory.java
 * @brief This file contains the SyntaxHighlighterFactory class, which is responsible for creating syntax highlighters based on the selected programming language.
 */

/**
 * @brief Factory class for creating syntax highlighters.
 */
public class SyntaxHighlighterFactory {
	/**
	 * @brief Creates a syntax highlighter based on the selected programming
	 *        language.
	 * @param selected The selected programming language.
	 * @return The syntax highlighter instance.
	 */
	public InterfaceSyntaxHighlighter switcthLanguage(String selected) {

		switch (selected) {
		case "cplusplus":
			return new HighlighterCplusplus();
		case "csharp":
			return new HighlighterCsharp();
		case "java":
			return new HighlighterJava();
		}
		return null;

	}
}
