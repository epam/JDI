// Method for creation a base form for popUp forms
function createPopUp(width, height, title){
    $(".b-popup-content").width(width);
    $(".b-popup-content").height(height);
    $(".b-popup-content").css("margin-top", ($(window).height() / 2 - $(".b-popup-content").height() / 2) + "px");
    $(".popup_name").empty();
    $(".popup_name").html(title);
    $(".popup_ok").empty();
    $(".popup_cancel").empty();
}

// Method to hide the form that is currently active
function PopUpHide(id){
    $(id).hide();
}

// Create popUp form for create new Suit
function PopUpAddingSuit(){
    // Create base form
    createPopUp(400, 200, "Add suit");

    // Cleat all fields
    $("#popup_add .popup_exception").empty();
    $("#addNameSuit").val("");
    $("#addDescriptionSuit").val("");
    $("#addPrioritySuit").val("1");
    $("#addTagsSuit").val("");

    // Add two buttons (Create and Cancel)
    $(".popup_ok").append("<div id='createSuitButton'>Create</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

    // Show popUp form
    $("#popup_add").show();
}

// Create popUp form for update current Suit
function PopUpUpdateSuit(){
    // Create base form
    createPopUp(400, 200, "Edit suit");

    // Clear field with exceptions
    $("#popup_update .popup_exception").empty();

    // Filling out the fields in accordance with the current Suit
    $("#updateNameSuit").val($("#nameSuit").text());
    $("#updateDescriptionSuit").val($("#descriptionSuit").text());
    $("#updatePrioritySuit").val($("#prioritySuit").text());
    $("#updateTagsSuit").val($("#tagsSuit").text());

    // Add two buttons (Edit and Cancel)
    $(".popup_ok").append("<div id='updateSuitButton'>Edit</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

    // Show popUp form
    $("#popup_update").show();
}

// Create popUp form for delete current Suit
function PopUpRemoveSuit(){
    // Create base form
    createPopUp(400, 150, "Delete suit");

    // Clear field with exceptions
    $("#popup_remove .popup_exception").empty();

    // Add two buttons (Delete and Cancel)
    $(".popup_ok").append("<div id='removeSuitButton'>Delete</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

    // Show popUp form
    $("#popup_remove").show();
}

// Create popUp form for create new Case
function PopUpAddCase(){
    // Create base form
    createPopUp(400, 180, "Add case");

    // Cleat all fields
    $("#popup_add_case .popup_exception").empty();
    $("#addDescriptionCase").val("");
    $("#addPriorityCase").val($("#prioritySuit").text());
    $("#addTagsCase").val("");

    // Add two buttons (Create and Cancel)
    $(".popup_ok").append("<div id='createCaseButton'>Create</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

    // Show popUp form
    $("#popup_add_case").show();
}

function PopUpRemoveCases(){
    // Create base form
    createPopUp(400, 140, "Delete case");

    // Clear field with exceptions
    $("#popup_remove_cases .popup_exception").empty();

    // Add two buttons (Delete and Cancel)
    $(".popup_ok").append("<div id='deleteCaseButton'>Delete</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

    // Show popUp form
    $("#popup_remove_cases").show();
}
