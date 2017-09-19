<#if suit.tags?has_content>
${suit.tags}
</#if>
Feature: ${suit.description}
    <#list suit.cases as case>

    <#if case.tags?has_content>
    ${case.tags}
    </#if>
    Scenario: ${case.description}
         <#list case.steps as step>
         <#assign t = step.type-1>
         ${types[t].typeName} ${step.description}
         </#list>

    </#list>