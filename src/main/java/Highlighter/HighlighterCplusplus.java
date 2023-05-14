package Highlighter;

/**
 * @file HighlighterCplusplus.java
 * @brief Contains the HighlighterCplusplus class, which implements the InterfaceSyntaxHighlighter for C++ syntax highlighting.
 */
import java.awt.Color;

/**
 * @class HighlighterCplusplus
 * @brief Implements the InterfaceSyntaxHighlighter for C++ syntax highlighting.
 */
public class HighlighterCplusplus implements InterfaceSyntaxHighlighter {
	/**
	 * Retrieves the C++ keyword variables.
	 *
	 * @return An array of C++ keyword variables.
	 */
	@Override
	public String[] KeywordVariables() {
		String[] CPP_KeywordVariables = { "bool", "char", "int", "float", "double", "void", "wchar_t", "short", "long",
				"unsigned", "size_t" };
		return CPP_KeywordVariables;
	}

	/**
	 * Retrieves the C++ keyword loops.
	 *
	 * @return An array of C++ keyword loops.
	 */
	@Override
	public String[] KeywordLoops() {
		String[] CPP_KeywordLoops = { "for", "while", "do", "while", "enhanced", "each", "if", "else", "try", "catch",
				"case", "break", "return" };
		return CPP_KeywordLoops;
	}

	/**
	 * Retrieves the C++ keyword identifiers.
	 *
	 * @return An array of C++ keyword identifiers.
	 */
	@Override
	public String[] KeywordIdentifiers() {
		String[] CPP_KeywordIdentifiers = { "define", "include", "setup", "ready", "size_t", "public", "if", "else",
				"try", "catch", "case", "break", "true", "false" };
		return CPP_KeywordIdentifiers;
	}

	/**
	 * Retrieves the color for C++ variables.
	 *
	 * @return The color for C++ variables.
	 */
	@Override
	public Color ColorVariables() {
		Color CPP_ColorVariables = Color.green;
		return CPP_ColorVariables;
	}

	/**
	 * Retrieves the color for C++ loops.
	 *
	 * @return The color for C++ loops.
	 */
	@Override
	public Color ColorLoops() {
		Color CPP_ColorLoops = Color.RED;
		return CPP_ColorLoops;
	}

	/**
	 * Retrieves the color for C++ identifiers.
	 *
	 * @return The color for C++ identifiers.
	 */
	@Override
	public Color ColorIdentifiers() {
		Color CPP_ColorIdentifiers = Color.white;
		return CPP_ColorIdentifiers;
	}

	/**
	 * Retrieves the color for C++ functions.
	 *
	 * @return The color for C++ functions.
	 */
	@Override
	public Color ColorFunction() {
		Color CPP_ColorFunction = Color.ORANGE;
		return CPP_ColorFunction;
	}
}
