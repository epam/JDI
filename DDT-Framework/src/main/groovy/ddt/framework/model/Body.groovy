package ddt.framework.model

/**
 * Model for message body.
 * Message body may require to evaluate script stored in evalScript field before processing.
 */
class Body {
    String body
    String evalScript

    /**
     * Compare object with input parameter.
     * @param o - object for comparison.
     * @return Return true if input parameter is the same object or if it has the same class and all fields are equal. Else return false.
     */
    boolean equalsTo(o) {
        if (this.is(o)) return true
        if (getClass() != o?.class) return false

        Body body1 = o as Body

        if (body != body1?.body) return false
        if (evalScript != body1?.evalScript) return false

        return true
    }
}
