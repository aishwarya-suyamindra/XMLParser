/**
 * This class represents a parse state that an XML node can be in. It handles the processing of
 * text in an XML node.
 */
public class ProcessTextNode extends AbstractXMLNodeParseState {
  /**
   * Initialises a ProcessTextNode object with the given XML node.
   *
   * @param node the XML node to parse
   */
  public ProcessTextNode(XMLNode node) {
    super(node);
  }

  @Override
  public XMLNode processInput(char c) throws InvalidXMLException {
    if (c == '>') {
      throw new InvalidXMLException("Invalid character");
    }
    // If the character is '>', it denotes the end of the text block, change state.
    if (c == '<') {
      node.updateParseState(new TextNodeProcessed(node));
      return node;
    }
    // Update the latest text node of the element node with the character
    node.updateTextNode(c);
    return node;
  }

  @Override
  public ParseState getState() {
    return ParseState.PROCESS_TEXT_NODE;
  }
}
