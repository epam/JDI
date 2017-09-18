var removeSuit = new Vue({
    el: '#removeSuit',
    data() {
        return {}
    },
    methods: {
        removeSuit: function() {
            axios.delete('/cucumber/suit/' + suit_id).then(function(response) {
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
                $("#case-priority-selector").val("");
                $("#case-create-date").val("");
                $("#case-tags").val("");
                $("#steps_container").empty();
                $("#tableCases").empty();
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