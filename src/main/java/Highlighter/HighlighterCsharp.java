package Highlighter;

/**
 * @file HighlighterCsharp.java
 * @brief Contains the HighlighterCsharp class, which implements the InterfaceSyntaxHighlighter for C# syntax highlighting.
 */
import java.awt.Color;

/**
 * @class HighlighterCsharp
 * @brief Implements the InterfaceSyntaxHighlighter for C# syntax highlighting.
 */
public class HighlighterCsharp implements InterfaceSyntaxHighlighter {
	/**
	 * Retrieves the C# keyword variables.
	 *
	 * @return An array of C# keyword variables.
	 */
	@Override
	public String[] KeywordVariables() {
		String[] CSHARP_KeywordVariables = { "bool", "byte", "sbyte", "char", "decimal", "double", "float", "int",
				"uint", "long", "ulong", "short", "ushort", "object", "string", "dynamic", "var" };
		return CSHARP_KeywordVariables;
	}

	/**
	 * Retrieves the C# keyword loops.
	 *
	 * @return An array of C# keyword loops.
	 */
	@Override
	public String[] KeywordLoops() {
		String[] CSHARP_KeywordLoops = { "for", "while", "do", "while", "foreach", "if", "else", "try", "catch", "case",
				"break", "class", "return" };
		return CSHARP_KeywordLoops;
	}

	/**
	 * Retrieves the C# keyword identifiers.
	 *
	 * @return An array of C# keyword identifiers.
	 */
	@Override
	public String[] KeywordIdentifiers() {
		String[] CSHARP_KeywordIdentifiers = { "define", "include", "setup", "ready", "size_t", "public", "if", "else",
				"try", "catch", "case", "break", "true", "false", "void", "public", "static" };
		return CSHARP_KeywordIdentifiers;
	}

	/**
	 * Retrieves the color for C# variables.
	 *
	 * @return The color for C# variables.
	 */
	@Override
	public Color ColorVariables() {
		Color CSHARP_ColorVariables = Color.BLUE;
		return CSHARP_ColorVariables;
	}

	/**
	 * Retrieves the color for C# loops.
	 *
	 * @return The color for C# loops.
	 */
	@Override
	public Color ColorLoops() {
		Color JAVA_ColorLoops = Color.RED;
		return JAVA_ColorLoops;
	}

	/**
	 * Retrieves the color for C# identifiers.
	 *
	 * @return The color for C# identifiers.
	 */
	@Override
	public Color ColorIdentifiers() {
		Color CSHARP_ColorLoops = Color.green;
		return CSHARP_ColorLoops;
	}

	/**
	 * Retrieves the color for C# functions.
	 *
	 * @return The color for C# functions.
	 */
	@Override
	public Color ColorFunction() {
		Color CSHARP_ColorFunction = Color.MAGENTA;
		return CSHARP_ColorFunction;
	}
}
