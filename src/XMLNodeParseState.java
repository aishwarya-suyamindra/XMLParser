/**
 * This interface represents the operations that can be performed by the different parse states
 * that an XML node can be in.
 */
public interface XMLNodeParseState {
  /**
   * Processes the given character and returns the updated xml node as a result
   * of processing this character.
   *
   * @param c The input character to process
   * @return the updated XMLNode
   * @throws InvalidXMLException if the input character is invalid for the current parse state the
   *                             xml node is in
   */
  XMLNode processInput(char c) throws InvalidXMLException;

  /**
   * Returns the current state.
   *
   * @return the current parse state
   */
  ParseState getState();
}