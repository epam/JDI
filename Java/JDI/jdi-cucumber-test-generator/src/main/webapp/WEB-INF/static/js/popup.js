function createPopUp(width, height, title){
    $(".b-popup-content").width(width);
    $(".b-popup-content").height(height);
    $(".b-popup-content").css("margin-top", ($(window).height() / 2 - $(".b-popup-content").height() / 2) + "px");
    $(".popup_name").empty();
    $(".popup_name").html(title);
    $(".popup_container").empty();
    $(".popup_ok").empty();
    $(".popup_cancel").empty();
}

function PopUpHide(id){
    $(id).hide();
}

function PopUpAddingSuit(){
    createPopUp(400, 150, "Adding suit");
    $("#popup_add").show();
    $(".popup_ok").append("<div id='createSuitButton'>Create</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

}

function PopUpDeleteSuit(){
    createPopUp(400, 120, "Deleting suit");
    $(".popup_container").append("<p>Do you want to delete this suit?</p>");
    $("#popup_delete").show();
    $(".popup_ok").append("<div id='createSuitButton'>Delete</div>");
    $(".popup_cancel").append("<div>Cancel</div>");
}