var updateSuit = new Vue({
    el: '#updateSuit',
    data() {
        return{}
    },
    methods: {
        updateSuit: function() {
            var nameSuit = $("#updateNameSuit").val();
            var descriptionSuit = $("#updateDescriptionSuit").val();
            var prioritySuit = $("#updatePrioritySuit").val();
            var tagsSuit = $("#updateTagsSuit").val();

            if(nameSuit == "" || prioritySuit === null){
                if(nameSuit == ""){
                    $('#updateNameSuit').addClass("emptyField");
                    setTimeout(function() {$("#updateNameSuit").removeClass("emptyField");}, 1000);
                }
                if(prioritySuit == null){
                    $('.wrapper_updatePrioritySuit').addClass("emptyField");
                    setTimeout(function() {$(".wrapper_updatePrioritySuit").removeClass("emptyField");}, 1000);
                }
                errorInfoBlock("Not filled mandatory fields!");
                return;
            }

            axios.put('/suit/' + suit_id, {id: suit_id, name: nameSuit, description: descriptionSuit, priority: prioritySuit, tags: tagsSuit}).then(function(response) {
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
