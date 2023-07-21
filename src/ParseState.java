/**
 * This enum represents the various states an XML node can be in when characters are being parsed.
 */
public enum ParseState {
  EMPTY {
    @Override
    public String getNodeState() {
      // The state of the node when there are no characters parsed yet is empty.
      return "Empty";
    }
  },
  PROCESS_START_TAG,
  PROCESS_END_TAG,
  PROCESS_TEXT_NODE,
  PROCESSED_START_TAG,
  PROCESSED_END_TAG {
    @Override
    public String getNodeState() {
      // The state of the node when the end tag of the node has been processed is valid.
      return "Valid";
    }
  },
  TEXT_NODE_PROCESSED;

  /**
   * Returns the current state of the node. It is incomplete by default.
   *
   * @return the current state of the node.
   */
  public String getNodeState() {
    return "Incomplete";
  }
}

