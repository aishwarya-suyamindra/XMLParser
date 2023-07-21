/**
 * This class represents a parse state that an XML node can be in. It handles the processing of
 * the start tag of the XML node.
 */
public class ProcessStartTag extends AbstractXMLNodeParseState {
  /**
   * Initialises a ProcessStartTag object with the given XML node.
   *
   * @param node the XML node to parse
   */
  public ProcessStartTag(XMLNode node) {
    super(node);
  }

  @Override
  public XMLNode processInput(char c) throws InvalidXMLException {
    // If the character is '>', it denotes the end of the start tag, change state.
    if (c == '>') {
      node.updateParseState(new StartTagProcessed(node));
      return node;
    }
    // If the character is a valid character that can be included in the start tag, update it
    // with the new character
    String startNodeText = node.getStartTag() + c;
    if (c != '<' && isValidTagName(startNodeText)) {
      node.updateStartTag(c);
    } else {
      throw new InvalidXMLException("Invalid character in the start tag");
    }
    return node;
  }

  @Override
  public ParseState getState() {
    return ParseState.PROCESS_START_TAG;
  }
}
