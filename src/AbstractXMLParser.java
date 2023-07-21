/**
 * This class is an abstract base class for the implementation of XMLParser. It contains the
 * method implementations that are common to the concrete implementations of the XMLParser
 * interface.
 */
public abstract class AbstractXMLParser implements XMLParser {
  protected final XMLNode rootNode;
  protected XMLNode currentNode;

  /**
   * Initialises the fields that are common to the concrete implementations of the XML Parser
   * interface.
   */
  protected AbstractXMLParser() {
    this.rootNode = new ElementNode(null);
    this.currentNode = rootNode;
  }

  @Override
  public XMLParser input(char c) throws InvalidXMLException {
    if (isToProcessInput()) {
      this.currentNode = currentNode.processInput(c);
      return getParser();
    } else {
      throw new InvalidXMLException("Invalid input character");
    }
  }

  /**
   * Validates if the current input is to be processed for the root node. Validation is done based
   * on the start and end text of the root node.
   *
   * @return true if the start and end tag of the root tag are not the same or if either of them
   *              are null, false otherwise
   */
  private boolean isToProcessInput() {
    String startNodeText = rootNode.getStartTag();
    String endNodeText = rootNode.getEndTag();
    if (startNodeText.isEmpty() || endNodeText.isEmpty()) {
      return true;
    }
    return !(startNodeText.equals(endNodeText)
            && rootNode.getParseState() == ParseState.PROCESSED_END_TAG);
  }

  @Override
  public abstract String output();

  /**
   * Creates and returns an XMLParser object.
   *
   * @return an XMLParser object
   */
  protected abstract XMLParser getParser();
}
