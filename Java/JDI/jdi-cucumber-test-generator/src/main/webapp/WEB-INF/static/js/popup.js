function createPopUp(width, height, title){
    $(".b-popup-content").width(width);
    $(".b-popup-content").height(height);
    $(".b-popup-content").css("margin-top", ($(window).height() / 2 - $(".b-popup-content").height() / 2) + "px");
    $(".popup_name").empty();
    $(".popup_name").html(title);
    $(".popup_ok").empty();
    $(".popup_cancel").empty();
}

function PopUpHide(id){
    $(id).hide();
}

function PopUpAddingSuit(){
    createPopUp(400, 150, "Add suit");
    $("#popup_add").show();
    $(".popup_ok").append("<div id='createSuitButton'>Create</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

}

function PopUpEditSuit(){
    createPopUp(400, 150, "Edit suit");
    $("#editNameSuit").val($("#nameSuit").text());
    $("#editDescriptionSuit").val($("#descriptionSuit").text());
    $("#popup_edit").show();
    $(".popup_ok").append("<div id='editSuitButton'>Edit</div>");
    $(".popup_cancel").append("<div>Cancel</div>");
}

function PopUpDeleteSuit(){
    createPopUp(400, 120, "Delete suit");
    $("#popup_delete").show();
    $(".popup_ok").append("<div id='deleteSuitButton'>Delete</div>");
    $(".popup_cancel").append("<div>Cancel</div>");
}

function PopUpAddCase(){
    createPopUp(400, 120, "Add case");
    $("#popup_add_case").show();
    $(".popup_ok").append("<div id='createCaseButton'>Create</div>");
    $(".popup_cancel").append("<div>Cancel</div>");
}

function PopUpEditCase(){
    createPopUp(400, 120, "Edit case");
    $("#popup_edit_case").show();
    $(".popup_ok").append("<div id='editCaseButton'>Edit</div>");
    $(".popup_cancel").append("<div>Cancel</div>");
}

function PopUpDeleteCase(){
    createPopUp(400, 120, "Delete case");
    $("#popup_delete_case").show();
    $(".popup_ok").append("<div id='deleteCaseButton'>Delete</div>");
    $(".popup_cancel").append("<div>Cancel</div>");
}
