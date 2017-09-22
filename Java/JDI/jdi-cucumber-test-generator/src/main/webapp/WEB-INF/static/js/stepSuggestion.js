$(document).ready(function () {
    getStepSuggestions();
});

function getStepSuggestions() {
    axios.get("/cucumber/step_suggestion").then(function(response) {
        $("#steps_table").empty();
        $("#steps_table").append($('<tr>')
                                .append($('<th>').text("Step content"))
                                .append($('<th>').addClass("small_th"))
                            );
        for (var i = 0; i < response.data.length; i++) {
            $("#steps_table").append($('<tr>')
                .append($('<td>')
                    .append(response.data[i].content)
                )
                .append($('<td>')
                    .append("<button class='delete_step_button' onclick='deleteSuggestionStep(" + response.data[i].id + ")'>Delete step</button>")
                )
            )
        }
    });
}

function deleteSuggestionStep(stepId){
    axios.delete('/cucumber/step_suggestion/' + stepId).then(function(response) {
        getStepSuggestions();
        successInfoBlock();
    }).catch(function(error) {
        errorInfoBlock("Fail deleting! Try again later!");
    });
}

function addSuggestionStep(){
    var text = $("#step_suggestion").val();
    $("#step_suggestion").val("");

    axios.post('/cucumber/step_suggestion', {content: text}).then(function(response) {
        getStepSuggestions();
        savedSteps.getSavedSteps();
        successInfoBlock();
    }).catch(function(error) {
        errorInfoBlock("Fail adding! Try again later!");
    });
}

var app = new Vue({
    el: "#app",
    data: {
        suggestion_steps: [],
        active: 0
    },
    methods:{
        computedClass: function (tab_active) {
             return this.active == tab_active;
        },
        changeActiveTab: function (tab_active) {
             this.active = tab_active;
        }
    }
})