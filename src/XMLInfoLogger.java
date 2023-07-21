import java.util.List;

/**
 * This class represents an XML Info logger.
 */
public class XMLInfoLogger extends AbstractXMLParser {
  /**
   * Constructs an XML Info Logger object.
   */
  public XMLInfoLogger() {
    super();
  }

  @Override
  protected XMLParser getParser() {
    return this;
  }

  /**
   * Provides the output of the parser, given all the inputs it has been provided
   * so far.
   *
   * @return a string that represents the parts of the input that have been successfully processed
   *         up to this point in the format:
   *         <ul>
   *          <li>Started: *tagname* - If a start tag tag has been entered </li>
   *          <li>Characters: *Characters* - The characters that are not part of a tag, all on one
   *                                 line, only if there are followed by a valid start or end tag.
   *                                 </li>
   *          <li>Ended: *tagname*- If an end tag tag has been entered. </li>
   *        </ul>
   *        A new line is added after the last line.
   */
  @Override
  public String output() {
    String output = "";
    return buildOutput(rootNode, output);
  }

  private String buildOutput(XMLNode node, String output) {
    ParseState state = node.getParseState();
    String startNodeText = node.getStartTag();
    String endNodeText = node.getEndTag();
    // If there is text in the start tag and the start tag is not currently being processed, add
    // it to the output string
    if (!startNodeText.isEmpty() && state != ParseState.PROCESS_START_TAG) {
      output += "Started:" + startNodeText + "\n";
    }
    // Add the text and child elements
    List<XMLChildElement> orderedNodes = node.getElements();
    for (int i = 0; i < orderedNodes.size(); i++) {
      XMLChildElement element = orderedNodes.get(i);
      if (element.getType() == ElementType.TEXT_NODE) {
        // Add characters from a text node only if it is followed by a valid start/ end tag.
        boolean isToAddTextNode = state == ParseState.PROCESSED_END_TAG;
        if ((i + 1) < orderedNodes.size()
                && orderedNodes.get(i + 1).getType() == ElementType.ELEMENT_NODE
                && !isToAddTextNode) {
          ParseState nextNodeState = orderedNodes.get(i + 1).getNode().getParseState();
          isToAddTextNode = nextNodeState != ParseState.PROCESS_START_TAG
                  && nextNodeState != ParseState.PROCESS_END_TAG;
        }
        if (isToAddTextNode) {
          output += "Characters:" + element.getText() + "\n";
        }
      } else {
        output += buildOutput(element.getNode(), "");
      }
    }
    // If there is text in the end tag and the end tag is not currently being processed, add
    // it to the output string
    if (!endNodeText.isEmpty() && node.getParseState() != ParseState.PROCESS_END_TAG) {
      output += "Ended:" + endNodeText + "\n";
    }
    return output;
  }
}

