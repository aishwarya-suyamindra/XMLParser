/**
 * Abstract base class for the  transitionary state of an XML node. It
 * contains the common methods and fields that are common when a closing tag is encountered.
 */
public abstract class AbstractXMLTransitionState extends AbstractXMLNodeParseState {
  protected String bufferString;

  /**
   * Initialises the fields that are common to the concrete implementation of the
   * transitionary parse states of an XML node.
   *
   * @param node the XML node to parse
   */
  protected AbstractXMLTransitionState(XMLNode node) {
    super(node);
    this.bufferString = "";
  }

  protected XMLNode handleTransition(char c, XMLNode node) {
    XMLNode currentNode = node;
    // if the character is '/', it signifies the end node, so change state
    if (c == '/') {
      node.updateParseState(new ProcessEndTag(node));
    } else if (isValidTagName(String.valueOf(c))) {
      //it is the start of a new child node, so create a new child node with the given
      //character and reset state
      node.addChildNode();
      XMLNode childNode = node.getCurrentChildNode();
      childNode.updateStartTag(c);
      childNode.updateParseState(new ProcessStartTag(childNode));
      currentNode = childNode;
    } else {
      return null;
    }
    return currentNode;
  }

  protected XMLNode handleTransitionOnClosingTag(char c, XMLNode node) {
    XMLNode currentNode = node;
    if (bufferString.equals("<")) {
      currentNode = handleTransition(c, node);
    } else {
      // If there is no end node text, it represents a text node.
      // Update the latest text node of the element node with the character
      node.addTextNode(c);
      node.updateParseState(new ProcessTextNode(node));
    }
    return currentNode;
  }


}
