package ddt.framework.model

/**
 * Parameter model for request parameter.
 * Request parameter may require to evaluate script stored in evalScript field before processing.
 */
class Parameter {
    String name
    String value
    String evalScript

    /**
     * Compare object with input parameter.
     * @param o - object for comparison.
     * @return Return true if input parameter is the same object or if it has the same class and all fields are equal. Else return false.
     */
    boolean equalsTo(o) {
        if (this.is(o)) return true
        if (getClass() != o?.class) return false

        Parameter parameter1 = o as Parameter

        if (evalScript != parameter1?.evalScript) return false
        if (name != parameter1?.name) return false
        if (value != parameter1?.value) return false

        return true
    }
}