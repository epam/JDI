$(function()
{
    $(document).on('click', '.add-step-icon', function(e)
    {
        var clickedEntry = $(this).parent().parent();

        $(clickedEntry).after("<div class=\"sortable-step-container\">\n" +
            "            <div class=\"step-info-handle\">\n" +
            "                <div class=\"select-step-type-container\">\n" +
            "                    <select class=\"step-type-select-tag\">\n" +
            "                        <option disabled selected>-----</option>\n" +
            "                        <option>Given</option>\n" +
            "                        <option>When</option>\n" +
            "                        <option>Then</option>\n" +
            "                        <option>And</option>\n" +
            "                        <option>But</option>\n" +
            "                    </select>\n" +
            "                </div>\n" +
            "\n" +
            "                <input type=\"text\" class=\"step-code-line\">\n" +
            "\n" +
            "                <img src=\"/static/images/deleteStep-icon.png\" class=\"delete-step-icon\">\n" +
            "            </div>\n" +
            "\n" +
            "            <div class=\"adding-new-step-div\">\n" +
            "                <img src=\"/static/images/addRow-icon.png\" class=\"add-step-icon\">\n" +
            "            </div>\n" +
            "        </div>");
    }).on('click', '.delete-step-icon', function(e)
    {
        var clickedEntry = $(this).parent().parent();
        clickedEntry.remove();
    });
});

$( function() {
    $( "#steps_container" ).sortable({
        revert: true,
        handle: '.step-info-handle'
//                cancel: ''
    });
    $( "#steps_container" ).disableSelection();
} );