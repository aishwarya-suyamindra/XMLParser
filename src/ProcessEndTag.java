/**
 * This class represents a parse state that an XML node can be in. It handles the processing of
 * the end tag of the XML node.
 */
public class ProcessEndTag extends AbstractXMLNodeParseState {
  /**
   * Initialises a ProcessEndTag object with the given XML node.
   *
   * @param node the XML node to parse
   */
  public ProcessEndTag(XMLNode node) {
    super(node);
  }

  @Override
  public XMLNode processInput(char c) throws InvalidXMLException {
    String startNodeText = node.getStartTag();
    String endNodeText = node.getEndTag();
    // If the character is '>', it denotes the end of the end tag and if a valid end tag has been
    // entered, change state.
    if (c == '>') {
      if (startNodeText.equals(endNodeText)) {
        node.updateParseState(new EndTagProcessed(node));
        return node;
      } else {
        throw new InvalidXMLException("Invalid character");
      }
    }
    endNodeText += c;
    if (isValidTagName(endNodeText) && startNodeText.startsWith(endNodeText)) {
      node.updateEndTag(c);
    } else {
      throw new InvalidXMLException("Invalid character");
    }
    return node;
  }

  @Override
  public ParseState getState() {
    return ParseState.PROCESS_END_TAG;
  }
}
