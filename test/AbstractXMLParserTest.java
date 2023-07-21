import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Abstract class for the test classes of the implementations of XMLParser interface. It
 * contains common test methods and helper methods.
 */
public abstract class AbstractXMLParserTest {
  /**
   * Calls the input method of the parser object, character by character, for the
   * given xml string and parser object.
   *
   * @param xmlString the xml to be parsed
   * @param parser    the parser object
   */
  protected void parse(String xmlString, XMLParser parser) {
    for (int i = 0; i < xmlString.length(); i++) {
      try {
        System.out.println(xmlString.charAt(i));
        parser.input(xmlString.charAt(i));
      } catch (InvalidXMLException ex) {
        fail("Invalid xml exception occurred, but should not have");
      }
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithEmptyInput() throws InvalidXMLException {
    String xmlString = " ";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      System.out.println(xmlString.charAt(i));
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithMultipleOpeningTagsThrows() throws InvalidXMLException {
    String xmlString = "<<html>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      System.out.println(xmlString.charAt(i));
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithExtraOpeningTagInNameThrows() throws InvalidXMLException {
    String xmlString = "<htm<l>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithForwardSlashInStartTagThrows() throws InvalidXMLException {
    String xmlString = "<htm/l></html>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithMultipleClosingTagsThrows() throws InvalidXMLException {
    String xmlString = "<html></html>>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithTagNameStartingWithANumberThrows() throws InvalidXMLException {
    String xmlString = "<1root></1root>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithTagNameStartingWithHyphenThrows() throws InvalidXMLException {
    String xmlString = "<-root></-root>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithTagNameWithInvalidCharactersThrows() throws InvalidXMLException {
    String xmlString = "<root!></root!>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithOnlyEndTagThrows() throws InvalidXMLException {
    String xmlString = "</root>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithIncorrectEndTagThrows() throws InvalidXMLException {
    String xmlString = "<root></html>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithMultipleForwardSlashInEndTagThrows() throws InvalidXMLException {
    String xmlString = "<root></ro/ot>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithOpeningTagsInEndTagThrows() throws InvalidXMLException {
    String xmlString = "<root></ro<ot>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithInvalidChildTagName() throws InvalidXMLException {
    String xmlString = "<html><head></head><div><1p></p><p></p></div></html>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithInvaliWhitespaceInTag() throws InvalidXMLException {
    String xmlString = "<roo\nt>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithInvalidSpecialCharactersInTag() throws InvalidXMLException {
    String xmlString = "<roo\t !>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithValidStartTagInvalidClosingTag() throws InvalidXMLException {
    String xmlString = "<root> This is a // \n text block >";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputNestedXMLWithMultipleOpeningTagsInStartTag() throws InvalidXMLException {
    String xmlString = "<root> This is a // \n text block <hea<ding>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputNestedXMLWithMultipleOpeningTagsInEndTag() throws InvalidXMLException {
    String xmlString = "<root> This is a // \n text block <heading> </headi<ng>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputNestedXMLWithMultipleForwardSlashInEndTag() throws InvalidXMLException {
    String xmlString = "<root> This is a // \n text block <heading> <//heading>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputNestedXMLWithIncompleteEndTag() throws InvalidXMLException {
    String xmlString = "<html><head><p> This is a paragraph! </p></hea>d<div></div</html>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputNestedXMLWithParentClosedBeforeChildTag() throws InvalidXMLException {
    String xmlString = "<html><head><p> This is a paragraph! </p></html></head>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  @Test(expected = InvalidXMLException.class)
  public void testInputWithInvalidNestedXML() throws InvalidXMLException {
    String xmlString = "<html><head></hea>d><div><p></p><p></p></div></html>";
    XMLParser obj = getParser();
    for (int i = 0; i < xmlString.length(); i++) {
      obj.input(xmlString.charAt(i));
    }
  }

  protected abstract XMLParser getParser();
}
