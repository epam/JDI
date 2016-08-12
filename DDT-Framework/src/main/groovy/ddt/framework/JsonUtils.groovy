package ddt.framework

import org.skyscreamer.jsonassert.comparator.DefaultComparator
import org.skyscreamer.jsonassert.JSONCompareResult
import org.skyscreamer.jsonassert.JSONCompare
import org.skyscreamer.jsonassert.JSONCompareMode
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import groovy.util.logging.Log4j

@Log4j
class JsonUtils {

    static JSONCompareResult compareJSON(String expected, String actual) throws JSONException {
        def result = JSONCompare.compareJSON(expected, actual, new DdtfJSONComparator(JSONCompareMode.NON_EXTENSIBLE))
        return result
    }

    static protected class DdtfJSONComparator extends DefaultComparator {

        protected JSONCompareMode mode

        public DdtfJSONComparator(JSONCompareMode mode) {
            super(mode)
            this.mode = mode
        }

        // This is expensive (O(n^2) -- yuck), but may be the only resort for some cases with loose array ordering, and no
        // easy way to uniquely identify each element.
        @Override
        protected void recursivelyCompareJSONArray(String key, JSONArray expected, JSONArray actual,
                                                   JSONCompareResult result) throws JSONException {
            def matched = new HashSet<Integer>()
            def unMatched = new HashSet<Integer>()
            for (int i = 0; i < expected.length(); ++i) {
                def expectedElement = expected.get(i)
                def matchFound = false
                for (int j = 0; j < actual.length(); ++j) {
                    def actualElement = actual.get(j)
                    if (matched.contains(j) || !actualElement.getClass().equals(expectedElement.getClass())) {
                        continue
                    }
                    if (expectedElement instanceof JSONObject) {
                        def candidateResult = compareJSON(expectedElement as JSONObject, actualElement as JSONObject)
                        if (candidateResult.passed()) {
                            matched.add(j)
                            matchFound = true
                            break
                        }
                    } else if (expectedElement instanceof JSONArray) {
                        def candidateResult = compareJSON(expectedElement as JSONArray, actualElement as JSONArray)
                        if (candidateResult.passed()) {
                            matched.add(j)
                            matchFound = true
                            break
                        }
                    } else if (expectedElement.equals(actualElement)) {
                        matched.add(j)
                        matchFound = true
                        break
                    }
                }
                if (!matchFound) {
                    unMatched.add(i)
                    //result.fail("$key[$i] Could not find match for element $expectedElement")
                    //result.fail("$key[$i] Could not find match for element ${bestCandidateResult?.message}")
                    //return
                }
            }

            for (int i : unMatched) {
                def expectedElement = expected.get(i)
                def bestCandidateScore = -1
                def bestCandidate = null
                def bestCandidateResult = null
                for (int j = 0; j < actual.length(); ++j) {
                    def actualElement = actual.get(j)
                    if (matched.contains(j) || !actualElement.getClass().equals(expectedElement.getClass())) {
                        continue
                    }
                    if (expectedElement instanceof JSONObject) {
                        def candidateResult = compareJSON(expectedElement as JSONObject, actualElement as JSONObject)
                        def candidateScore = candidateResult.fieldFailures.size()

                        if (bestCandidateScore < 0 || bestCandidateScore > candidateScore) {
                            bestCandidate = j
                            bestCandidateResult = candidateResult
                            bestCandidateScore = candidateScore
                        }
                    } else if (expectedElement instanceof JSONArray) {
                        def candidateResult = compareJSON(expectedElement as JSONArray, actualElement as JSONArray)
                        def candidateScore = candidateResult.fieldFailures.size()

                        if (bestCandidateScore < 0 || bestCandidateScore > candidateScore) {
                            bestCandidateScore = candidateScore
                            bestCandidate = j
                            bestCandidateResult = candidateResult
                        }
                    } else if (!expectedElement.equals(actualElement)) {
                        bestCandidate = j
                        bestCandidateResult = (new JSONCompareResult()).fail("", expectedElement, actualElement)
                    }
                }
                result.fail("$key[$i]${bestCandidateResult.isFailureOnField() ? '.' : ' '}${bestCandidateResult?.message}");
                matched.add(bestCandidate)
            }
        }
    }

}
