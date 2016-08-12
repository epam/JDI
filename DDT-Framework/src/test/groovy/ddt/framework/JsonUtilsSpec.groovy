package ddt.framework

import ddt.framework.CompareUtils
import spock.lang.Specification

class JsonUtilsSpec extends Specification {

    def "comparison returns true for equals json"() {
        expect:
        CompareUtils.compare(positive("expected${i}.json"), positive("actual${i}.json")).value

        where:
        i << [1, 4]
    }

    def "comparison returns false for different json"() {
        def result = CompareUtils.compare(expected, actual)
        println result.getMessage()
        expect:
        !result.value

        where:
        expected                   | actual
        negative("expected1.json") | negative("actual1.json")
    }

    def "comparison returns false if args are null"() {
        expect:
        !CompareUtils.compare(expected, actual).value
        where:
        expected | actual
        null     | "a"
        "a"      | null
    }

    def "comparison returns true if both args are null"() {
        expect:
        CompareUtils.compare(expected, actual).value
        where:
        expected | actual
        null     | null
        ""       | ""
        ""       | null
        null     | ""
    }

    String positive(String fileName) {
        content(fileName, "ddt/framework/positive/")
    }

    String negative(String fileName) {
        content(fileName, "ddt/framework/negative/")
    }

    String content(String name, String absPath) {
        def uri = getClass().classLoader.getResource(absPath + name).toURI()
        new File(uri).text
    }
}
