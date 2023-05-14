package Highlighter;
/**
 * @file HighlighterJava.java
 * @brief Contains the HighlighterJava class, which implements the InterfaceSyntaxHighlighter for Java syntax highlighting.
 */
import java.awt.Color;
/**
 * @class HighlighterJava
 * @brief Implements the InterfaceSyntaxHighlighter for Java syntax highlighting.
 */
public class HighlighterJava implements InterfaceSyntaxHighlighter {
	/**
     * Retrieves the Java keyword variables.
     *
     * @return An array of Java keyword variables.
     */
	@Override
	public String[] KeywordVariables() {
		String[] JAVA_KeywordVariables = { "boolean", "byte", "char", "short", "int", "long", "float", "double",
				"String", "Array", "Object", "Class", "void", "final" };
		return JAVA_KeywordVariables;
	}
	/**
     * Retrieves the Java keyword loops.
     *
     * @return An array of Java keyword loops.
     */
	@Override
	public String[] KeywordLoops() {
		String[] JAVA_KeywordLoops = { "for", "while", "do","while", "enhanced","each", "if", "else", "try", "catch",
				"case", "break","return" };
		return JAVA_KeywordLoops;
	}
	/**
     * Retrieves the Java keyword identifiers.
     *
     * @return An array of Java keyword identifiers.
     */
	@Override
	public String[] KeywordIdentifiers() {
		String[] JAVA_KeywordIdentifiers = { "package", "import", "public", "class", "extends", "private", "void",
				"new", "null", "static", "true", "return", "throws", "super", "false","static" };
		return JAVA_KeywordIdentifiers;
	}
	/**
     * Retrieves the color for Java variables.
     *
     * @return The color for Java variables.
     */
	@Override
	public Color ColorVariables() {
		Color JAVA_ColorVariables = Color.ORANGE;
		return JAVA_ColorVariables;
	}
	/**
     * Retrieves the color for Java loops.
     *
     * @return The color for Java loops.
     */
	@Override
	public Color ColorLoops() {
		Color JAVA_ColorLoops = Color.RED;
		return JAVA_ColorLoops;
	}
	/**
     * Retrieves the color for Java identifiers.
     *
     * @return The color for Java identifiers.
     */
	@Override
	public Color ColorIdentifiers() {
		Color JAVA_ColorLoops = Color.cyan;
		return JAVA_ColorLoops;
	}
	/**
     * Retrieves the color for Java functions.
     *
     * @return The color for Java functions.
     */
	@Override
	public Color ColorFunction() {
		Color JAVA_ColorFunction = Color.ORANGE;
		return JAVA_ColorFunction;
	}

}
