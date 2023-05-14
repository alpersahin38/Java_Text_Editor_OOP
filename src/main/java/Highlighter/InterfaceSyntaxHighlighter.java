package Highlighter;

/**
 * @file InterfaceSyntaxHighlighter.java
 * @brief This file contains the InterfaceSyntaxHighlighter interface, which defines the contract for syntax highlighters.
 */
import java.awt.Color;

/**
 * @brief Interface for syntax highlighters.
 */
public interface InterfaceSyntaxHighlighter {

	/**
	 * @brief Retrieves the array of keyword variables.
	 * @return An array of keyword variables.
	 */
	public String[] KeywordVariables();

	/**
	 * @brief Retrieves the array of keyword loops.
	 * @return An array of keyword loops.
	 */
	public String[] KeywordLoops();

	/**
	 * @brief Retrieves the array of keyword identifiers.
	 * @return An array of keyword identifiers.
	 */
	public String[] KeywordIdentifiers();

	/**
	 * @brief Retrieves the color for variables.
	 * @return The color for variables.
	 */
	public Color ColorVariables();

	/**
	 * @brief Retrieves the color for loops.
	 * @return The color for loops.
	 */
	public Color ColorLoops();

	/**
	 * @brief Retrieves the color for identifiers.
	 * @return The color for identifiers.
	 */
	public Color ColorIdentifiers();

	/**
	 * @brief Retrieves the color for functions.
	 * @return The color for functions.
	 */
	public Color ColorFunction();
}