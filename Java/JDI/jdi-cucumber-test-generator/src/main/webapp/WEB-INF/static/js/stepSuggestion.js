"use strict";
var Store = {
    suggestion_steps: [],
    active: 0,
}

var app = new Vue({
    el: "#app",
    data: {
        Store,
        text: ""
    },
    methods:{
        computedClass: function (tab_active) {
             return Store.active == tab_active;
        },
        changeActiveTab: function (tab_active) {
             Store.active = tab_active;
        },
        addSuggestionStep: function() {
            var contentSteps = this.getFiltredSteps.map(function(step){return step.content;});
            if(!contentSteps.includes(this.text)){
                var obj = {content: this.text, type: Store.active};
                axios.post('/cucumber/step_suggestion', obj).then(function(response) {
                    Store.suggestion_steps.push(response.data);
                    successInfoBlock();
                }).catch(function(error) {
                    errorInfoBlock("Fail updating! Try again later!");
                });
                this.text = "";
            }
            else{
                errorInfoBlock("This suggestion step is already exist!");
            }
        },
        deleteSuggestion: function(suggestion_id) {
            axios.delete('/cucumber/step_suggestion/' + suggestion_id).then(function(response) {
                axios.get("/cucumber/step_suggestion").then(function(response) {
                    Store.suggestion_steps = response.data;
                });
            });
        }
    },
    computed:{
        getFiltredSteps: function() {
            return Store.suggestion_steps.filter(function(step) {
                return step.type == Store.active;
            });
        }
    }
});
axios.get("/cucumber/step_suggestion").then(function(response) {
    Store.suggestion_steps = response.data;
});
