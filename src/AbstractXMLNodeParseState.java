/**
 * Abstract base class for the concrete implementations of the XMLNodeParseState interface. It
 * contains the fields and methods that are common to the implementations of the concrete
 * classes.
 */
public abstract class AbstractXMLNodeParseState implements XMLNodeParseState {
  protected final XMLNode node;

  /**
   * Initialises the common fields for the concrete implementations of the XMLNodeParseState
   * interface.
   *
   * @param node the XML node to parse
   */
  protected AbstractXMLNodeParseState(XMLNode node) {
    this.node = node;
  }

  /**
   * Validates if the given text is a valid tag name.
   *
   * @return true if the tag name is valid, false otherwise
   */
  protected boolean isValidTagName(String text) {
    return text.matches("^[a-zA-Z:_][0-9-a-zA-Z:_]*$");
  }
}