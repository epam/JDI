package ddt.framework

import spock.lang.Specification

/**
 * Test for VariablesMapping.
 */
class VariablesMappingSpec extends Specification {
    def "get one variable"() {
        def map = new VariablesMapping(maps(file))
        expect:
        map.getVariablesMap() == variables
        where:
        file               | variables
        "oneVariable.xls"  | [first: 'first value']
        "oneVariable.xlsx" | [first: 'first value']
    }

    def "empty map"() {
        def map = new VariablesMapping(maps(file))
        expect:
        map.getVariablesMap() == variables
        where:
        file         | variables
        "empty.xls"  | [:]
        "empty.xlsx" | [:]
    }

    def "commented map"() {
        def map = new VariablesMapping(maps(file))
        expect:
        map.getVariablesMap() == variables
        where:
        file             | variables
        "commented.xls"  | [:]
        "commented.xlsx" | [:]
    }

    def "empty row"() {
        def map = new VariablesMapping(maps(file))
        expect:
        map.getVariablesMap() == variables
        where:
        file               | variables
        "oneVariable.xls"  | [first: 'first value']
        "oneVariable.xlsx" | [first: 'first value']
    }

    def "get variables map"() {
        def map = new VariablesMapping(maps(file))
        expect:
        map.getVariablesMap() == variables
        where:
        file       | variables
        "map.xls"  | [first: 'first value', second: 'second value']
        "map.xlsx" | [first: 'first value', second: 'second value']
    }

    def "get duplicated variables"() {
        def map = new VariablesMapping(maps(file))
        expect:
        map.getVariablesMap() == variables
        where:
        file              | variables
        "duplicated.xls"  | [first: 'third value', second: 'second value']
        "duplicated.xlsx" | [first: 'third value', second: 'second value']
    }

    String maps(String fileName) {
        filePath(fileName, "ddt/framework/maps/")
    }

    String filePath(String name, String absPath) {
        def uri = getClass().classLoader.getResource(absPath + name).toURI()
        new File(uri).absolutePath
    }


}

