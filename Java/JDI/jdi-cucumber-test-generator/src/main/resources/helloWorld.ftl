<html>
    <head>
        <title>
            ${title}
        </title>
        <style type="text/css">
            li {
                    list-style-type: none;
                }
        </style>
    </head>

    <body>
        <h1>${title}</h1>
        <br>
        <p>Example object:</p><br>
        ID: ${exampleObject.id}<br>
        Row number: ${exampleObject.rowNumber}<br>
        Description: "${exampleObject.description}"<br>
        Step type: ${exampleObject.type}<br>

        <p></p>
        <ul>
            <#list steps as step>
                <li>${step_index+1})   ID: "${step.id}", Row #${step.rowNumber}, Description: "${step.description}",
                Step type:
                <#switch step.type>
                    <#case 0>
                        Given
                        <#break>
                    <#case 1>
                        When
                        <#break>
                    <#case 2>
                        Then
                        <#break>
                    <#case 3>
                        And
                        <#break>
                    <#case 4>
                        But
                        <#break>
                    <#case 5>
                        *
                        <#break>
                </#switch>
                </li>
            </#list>
        </ul>
    </body>
</html>