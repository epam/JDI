Feature: ${suit.description}

    <#list suit.cases as case>
    Scenario: ${case.description}
         <#list case.steps as step>
            <#switch step.type>
           <#case 0>
           Given ${step.description}
           <#break>
           <#case 1>
           When ${step.description}
           <#break>
           <#case 2>
           Then ${step.description}
           <#break>
           <#case 3>
           And ${step.description}
           <#break>
           <#case 4>
           But ${step.description}
           <#break>
           <#case 5>
           * ${step.description}
           <#break>
           </#switch>
         </#list>

    </#list>