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

    var keyWordsArray = $(".step-type-select-tag");
    var stepsArray = $(".step-code-line");


    var descriptionIsEmpty = false;
    var someDropdownsAreEmpty = false;


    if (description === null || description === "") {
        if(description === null || description === ""){
            $('#case-description-textfield').addClass("emptyField");
        }
        $("#case-save-exception").text("Not filled mandatory fields!");
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
            //alert( [ xhr.status, textStatus ] );
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

function generateFile(){
    var arrayCasesId = new Array();
    var i = 0;
    $('#tableCases input:checkbox').each(function() {
        var caseId = $(this).parent().children(".particular_caseId").val();
        arrayCasesId[i++] = caseId;
    });

     $.ajax({
            type: "POST",
            url: "/downloadFeatureFile",
            data: {
                suitId: suit_id,
                caseIds: arrayCasesId
            }, // parameters
            success : function(response) {
                alert("Success!");
            },
            error: function( xhr, textStatus ) {
                alert( [ xhr.status, textStatus ] );
            }
        });
}
