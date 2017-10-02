"use strict";
var Store = {
    active: 0
};

var savedSteps = new Vue({
    el: '#steps_container',
    data: {
        savedSteps: []
    },
    methods: {
        getSavedSteps: function() {
            axios.get("/cucumber/step_suggestion").then(function(response) {
                this.savedSteps = response.data;
            }.bind(this));
        },
        getSteps: function () {
            var steps = this.savedSteps.filter(function(step) {
                return step.type == Store.active;
            });
            var usedSteps = steps.map(function(step){return step.content;});
            return usedSteps;
        }
    },
    mounted: function() {
        this.getSavedSteps();
    }
});


$( function() {
    $(document).on("focus keyup","input.step-code-line",function(event) {
        Store.active = parseInt($(this).parent().children("div.select-step-type-container").children("select").val());
        $(this).autocomplete({
            source: function(request, response) {
                var results = $.ui.autocomplete.filter(savedSteps.getSteps(), request.term);

                response(results.slice(0, 7));
            },
            minLength: 2
//            select: function (event, ui) {
//                event.preventDefault();
//                this.value = ui.item.label;
//                $(this).next().val(ui.item.value);
//            },
//            focus: function (event, ui) {
//                event.preventDefault();
//                this.value = ui.item.label;
//                $(this).next().val(ui.item.value);
//            }
        });
    });
} );