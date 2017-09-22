<#if suit.tags?has_content>
<#assign t = "">
<#list suit.tags?split(" ") as tag><#if tag?has_content><#assign t += tag?replace('^@*', '@', 'r') + " "></#if></#list>
${t?trim}
</#if>
Feature: ${suit.description}
    <#list suit.cases as case>

    <#if case.tags?has_content>
    <#assign s = "">
    <#list case.tags?split(" ") as tag><#if tag?has_content><#assign s += tag?replace('^@*', '@', 'r') + " "></#if></#list>
    ${s?trim}
    </#if>
    Scenario: ${case.description}
         <#list case.steps as step>
            <#assign t = step.type>
            ${types[t].typeName} ${step.description}
         </#list>
    </#list>