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
    createPopUp(400, 180, "Add suit");

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

// Create popUp form for delete current Suit
function PopUpRemoveSuit(){
    // Create base form
    createPopUp(400, 130, "Delete suit");

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
    createPopUp(400, 160, "Add case");

    // Cleat all fields
    $("#popup_add_case .popup_exception").empty();
    $("#addDescriptionCase").val("");
    $("#addPriorityCase").val($("#value_of_priority_info").val());
    $("#addTagsCase").val("");

    // Add two buttons (Create and Cancel)
    $(".popup_ok").append("<div id='createCaseButton'>Create</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

    // Show popUp form
    $("#popup_add_case").show();
}

// Create popUp form for create new Case
function PopUpTagFilter() {
    $("#tags_list").empty();

    // Create base form
    createPopUp(400, 200, "Filter cases by tags");

    axios.get("/cucumber/tags").then(function(response) {
        var tags = response.data;

        $("#tags_list").append($('<li>'))
            .append($('<input>').attr('type', 'checkbox')
                .attr('class', 'checkedAllTags')
                .attr('value', 'true'))
            .append($('<span>').text('  Show all cases'));

        for (var i = 0; i < tags.length; i++) {
            $("#tags_list").append($('<li>'))
                .append($('<input>').attr('type', 'checkbox')
                    .attr('class', 'checkedTags')
                    .attr('value', tags[i].name)
                    .prop('checked', _.includes(previouslySelectedTags, tags[i].name)))
                .append($('<span>').text('  ' + tags[i].name));
        }
    }.bind(this));

    // Add two buttons
    $(".popup_ok").append("<div id='filterCasesByTags'>Filter</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

    // Show popUp form
    $("#popup_tag_filter").show();
}

function PopUpRemoveCases(){
    // Create base form
    createPopUp(400, 130, "Delete case");

    // Clear field with exceptions
    $("#popup_remove_cases .popup_exception").empty();

    // Add two buttons (Delete and Cancel)
    $(".popup_ok").append("<div id='deleteCaseButton'>Delete</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

    // Show popUp form
    $("#popup_remove_cases").show();
}

// Create popUp form for create new Case
function PopUpManageStepSuggestions(){
    // Create base form
    createPopUp(600, 400, "Manage step suggestions");

    // Add two buttons (Create and Cancel)
    $(".popup_ok").append("<div id='createCaseButton'>Create</div>");
    $(".popup_cancel").append("<div>Cancel</div>");

    // Show popUp form
    $("#popup_manage_step_suggestions").show();
}