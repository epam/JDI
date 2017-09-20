$(document).ready(function () {
    getStepSuggestions();
});

function getStepSuggestions() {
    axios.get("/cucumber/getAutoCompleteList").then(function(response) {
        $("#steps_table").empty();

        for (var i = 0; i < response.data.length; i++) {
            console.log(response.data[i].content);

            $("#steps_table").append($('<tr>')
                .append($('<td>')
                    .append(response.data[i].id)
                    .css('visibility', 'hidden')
                )
                .append($('<td>')
                    .append(response.data[i].content)
                )
                .append($('<td>')
                    .append("<button class='delete-step'>Delete suit</button>")
                )
            )
        }

    });
}

$(document).on('click', '.delete-step', function(e)
{
    var toDelete = $(this).parent().parent().children(":first").text();

    axios.delete('/cucumber/removeAutoComplete/' + toDelete).then(function(response) {
        getStepSuggestions();
        successInfoBlock();
    }).catch(function(error) {
        errorInfoBlock("Fail deleting! Try again later!");
    });
});

$(document).on('click', '#add_step_button', function(e)
{

    var text = $("#step_suggestion").val();
    $("#step_suggestion").val("");

    axios.post('/cucumber/addAutoComplete', {content: text}).then(function(response) {
        getStepSuggestions();
        successInfoBlock();
    }).catch(function(error) {
        errorInfoBlock("Fail adding! Try again later!");
    });
});