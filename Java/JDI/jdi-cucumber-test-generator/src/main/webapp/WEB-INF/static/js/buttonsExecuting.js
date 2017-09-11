function cancelCaseEditing() {
    $.get("/getCase/" + case_id, function(response){
        $("#case-description-textfield").val(response.description);
        $("#case-priority-selector").val(response.priority);
        $("#case-create-date").val(response.creationDate);
        $("#case-tags").val(response.tags);
        $("#case-save-exception").text("");
    });
}

function saveCase() {
    var description = $("#case-description-textfield").val();
    var priority = $("#case-priority-selector").val();
    var tags = $("#case-tags").val();

    if (description === null || description === "") {
        $("#case-save-exception").text("Not filled mandatory fields!");
        return;
    }

    var formData = {
        "id": case_id,
        "description": description,
        "tags": tags
    };

    $.ajax({
        type: "POST",
        url: "/updateCase",
        contentType : 'application/json',
        data: JSON.stringify(formData),
        success : function(response) {
            getSuitInfoWithOutCleanCases(suit_id);
            $("#case-save-exception").text("");
        },
        error: function( xhr, textStatus ) {
            alert( [ xhr.status, textStatus ] );
        }
    });
}

function removeCases() {
    var description = $("#case-description-textfield").val();
    var priority = $("#case-priority-selector").val();

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
        }, // parameters
        success : function(response) {
            getSuitInfo(suit_id);
            $("#case-save-exception").text("");
        },
        error: function( xhr, textStatus ) {
            alert( [ xhr.status, textStatus ] );
        }
    });
}

function disableCaseButtons () {
    var flag = false;

    $('#tableCases input:checkbox').each(function() {
        if ($(this).prop("checked")) {
            flag = true;
        }
    });

    if (!flag) { // если ни один checkbox не отмечен
        $(".delete-cases-button").addClass("disabled-link");
        $(".generate-feature-button").addClass("disabled-link");
    } else {
        $(".delete-cases-button").removeClass("disabled-link");
        $(".generate-feature-button").removeClass("disabled-link");
    }
}

$("#tableCases").on("change", "input", function(){
    var flag = false;

    $('#tableCases input:checkbox').each(function() {
        if ($(this).prop("checked")) {
            flag = true;
        }
    });

    if (!flag) { // если ни один checkbox не отмечен
        $(".delete-cases-button").addClass("disabled-link");
        $(".generate-feature-button").addClass("disabled-link");
    } else {
        $(".delete-cases-button").removeClass("disabled-link");
        $(".generate-feature-button").removeClass("disabled-link");
    }
});
