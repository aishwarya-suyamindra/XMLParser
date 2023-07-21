import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * A Junit test class for the XMLValidator class.
 */
public class XMLValidatorTest extends AbstractXMLParserTest {

  // region Input

  @Test
  public void testInputWithValidStartTag() {
    String xmlString = "<ht:ml0_->";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Incomplete";
    assertEquals(expected, obj.output());
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithoutStartTagThrows() throws InvalidXMLException {
    String xmlString = "html";
    XMLParser obj = new XMLValidator();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test
  public void testInputWithEmptyTag() {
    String xmlString = "<></>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testInputWithValidStartAndEndTagName() {
    String xmlString = "<html1:--></html1:-->";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testInputWithValidTagAndText() {
    String xmlString = "<html>Text 1</html>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testInputWithValidTagAndChildNode() {
    String xmlString = "<html><p></p></html>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testInputWithValidTagTextAndSingleChildNode() {
    String xmlString = "<html>Text 1 <h1> Heading </h1></html>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testInputWithValidTagTextAndSingleChildNodeFollowedByText() {
    String xmlString = "<html>Text 1 <h1> Heading </h1> </html>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testInputWithValidTagAndChildNodeWithText() {
    String xmlString = "<html><p>This is / a \n paragraph!</p></html>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testInputWithValidTagAndTwoChildNodes() {
    String xmlString = "<html><h1> *Heading1* </h1><h2>Heading 2</h2></html>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testInputWithValidTagTextAndTwoChildNodes() {
    String xmlString = "<html><h1></h1>Text 1 <h2></h2></html>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testInputWithValidTagTextAndTwoChildNodesFollowedByText() {
    String xmlString = "<html>This is the first text-block<h1> Heading1 </h1>1 Text 1 <h2>Heading"
            + "2</h2> This is another text-block </html>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testInputWithValidTagAndNestedChildNodesWithText() {
    String xmlString = "<html><h1> Heading1! <h2> /Heading/ </h2> Text block 1 </h1> "
            + "</html>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  //endregion

  //region output
  @Test
  public void testOutputWhenNoInputsAreProvided() {
    XMLParser obj = new XMLValidator();
    String expected = "Status:Empty";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWhenOnlyStartTagIsProvided() {
    String xmlString = "<";
    XMLParser obj = new XMLValidator();
    String expected = "Status:Incomplete";
    parse(xmlString, obj);
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWhenOnlyEntireStartTagIsProvided() {
    String xmlString = "<html>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Incomplete";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWhenIncompleteEndTagIsProvided() {
    String xmlString = "<html></html";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Incomplete";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputIncompleteXMLWithText() {
    String xmlString = "<html>Text data<";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Incomplete";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputIncompleteNestedXML() {
    String xmlString = "<html>Text data <h1>Heading 1 <h2> Sub-heading </h2> </h";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Incomplete";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputNestedXMLWithIncompleteRootTag() {
    String xmlString = "<html>Text data <h1>Heading 1 <h2> Sub-heading </h2> </h1> </html";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Incomplete";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithValidXml() {
    String xmlString = "<html></html>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithValidNestedXML() {
    String xmlString = "<html>Text <h1> Heading 1 </h1></html>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithEmptyTag() {
    String xmlString = "<></>";
    XMLParser obj = new XMLValidator();
    parse(xmlString, obj);
    String expected = "Status:Valid";
    assertEquals(expected, obj.output());
  }

  @Override
  protected XMLParser getParser() {
    return new XMLValidator();
  }
  //endregion
}