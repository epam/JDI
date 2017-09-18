Feature: ${suit.description}

    <#list suit.cases as case>
    Scenario: ${case.description}
         <#list case.steps as step>
         <#assign t = step.type-1>
         ${types[t].typeName} ${step.description}
         </#list>

    </#list>