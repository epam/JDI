function createPopUp(width, height, title, functionOk){
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
    var func = function(){

    };
    createPopUp(400, 150, "Adding suit", func);
    $(".popup_container").append("<table><tr><td>Name:</td><td><input id='nameSuit' v-model='name' type='text'/></td></tr><tr><td>Description:</td><td><input id='descriptionSuit' v-model='description' type='text'/></td></tr></table>");
    $("#popup_add").show();
    $(".popup_ok").append("<div id='createSuitButton'>Create</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

}

function PopUpDeleteSuit(){
var func = function(){

    };
    createPopUp(400, 120, "Deleting suit", func);
    $(".popup_container").append("<p>Do you want to delete this suit?</p>");
    $("#popup_delete").show();
    $(".popup_ok").append("<div id='createSuitButton'>Delete</div>");
    $(".popup_cancel").append("<div>Cancel</div>");
}