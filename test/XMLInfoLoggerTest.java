import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Junit test class for the XMLInfoLogger class.
 */
public class XMLInfoLoggerTest extends AbstractXMLParserTest {
  @Override
  protected XMLParser getParser() {
    return new XMLInfoLogger();
  }

  @Test
  public void TestOutputWhenNoInputsAreProvided() {
    XMLParser obj = new XMLInfoLogger();
    String expected = "";
    assertEquals(expected, obj.output());
  }

  @Test
  public void TestOutputWhenOnlyStartTagIsProvided() {
    String xmlString = "<";
    XMLParser obj = new XMLInfoLogger();
    String expected = "";
    parse(xmlString, obj);
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithValidStartAndEndTag() {
    String xmlString = "<html1:_-></html1:_->";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:html1:_-\nEnded:html1:_-\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithOnlyValidStartTag() {
    String xmlString = "<ht:ml9>";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:ht:ml9\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithOnlyIncompleteStartTag() {
    String xmlString = "<html";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithValidStartTagIncompleteEndTag() {
    String xmlString = "<ht:ml9></ht:ml9";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:ht:ml9\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithValidStartTagTextAndIncompleteEndTag() {
    String xmlString = "<html>Test</html";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:html\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithValidTagAndText() {
    String xmlString = "<html>Test</html>";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:html\nCharacters:Test\nEnded:html\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithValidTagTextAndIncompleteStartTagOfChildNode() {
    String xmlString = "<html>Text 1 <h1";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:html\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithValidTagTextAndStartTagOfChildNode() {
    String xmlString = "<html>Text 1 <h1>";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:html\nCharacters:Text 1 \nStarted:h1\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithValidTagTextAndSingleChildNode() {
    String xmlString = "<html>Text 1 <h1> Heading </h1>";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:html\nCharacters:Text 1 \nStarted:h1\nCharacters: "
            + "Heading \nEnded:h1\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithValidTagTextAndSingleChildNodeFollowedByText() {
    String xmlString = "<html>Text 1 <h1> Heading </h1> Text 2</html>";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:html\nCharacters:Text 1 \nStarted:h1\nCharacters: Heading "
            + "\nEnded:h1\nCharacters: Text 2\nEnded:html\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithValidTagTwoChildNodes() {
    String xmlString = "<html><h1> Heading1 </h1><h2>Heading 2</h2></html>";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:html\nStarted:h1\nCharacters: "
            + "Heading1 \nEnded:h1\nStarted:h2\nCharacters:Heading 2\nEnded:h2\nEnded:html\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithSpecialCharactersInText() {
    String xmlString = "<html>Te \n xt \t block! </html>";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:html\nCharacters:Te \n xt \t block! \nEnded:html\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputIncompleteNestedXML() {
    String xmlString = "<html>Text data <h1>Heading 1 <h2> Sub-heading </h2> <";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:html\nCharacters:Text data \nStarted:h1\nCharacters:Heading 1 "
            + "\nStarted:h2\nCharacters: Sub-heading \nEnded:h2\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputNestedXMLWithIncompleteRootTag() {
    String xmlString = "<html>Text data <h1>Heading 1 <h2> Sub-heading </h2> </h1> </html";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:html\nCharacters:Text data \nStarted:h1\nCharacters:Heading 1 "
            + "\nStarted:h2\nCharacters: Sub-heading \nEnded:h2\nCharacters: "
            + "\nEnded:h1\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithValidNestedXML() {
    String xmlString = "<html>Text <h1> Heading 1 </h1></html>";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:html\nCharacters:Text \nStarted:h1\nCharacters: Heading 1 "
            + "\nEnded:h1\nEnded:html\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithValidNestedChildXML() {
    String xmlString = "<html>Text <h1> Heading 1 <h2> Subheading</h2></h1></html>";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "Started:html\nCharacters:Text \nStarted:h1\nCharacters: Heading 1 "
            + "\nStarted:h2\nCharacters: Subheading\nEnded:h2\nEnded:h1\nEnded:html\n";
    assertEquals(expected, obj.output());
  }

  @Test
  public void testOutputWithEmptyTag() {
    String xmlString = "<></>";
    XMLParser obj = new XMLInfoLogger();
    parse(xmlString, obj);
    String expected = "";
    assertEquals(expected, obj.output());
  }
}