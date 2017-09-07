function cancelCaseEditing() {
    $.get("/getCase/" + $("#caseId").text(), function(response){
        $("#code-textarea").val(response.steps);
        $("#case-description-textfield").val(response.description);
        $("#case-priority-textfield").val(response.priority);
    });
}

function saveCase() {
    var description = $("#case-description-textfield").val();
//    var priority = $("#case-priority-textfield").val();
    var code = $("#code-textarea").val();
    var suit_id = $("#suitId").text();
    var case_id = $("#caseId").text();

    // alert(description);
    // alert(priority);
    // alert(code);
    // alert(case_id);

    if (description === null || description === "") {
            alert("Fill in all required entry field!");
            return;
        }


    var formData = {
        "id": case_id,
        "description": description,
        "steps": code
    };

    $.ajax({
        type: "POST",
        url: "/updateCase",
        contentType : 'application/json',
        data: JSON.stringify(formData),
        success : function(response) {
            getSuitInfoWithOutCleanCases($(".is-active").text());
        },
        error: function( xhr, textStatus ) {
            alert( [ xhr.status, textStatus ] );
        }
    });
}

function removeCases() {
    var description = $("#case-description-textfield").val();
    var priority = $("#case-priority-textfield").val();
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
            caseId: case_id,
            description: description,
            priority: priority,
            code: code
        }, // parameters
        success : function(response) {
            getSuitInfo($(".is-active").text());
        },
        error: function( xhr, textStatus ) {
            alert( [ xhr.status, textStatus ] );
        }
    });
}