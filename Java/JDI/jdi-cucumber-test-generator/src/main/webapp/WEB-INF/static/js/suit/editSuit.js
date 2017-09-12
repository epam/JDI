var editSuit = new Vue({
    el: '#editSuit',
    data() {
        return{}
    },
    methods: {
        editSuit: function() {
            var nameSuit = $("#editNameSuit").val();
            var descriptionSuit = $("#editDescriptionSuit").val();
            var prioritySuit = $("#editPrioritySuit").val();
            var tagsSuit = $("#editTagsSuit").val();

            if(nameSuit == "" || prioritySuit === null){
                if(nameSuit == ""){
                    $('#editNameSuit').addClass("emptyField");
                    setTimeout(function() {$("#editNameSuit").removeClass("emptyField");}, 1000);
                }
                if(prioritySuit == null){
                    $('.wrapper_editPrioritySuit').addClass("emptyField");
                    setTimeout(function() {$(".wrapper_editPrioritySuit").removeClass("emptyField");}, 1000);
                }
                $("#popup_edit .popup_exception").text("Not filled mandatory fields!");
                return;
            }

            axios.post('/updateSuit', {id: suit_id, name: nameSuit, description: descriptionSuit, priority: prioritySuit, tags: tagsSuit}).then(function(response) {
                PopUpHide("#popup_edit");
                getSuits.getSuits();
                getSuitInfo(suit_id);
            }).catch(function(error) {
                $("#popup_edit .popup_exception").text("Try again later!");
            });
        }
    },
    watch: {
        message: function() {
            console.log(this.message)
        }
    }
});
