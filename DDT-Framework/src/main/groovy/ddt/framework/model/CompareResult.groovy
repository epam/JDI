package ddt.framework.model

/**
 * Model for result of comparison. The value field contains value for assertion and the message field contains description for result of comparison.
 */
class CompareResult {
    final static String JSON = "json"
    final static String XML = "xml"
    final static String TXT = "txt"

    boolean value
    String message
    String comparedType
}
