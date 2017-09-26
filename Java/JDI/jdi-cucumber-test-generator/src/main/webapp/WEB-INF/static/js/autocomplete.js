var savedSteps = new Vue({
    el: '#steps_container',
    data: {
        savedSteps: [],
    },
    methods: {
        getSavedSteps: function() {
            axios.get("/cucumber/step_suggestion").then(function(response) {
                this.savedSteps = response.data;
            }.bind(this));
        },
        getSteps: function () {
            var steps = [];
            for (var i = 0; i < this.savedSteps.length; i++) {
                steps[i] = this.savedSteps[i].content;
            }
            return steps;
        }
    },
    mounted: function() {
        this.getSavedSteps();
    }
});


$( function() {
    $(document).on("focus keyup","input.step-code-line",function(event) {
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