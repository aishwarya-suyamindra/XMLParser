/**
 * This class represents a transitionary state of an XML node, after an end tag has been
 * processed. It handles the transition to the next state, based on the input
 * character.
 */
public class EndTagProcessed extends AbstractXMLTransitionState {
  /**
   * Initialises a EndTagProcessed object with the given XML node.
   *
   * @param node the XML node to parse
   */
  public EndTagProcessed(XMLNode node) {
    super(node);
  }

  @Override
  public XMLNode processInput(char c) throws InvalidXMLException {
    // After an end tag has been processed, if there is another closing tag, it is an invalid
    // input
    if (c == '>') {
      throw new InvalidXMLException("Invalid character");
    }

    if (c == '<') {
      // If the character is '<' and the end node is not empty - it denotes either an end node of
      // another tag (which should be that of the parent) or the start of a new child node, else
      // it's invalid.
      // Assume it to be an end node by default.
      String endNodeText = node.getEndTag();
      if (!endNodeText.isEmpty() && bufferString.isEmpty()) {
        bufferString = "<";
      } else {
        throw new InvalidXMLException("Invalid character");
      }
      return node;
    } else {
      XMLNode parentNode = node.getParentNode();
      if (parentNode != null) {
        XMLNode currentNode = handleTransitionOnClosingTag(c, parentNode);
        if (currentNode == null) {
          throw new InvalidXMLException("Invalid character");
        }
        return currentNode;
      }
    }
    return null;
  }

  @Override
  public ParseState getState() {
    return ParseState.PROCESSED_END_TAG;
  }
}
