var addCase = new Vue({
    el: '#addCase',
    data() {
        return{}
    },
    methods: {
        addCase: function() {
            var descriptionCase = $("#addDescriptionCase").val();
            var priorityCase = $("#addPriorityCase").val();
            var tagsCase = $("#addTagsCase").val();

            if(descriptionCase == "" || priorityCase == ""){
                $("#popup_add_case .popup_exception").text("Not filled mandatory fields!");
                return;
            }

            axios.put('/addCase/suit/' + suit_id, {description: descriptionCase, priority: priorityCase, tags: tagsCase}).then(function(response) {
                PopUpHide("#popup_add_case");
                getSuitInfo(suit_id);
            }).catch(function(error) {
                $("#popup_add_case .popup_exception").text("Try again later!");
            });
        }
    },
    watch: {
        message: function() {
            console.log(this.message)
        }
    }
});