package ddt.framework

import ddt.framework.model.CompareResult
import org.skyscreamer.jsonassert.JSONCompare
import org.skyscreamer.jsonassert.JSONCompareMode
import org.json.JSONException

import net.javacrumbs.jsonunit.core.internal.Diff
import static net.javacrumbs.jsonunit.JsonAssert.when
import net.javacrumbs.jsonunit.core.Option
import com.fasterxml.jackson.core.JsonParseException

import org.custommonkey.xmlunit.DetailedDiff
import org.xml.sax.SAXException

import groovy.util.logging.Log4j

/**
 * CompareUtils class contains methods to compare String, JSON and XML values.
 */
@Log4j
class CompareUtils {

    private static final String ROOT = ""
    private static final String FULL_JSON = "fullJson"

    static CompareResult compare(String expected, String actual) {
        if (!expected && !actual) {
            return new CompareResult(
                    value: true,
                    message: "Expected and Actual are null"
            )
        } else if (expected?.trim() && actual?.trim()) {

            try {

                return compareJSON(expected, actual)
            } catch (JSONException e) {
                log.info "Compared values are not valid JSON Documents"
            } catch (JsonParseException jpe) {
                log.info "Compared values are not valid JSON Documents"
            } catch (IllegalArgumentException iea) {
                log.info "Compared values are not valid JSON Documents"
                log.info "message: " + iea.cause.toString()
            }

            try {
                return compareXML(expected, actual)
            } catch (SAXException e) {

            }

            log.info "Compare values as Strings"
            return new CompareResult(
                    value: expected == actual,
                    message: "'Expected' = '${expected}' \r\n" +
                            "'Actual'  =  '${actual}'",
                    comparedType: CompareResult.TXT
            )
        } else {
            return new CompareResult(
                    value: false,
                    message: "'Expected' = '${expected}' \r\n" +
                            "'Actual'  =  '${actual}'",
                    comparedType: CompareResult.TXT
            )
        }
    }

    static CompareResult compareJSON(String expected, String actual) throws JSONException, JsonParseException {
        def diff = Diff.create(
                expected, actual, FULL_JSON, ROOT, when(Option.IGNORING_ARRAY_ORDER))

        def resultDefaultComparator = JSONCompare.compareJSON(expected, actual, JSONCompareMode.NON_EXTENSIBLE)

        def resultDdtfComparator = JsonUtils.compareJSON(expected, actual)

        log.info "Compare JSON Documents"
        return new CompareResult(
                value: resultDefaultComparator.passed(),//diff.similar()
                message: "I JSONUnit. ${diff.differences()} \r\n" +
                        "II DDTF Comparator. ${resultDdtfComparator.getMessage()} \r\n" +
                        "III Default Comparator. ${resultDefaultComparator.getMessage()} \r\n" +
                        "'Expected' = '${expected}' \r\n" +
                        "'Actual'  =  '${actual}'",
                comparedType: CompareResult.JSON
        )
    }

    static CompareResult compareXML(String expected, String actual) throws SAXException {

        def diff = XMLUtils.compareXML(expected, actual)
        DetailedDiff detailedDiff = new DetailedDiff(diff)
        List allDifferences = detailedDiff.getAllDifferences()

        return new CompareResult(
                value: diff.similar(),
                message: "Found ${allDifferences.size()} differences" +
                        (allDifferences.size() > 0 ? ": \r\n" + detailedDiff.toString() : ". \r\n") +
                        "'Expected' = '${expected}' \r\n" +
                        "'Actual'  =  '${actual}'",
                comparedType: CompareResult.XML
        )
    }
}



