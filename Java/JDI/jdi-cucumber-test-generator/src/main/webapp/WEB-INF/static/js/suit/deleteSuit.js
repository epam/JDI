var deleteSuit = new Vue({
    el: '#deleteSuit',
    data() {
        return {}
    },
    methods: {
        deleteSuit: function() {
            axios.get('/removeSuit/' + suit_id).then(function(response) {
                PopUpHide("#popup_delete");
                getSuits.getSuits();
                suit_id = -1;
                $("#nameSuit").empty();
                $("#descriptionSuit").empty();
                $("#countCases").text("0");
                $("#tableCases").empty();
                $("#code-textarea").val("");
                $("#case-description-textfield").val("");
            }).catch(function(error) {
                $("#popup_delete .popup_exception").text("Try again later!");
            });
        }
    },
    watch: {
        message: function() {
            console.log(this.message)
        }
    }
});