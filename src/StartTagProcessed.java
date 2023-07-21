/**
 * This class represents a transitionary state of an XML node. It represents the state after a
 * start tag has been processed and it handles the transition to the next state, based on the input
 * character.
 */
public class StartTagProcessed extends AbstractXMLTransitionState {
  /**
   * Initialises a StartTagProcessed object.
   */
  public StartTagProcessed(XMLNode node) {
    super(node);
  }

  @Override
  public XMLNode processInput(char c) throws InvalidXMLException {
    // After a start tag has been processed, if there is another closing tag, it is an invalid
    // input
    if (c == '>') {
      throw new InvalidXMLException("Invalid character");
    }

    if (c == '<') {
      // If the character is '<' and the end node is empty - it can either signify an end node or
      // the start of a new child node, else it's invalid.
      // Assume it to be an end node by default.
      String endNodeText = node.getEndTag();
      if (endNodeText.isEmpty() && bufferString.isEmpty()) {
        bufferString = "<";
      } else {
        throw new InvalidXMLException("Invalid character");
      }
      return node;
    } else {
      XMLNode currentNode = handleTransitionOnClosingTag(c, node);
      if (currentNode == null) {
        throw new InvalidXMLException("Invalid character");
      }
      return currentNode;
    }
  }

  @Override
  public ParseState getState() {
    return ParseState.PROCESSED_START_TAG;
  }
}
