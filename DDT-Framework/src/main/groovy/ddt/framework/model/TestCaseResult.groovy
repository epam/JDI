package ddt.framework.model


/**
 * Model for test case run result. TestCaseResult object contains data that are concerned to run results of one test case.
 */
class TestCaseResult {
    String   name
	Long     time
	Boolean  failSign
	ArrayList<String> resultMessages
	LinkedHashMap<String,Object> failure
}