/**
 * This interface represents the various operations that can be performed on XML Elements.
 */
public interface XMLElement {
  /**
   * Returns the text associated with the element.
   *
   * @return string representing the text of the element
   */
  String getText();

  /**
   * Appends the given character to the text of the element.
   *
   * @param c the character to append to the text
   */
  void updateText(char c);

  /**
   * Returns the node associated with the element.
   *
   * @return the child xml node
   */
  XMLNode getNode();

  /**
   * Returns the type of the XML element.
   *
   * @return the type of the XML element
   */
  ElementType getType();
}
