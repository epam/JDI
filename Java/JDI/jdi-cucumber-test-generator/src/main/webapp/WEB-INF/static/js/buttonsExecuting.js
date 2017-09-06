function cancelCaseEditing() {
    $("#case-description-textfield").val('');
    $("#case-priority-textfield").val('');
    $("#case-stepsnum-textfield").val('');
    $("#code-textarea").val('');
}

function saveCase() {
    var description = $("#case-description-textfield").val();
    var priority = $("#case-priority-textfield").val();
    var numOfSteps = $("#case-stepsnum-textfield").val();
    var code = $("#code-textarea").val();


    if (description === null || description === "" ||
        priority === null || priority === "") {
        alert("Fill in all required entry field!");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/saveCase",
        data: {
            suitID: suit_id,
            description: description,
            priority: priority,
            numOfSteps: numOfSteps,
            code: code
        }, // parameters
        success : function(response) {
            alert("Success");
        },
        error: function( xhr, textStatus ) {
            alert( [ xhr.status, textStatus ] );
        }
    });
}