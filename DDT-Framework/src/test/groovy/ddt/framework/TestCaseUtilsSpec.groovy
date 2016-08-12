package ddt.framework

import spock.lang.Specification

class TestCaseUtilsSpec extends Specification {
   
    def "findTestCaseName returns defaultValue"() {
	
	    expect:
		TestCaseUtils.findTestCaseName(descExample,patternExample,inputDefaultValue) == "[TC_" + inputDefaultValue + "]"
		
        where:
        descExample               | patternExample            | inputDefaultValue
		"Try to find any Value"   | ".+"                      | "TestCase"
		"Try to find any Value"   | "(.+\\d+.+)"              | "TestCase"
		"Try to find any Value"   | "(.+(\\d+).+)"            | "TestCase"
        "Try to find any Value"   | "(.*?(TC-\\d+)-(\\d+).+)" | "TestCase"
		"Try to find any Value"   | ".+"                      | null
		"Try to find any Value"   | "(.+(\\d+).+)"            | null
		"Try to find any Value"   | "(.*?(TC-\\d+)-(\\d+).+)" | null
		"Try to find any Value"   | "(.+\\d+.+)"              | ""
		"Try to find any Value"   | "(.*?(TC-\\d+)-(\\d+).+)" | ""
		"Try to find any Value"   | ".+"                      | ""
    }
	
	def "findTestCaseName returns matched values" () {
	
	    expect:
		TestCaseUtils.findTestCaseName(descExample,patternExample,inputDefaultValue) == "[TC_" + matchedValue + "]"
		
		where:
		descExample                | patternExample                | inputDefaultValue     | matchedValue
		"Try to find any Value"    | "(.+)"                        | "TestCase"            | "Try to find any Value"
		"Try to find any Value"    | "(.+) any (.+)"               | "TestCase"            | "Try to find_Value"
		"TC-01-001 try to find..." | ".*?(\\d+).+"                 | "TestCase"            | "01"
		"TC-01-001 try to find..." | ".*?TC-(\\d+)-(\\d+).+"       | "TestCase"            | "01_001"
		"TC-01-001 try to find..." | ".*?(TC-\\d+)-(\\d+)(.+find)" | "TestCase"            | "TC-01_001"
		"TC-01-001 try to find..." | ".*?(\\d+).+"                 | "TestCase"            | "01"
		"TC-01-001 try to find..." | "TC-(\\d+)-(\\d+).+"          | "TestCase"            | "01_001"
	}
	
}