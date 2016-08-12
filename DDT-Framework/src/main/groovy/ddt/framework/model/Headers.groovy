package ddt.framework.model

/**
 * Model for request Headers.
 * Headers may require to evaluate script stored in evalScript field before processing.
 */
class Headers {
    String headers
    String evalScript

    /**
     * Compare object with input parameter.
     * @param o - object for comparison.
     * @return Return true if input parameter is the same object or if it has the same class and all fields are equal. Else return false.
     */
    boolean equalsTo(o) {
        if (this.is(o)) return true
        if (getClass() != o?.class) return false

        Headers headers1 = o as Headers

        if (headers != headers1?.headers) return false
        if (evalScript != headers1?.evalScript) return false

        return true
    }
}
