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

// Create popUp form for edit current Suit
function PopUpEditSuit(){
    // Create base form
    createPopUp(400, 200, "Edit suit");

    // Clear field with exceptions
    $("#popup_add .popup_exception").empty();

    // Filling out the fields in accordance with the current Suit
    $("#editNameSuit").val($("#nameSuit").text());
    $("#editDescriptionSuit").val($("#descriptionSuit").text());
    $("#editPrioritySuit").val($("#prioritySuit").text());
    $("#editTagsSuit").val($("#tagsSuit").text());

    // Add two buttons (Edit and Cancel)
    $(".popup_ok").append("<div id='editSuitButton'>Edit</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

    // Show popUp form
    $("#popup_edit").show();
}

// Create popUp form for delete current Suit
function PopUpDeleteSuit(){
    // Create base form
    createPopUp(400, 150, "Delete suit");

    // Clear field with exceptions
    $("#popup_delete .popup_exception").empty();

    // Add two buttons (Delete and Cancel)
    $(".popup_ok").append("<div id='deleteSuitButton'>Delete</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

    // Show popUp form
    $("#popup_delete").show();
}

// Create popUp form for create new Case
function PopUpAddCase(){
    // Create base form
    createPopUp(400, 180, "Add case");

    // Cleat all fields
    $("#popup_add_case .popup_exception").empty();
    $("#addDescriptionCase").val("");
    $("#addPriorityCase").val("1");
    $("#addTagsCase").val("");

    // Add two buttons (Create and Cancel)
    $(".popup_ok").append("<div id='createCaseButton'>Create</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

    // Show popUp form
    $("#popup_add_case").show();
}

function PopUpDeleteCase(){
    // Create base form
    createPopUp(400, 140, "Delete case");

    // Clear field with exceptions
    $("#popup_delete_case .popup_exception").empty();

    // Add two buttons (Delete and Cancel)
    $(".popup_ok").append("<div id='deleteCaseButton'>Delete</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

    // Show popUp form
    $("#popup_delete_case").show();
}
