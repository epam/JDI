function cancelCaseEditing() {
    getCaseInfo();
}

function saveCase() {
    var description = $("#value_of_description_info").val();
    var priority = $("#value_of_priority_info").val();
    var tags = $("#value_of_tags_info").val();

    var keyWordsArray = $(".step-type-select-tag");
    var stepsArray = $(".step-code-line");

    var descriptionIsEmpty = false;
    var someDropdownsAreEmpty = false;

    if (description === null || description === "") {
        if(description === null || description === ""){
            $('#case-description-textfield').addClass("emptyField");
        }
        descriptionIsEmpty = true;
    }

     var elemsTotal = stepsArray.length;
     for (var i  = 0; i < elemsTotal; i++) {
         if ($(keyWordsArray[i]).val() === null) {
             $(keyWordsArray[i]).parent().addClass("emptyField");
             someDropdownsAreEmpty = true;
         }
     }

     if (someDropdownsAreEmpty || descriptionIsEmpty) {
         setTimeout(function() {$(".emptyField").removeClass("emptyField");}, 2000);
         errorInfoBlock("Not filled mandatory fields!");
         return;
     }

    var steps = new Array($(".step-code-line").length);
    for(var i = 0; i < steps.length; i++){
        var step_line = $($(".step-code-line")[i]).val();
        var step_type = $($(".step-type-select-tag")[i]).val();
        steps[i] = {
            "description": step_line,
            "rowNumber": i,
            "type": parseInt(step_type)
        };
    }

    var formData = {
        id: case_id,
        description: description,
        priority: priority,
        tags: tags,
        steps: steps
    };

    $.ajax({
        type: "PUT",
        url: "/cucumber/suit/" + suit_id + "/case/" + case_id,
        contentType : 'application/json',
        data: JSON.stringify(formData),
        success : function(response) {
            getSuitInfoWithOutCleanCases(suit_id);
            successInfoBlock();
        },
        error: function( xhr, textStatus ) {
            errorInfoBlock("Fail updating! Try again later!");
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
        url: "/cucumber/saveCase",
        data: {
            suitID: suit_id,
            caseId: case_id,
            description: description,
            priority: priority,
        },
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

    if (!flag) {
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

    if (!flag) {
        $(".delete-cases-button").addClass("disabled-link");
        $(".generate-feature-button").addClass("disabled-link");
    } else {
        $(".delete-cases-button").removeClass("disabled-link");
        $(".generate-feature-button").removeClass("disabled-link");
    }
});

function generateFile(){
    var arrayCasesId = new Array();
    var i = 0;

    $('#tableCases input:checkbox').each(function() {
        if ($(this).prop("checked")) {
            var caseId = $(this).parent().children(".particular_caseId").val();
            arrayCasesId[i++] = { id: parseInt(caseId) };
        }
    });

    var formData = {
        id: suit_id,
        cases: arrayCasesId
    };

     $.ajax({
            type: "POST",
            url: "/cucumber/downloadFeatureFile",
            contentType : 'application/json',
            data: JSON.stringify(formData), // parameters
            success : function(response) {
            // Add in Blob text for .feature file
                var blob = new Blob([response], {type: "text/plain;charset=utf-8"});
                saveAs(blob, "main.feature");
            },
            error: function( xhr, textStatus ) {
                alert( [ xhr.status, textStatus ] );
            }
        });
}
