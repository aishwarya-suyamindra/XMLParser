import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an XML Node. An XML node is given by its start tag, end tag, text node,
 * level, its current state and other child XML nodes.
 */
public class ElementNode implements XMLNode {
  private String startNode;
  private String endNode;
  private XMLNode parentNode;
  private XMLNodeParseState state;
  private List<XMLChildElement> orderedNodeList;

  /**
   * Constructs an XMLNode and initialises it with the given level and parent node.
   *
   * @param parentNode the parentNode of the node
   */
  public ElementNode(XMLNode parentNode) {
    this.startNode = "";
    this.endNode = "";
    this.parentNode = parentNode;
    this.orderedNodeList = new ArrayList<>();
  }

  @Override
  public XMLNode processInput(char c) throws InvalidXMLException {
    if (getParseState() == ParseState.EMPTY) {
      if (c == '<') {
        this.state = new ProcessStartTag(this);
        return this;
      } else {
        throw new InvalidXMLException("Invalid character");
      }
    }
    XMLNode node = state.processInput(c);
    return node;
  }

  @Override
  public String getStartTag() {
    return this.startNode;
  }

  @Override
  public String getEndTag() {
    return this.endNode;
  }

  @Override
  public void updateStartTag(char c) {
    // If the character is an opening bracket, don't add it to the start node text
    if (c != '<') {
      this.startNode += c;
    }
  }

  @Override
  public void updateEndTag(char c) {
    this.endNode += c;
  }

  @Override
  public void addTextNode(char c) {
    this.orderedNodeList.add(new XMLChildElement(String.valueOf(c), null, ElementType.TEXT_NODE));
  }

  @Override
  public void updateTextNode(char c) {
    XMLChildElement element = this.orderedNodeList.get(this.orderedNodeList.size() - 1);
    element.updateText(c);
  }

  @Override
  public void addChildNode() {
    XMLNode node = new ElementNode(this);
    this.orderedNodeList.add(new XMLChildElement(null, node, ElementType.ELEMENT_NODE));
  }

  @Override
  public XMLNode getCurrentChildNode() {
    return this.orderedNodeList.get(this.orderedNodeList.size() - 1).getNode();
  }

  @Override
  public XMLNode getParentNode() {
    return this.parentNode;
  }

  @Override
  public ParseState getParseState() {
    if (state == null) {
      return ParseState.EMPTY;
    }
    return state.getState();
  }

  @Override
  public List<XMLChildElement> getElements() {
    return this.orderedNodeList;
  }

  @Override
  public void updateParseState(XMLNodeParseState state) {
    this.state = state;
  }
}