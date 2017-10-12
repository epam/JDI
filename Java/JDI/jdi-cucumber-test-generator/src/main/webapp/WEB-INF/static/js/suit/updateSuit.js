var updateSuit = new Vue({
    data() {
        return{}
    },
    methods: {
        updateSuit: function() {
            var nameSuit = $("#value_of_name_info").val();
            var descriptionSuit = $("#value_of_description_info").val();
            var prioritySuit = $("#value_of_priority_info").val();
            var creationDateSuit = $("#value_of_create_date_info").val();
            var tagsSuit = $("#value_of_tags_info").val();

            if(nameSuit == "" || prioritySuit === null){
                if(nameSuit == ""){
                    $('#value_of_name_info').addClass("emptyField");
                    setTimeout(function() {$("#value_of_name_info").removeClass("emptyField");}, 1000);
                }
                if(prioritySuit == null){
                    $('.wrapper_updatePrioritySuit').addClass("emptyField");
                    setTimeout(function() {$(".wrapper_updatePrioritySuit").removeClass("emptyField");}, 1000);
                }
                errorInfoBlock("Not filled mandatory fields!");
                return;
            }

            axios.put('/cucumber/suit/' + suit_id, {id: suit_id, name: nameSuit, description: descriptionSuit, priority: prioritySuit, creationDate: creationDateSuit, tags: tagsSuit}).then(function(response) {
                PopUpHide("#popup_update");
                getSuits.getSuits();
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
