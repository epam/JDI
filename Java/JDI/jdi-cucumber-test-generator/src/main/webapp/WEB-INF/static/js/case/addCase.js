var addCase = new Vue({
    el: '#addCase',
    data() {
        return{}
    },
    methods: {
        addCase: function() {
            var descriptionCase = $("#addDescriptionCase").val();
            var priorityCase = $("#addPriorityCase").val();
            var tags = $("#addTagsCase").val().split(" ");
             var tagsCase = new Array(tags.length);
                  for ( var i = 0; i < tagsCase.length; i++){
                  tagsCase[i] = {
                     "name": tags[i]
                  }
                  }
            if(descriptionCase == "" || priorityCase == ""){
                if(descriptionCase == ""){
                    $('#addDescriptionCase').addClass("emptyField");
                    setTimeout(function() {$("#addDescriptionCase").removeClass("emptyField");}, 1000);
                }
                if(priorityCase == ""){
                    $('#addPriorityCase').addClass("emptyField");
                    setTimeout(function() {$("#addPriorityCase").removeClass("emptyField");}, 1000);
                }
                errorInfoBlock("Not filled mandatory fields!");
                return;
            }

            axios.post('/cucumber/suit/' + suit_id + '/case', {description: descriptionCase, priority: priorityCase, tags: tagsCase}).then(function(response) {
                PopUpHide("#popup_add_case");
                getSuitInfo(suit_id);
                successInfoBlock();
            }).catch(function(error) {
                errorInfoBlock("Fail updating! Try again later!");
            });
        }
    },
    watch: {
        message: function() {
            console.log(this.message)
        }
    }
});