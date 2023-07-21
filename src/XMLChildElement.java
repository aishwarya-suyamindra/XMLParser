/**
 * This class represents a datatype to maintain the order of elements of different types of
 * elements in an xml node.
 */
public class XMLChildElement implements XMLElement {
  private String text;
  private XMLNode node;
  private ElementType nodeType;

  /**
   * Constructs an XMLElement object with the given text, node and element type.
   *
   * @param text     the text of the text node
   * @param node     the child node
   * @param nodeType the type of the element node
   */
  public XMLChildElement(String text, XMLNode node, ElementType nodeType) {
    this.text = text;
    this.node = node;
    this.nodeType = nodeType;
  }

  @Override
  public String getText() {
    return this.text;
  }

  @Override
  public void updateText(char c) {
    this.text += c;
  }

  @Override
  public XMLNode getNode() {
    return this.node;
  }

  @Override
  public ElementType getType() {
    return this.nodeType;
  }

}