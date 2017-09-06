var editSuit = new Vue({
    el: '#editSuit',
    data() {
    },
    methods: {
        editSuit: function() {
            axios.post('/editTestSuit', {id: $("#suitId").text(), name: $("#editNameSuit").val(), description: $("#editDescriptionSuit").val()}).then(function(response) {
                PopUpHide("#popup_edit");
                getSuits.getSuits();
            }).catch(function(error) {
            });
        }
    },
    watch: {
        message: function() {
            console.log(this.message)
        }
    }
});
editSuit.name = $("#nameSuit").text();
editSuit.description = $("#descriptionSuit").text();
