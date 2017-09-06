var deleteSuit = new Vue({
    el: '#deleteSuit',
    data() {
        return {}
    },
    methods: {
        deleteSuit: function() {
            axios.get('/removeTestSuit/' + $("#suitId").text()).then(function(response) {
                PopUpHide("#popup_delete");
                getSuits.getSuits();
                $("#suitId").empty();
                $("#nameSuit").empty();
                $("#descriptionSuit").empty();
                $("#countCases").text("0");

            }).catch(function(error) {
                PopUpHide("#popup_delete");
                getSuits.getSuits();
            });
        }
    },
    watch: {
        message: function() {
            console.log(this.message)
        }
    }
});