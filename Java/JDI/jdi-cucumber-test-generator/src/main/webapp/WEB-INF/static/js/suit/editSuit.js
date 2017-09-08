var editSuit = new Vue({
    el: '#editSuit',
    data() {
        return{}
    },
    methods: {
        editSuit: function() {
            axios.post('/editTestSuit', {id: $("#suitId").text(), name: $("#editNameSuit").val(), description: $("#editDescriptionSuit").val()}).then(function(response) {
                PopUpHide("#popup_edit");
                getSuits.getSuits();
                getSuitInfo($("#editNameSuit").val());
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
