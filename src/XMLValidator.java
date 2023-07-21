/**
 * This class represents an XML Validator. It takes in the input and reports whether the
 * characters given to it until then form valid XML.
 */
public class XMLValidator extends AbstractXMLParser {
  /**
   * Constructs an XML validator object.
   */
  public XMLValidator() {
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
   * @return Returns a string in the format *Status:CurrentStatus*
   */
  @Override
  public String output() {
    ParseState state = rootNode.getParseState();
    return "Status:" + state.getNodeState();
  }
}

