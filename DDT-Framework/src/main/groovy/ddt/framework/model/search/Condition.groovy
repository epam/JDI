package ddt.framework.model.search

/**
 * Class representing condition during product search in AEM.
 *
 * @author mikhail_bazhenov
 */
class Condition {
    private final String rule
    private final String operator
    private final String value

    Condition(String rule, String operator, String value) {
        this.rule = rule
        this.operator = operator
        this.value = value
    }

    String getRule() {
        return rule
    }

    String getOperator() {
        return operator
    }

    String getValue() {
        return value
    }


    @Override
    public String toString() {
        return "Condition{rule='$rule', operator='$operator', value='$value'}"
    }
}