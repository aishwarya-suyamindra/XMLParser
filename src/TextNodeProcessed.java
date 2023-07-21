/**
 * This class represents a transitionary state of an XML node. It represents the state after a
 * text of an XML node has been processed and it handles the transition to the next state, based
 * on the input character.
 */
public class TextNodeProcessed extends AbstractXMLTransitionState {
  /**
   * Initialises a TextNodeProcessed object with the given XML node.
   *
   * @param node the XML node to parse
   */
  public TextNodeProcessed(XMLNode node) {
    super(node);
  }

  @Override
  public XMLNode processInput(char c) throws InvalidXMLException {
    XMLNode currentNode = handleTransition(c, node);
    if (currentNode == null) {
      throw new InvalidXMLException("Invalid character");
    }
    return currentNode;
  }

  @Override
  public ParseState getState() {
    return ParseState.TEXT_NODE_PROCESSED;
  }
}
