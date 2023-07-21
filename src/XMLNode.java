import java.util.List;

/**
 * This interface represents the various operations that can be performed on an XML node.
 */
interface XMLNode {
  /**
   * Processes the given character for the xml node, and returns the updated xml node as a result
   * of processing this character.
   *
   * @param c The input character to process
   * @return The updated XMLNode
   * @throws InvalidXMLException if the input character is invalid for the current parse state the
   *                             XML node is in
   */

  XMLNode processInput(char c) throws InvalidXMLException;

  /**
   * Returns the current text of the start tag of the node.
   *
   * @return the start tag text of the node.
   */
  String getStartTag();

  /**
   * Returns the current text of the end tag of the node.
   *
   * @return the end tag text of the node.
   */
  String getEndTag();

  /**
   * Appends the given character to the start tag of the node.
   *
   * @param c the character to add to the start tag
   */
  void updateStartTag(char c);

  /**
   * Appends the given character to the end tag of the node.
   *
   * @param c the character to add to the end tag
   */
  void updateEndTag(char c);

  /**
   * Appends the given character to the current text node of the xml node.
   *
   * @param c the character to be appended
   */
  void updateTextNode(char c);

  /**
   * Adds a new text node for the xml node.
   */
  void addTextNode(char c);

  /**
   * Adds a new child node to the xml node.
   */
  void addChildNode();

  /**
   * Returns the current child node that is being parsed.
   *
   * @return current child node of the xml node.
   */
  XMLNode getCurrentChildNode();

  /**
   * Returns the parent node of the current node that is being parsed.
   *
   * @return the parent node
   */
  XMLNode getParentNode();

  /**
   * Gets the current parse state of the XML node.
   *
   * @return the current parse state
   */
  ParseState getParseState();

  /**
   * Updates the parse state of the XML node with the given state.
   *
   * @param state the updated parse state of the XML node
   */
  void updateParseState(XMLNodeParseState state);

  /**
   * Returns the list of text and child nodes that are part of the XML node.
   *
   * @return ordered list of XML child elements.
   */
  List<XMLChildElement> getElements();
}
