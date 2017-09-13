var removeSuit = new Vue({
    el: '#removeSuit',
    data() {
        return {}
    },
    methods: {
        removeSuit: function() {
            axios.delete('/removeSuit/' + suit_id).then(function(response) {
                PopUpHide("#popup_remove");
                getSuits.getSuits();
                suit_id = -1;
                $(".tab_name").removeClass("is-active");
                $("#nameSuit").empty();
                $("#descriptionSuit").empty();
                $("#prioritySuit").empty();
                $("#createDateSuit").empty();
                $("#tagsSuit").empty();
                $("#countCases").text("0");
                $("#tableCases").empty();
                $("#code-textarea").val("");
                $("#case-description-textfield").val("");
            }).catch(function(error) {
                $("#popup_remove .popup_exception").text("Try again later!");
            });
        }
    },
    watch: {
        message: function() {
            console.log(this.message)
        }
    }
});