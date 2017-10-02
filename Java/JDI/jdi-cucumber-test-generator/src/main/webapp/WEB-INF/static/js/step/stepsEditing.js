$(function()
{
    $(document).on('click', '.add-step-icon', function(e)
    {
        var clickedEntry = $(this).parent().parent();

        $(clickedEntry).after("<div class=\"sortable-step-container\">\n" +
            "                                            <div>\n" +
            "                                                <img src=\"/cucumber/static/images/handle-icon.png\" class=\"handle-icon\">\n" +
            "                                                <div style=\"margin: 0; border: 1px dotted gray; width: 620px; float: left; padding: 5px;\">\n" +
            "                                                    <div class=\"select-step-type-container\">\n" +
            "                                                        <select class=\"step-type-select-tag\">\n" +
            "                                                            <option value=\"100\" disabled selected>-----</option>\n" +
            "                                                            <option value=\"0\">Given</option>\n" +
            "                                                            <option value=\"1\">When</option>\n" +
            "                                                            <option value=\"2\">Then</option>\n" +
            "                                                            <option value=\"3\">And</option>\n" +
            "                                                            <option value=\"4\">But</option>\n" +
            "                                                            <option value=\"5\">*</option>\n" +
            "                                                        </select>\n" +
            "                                                    </div>\n" +
            "                                                   <input type=\"text\" class=\"step-code-line\">\n" +
            "                                                <div style=\"clear: both; width: 0px;\"></div>\n" +
            "                                                </div>\n" +
            "                                                <img src=\"/cucumber/static/images/addRow-icon.png\" class=\"add-step-icon\">\n" +
            "                                                <img src=\"/cucumber/static/images/deleteStep-icon.png\" class=\"delete-step-icon\">\n" +
            "                                            </div>\n" +
            "                                        </div>");
    }).on('click', '.delete-step-icon', function(e)
    {

        var steps = $(".sortable-step-container");

        if (steps.length >= 2) {
            var clickedEntry = $(this).parent().parent();
            clickedEntry.remove();
        }

        if (steps.length == 1) {
            var clickedEntry = $(this).parent().parent();
            clickedEntry.remove();

            $("#steps_container").append("<a id='add_first_step_button'>Add step</a>");
        }
    }).on('click', '#add_first_step_button', function(e)
    {
        $("#steps_container").empty();

        $("#steps_container").append("<div class=\"sortable-step-container\">\n" +
            "                                            <div>\n" +
            "                                                <img src=\"/cucumber/static/images/handle-icon.png\" class=\"handle-icon\">\n" +
            "                                                <div style=\"margin: 0; border: 1px dotted gray; width: 620px; float: left; padding: 5px;\">\n" +
            "                                                    <div class=\"select-step-type-container\">\n" +
            "                                                        <select class=\"step-type-select-tag\">\n" +
            "                                                            <option value=\"100\" disabled selected>-----</option>\n" +
            "                                                            <option value=\"0\">Given</option>\n" +
            "                                                            <option value=\"1\">When</option>\n" +
            "                                                            <option value=\"2\">Then</option>\n" +
            "                                                            <option value=\"3\">And</option>\n" +
            "                                                            <option value=\"4\">But</option>\n" +
            "                                                            <option value=\"5\">*</option>\n" +
            "                                                        </select>\n" +
            "                                                    </div>\n" +
            "                                                   <input type=\"text\" class=\"step-code-line\">\n" +
            "                                                <div style=\"clear: both; width: 0px;\"></div>\n" +
            "                                                </div>\n" +
            "                                                <img src=\"/cucumber/static/images/addRow-icon.png\" class=\"add-step-icon\">\n" +
            "                                                <img src=\"/cucumber/static/images/deleteStep-icon.png\" class=\"delete-step-icon\">\n" +
            "                                            </div>\n" +
            "                                        </div>");
    });
});

$( function() {
    $( "#steps_container" ).sortable({
        revert: true,
        handle: '.handle-icon'
    });
    $( "#steps_container" ).disableSelection();
} );