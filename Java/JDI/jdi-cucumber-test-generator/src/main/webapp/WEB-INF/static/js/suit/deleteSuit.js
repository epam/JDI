var deleteSuit = new Vue({
    el: '#deleteSuit',
    data() {
        return {
            id: -1,
        }
    },
    methods: {
        deleteSuit: function() {
            this.id = suit_id;

            axios.get('/removeTestSuit/' + this.id).then(function(response) {
                PopUpHide("#popup_delete");
                suit_id = -1;
                getSuits.getSuits();
            }).catch(function(error) {
                PopUpHide("#popup_delete");
                suit_id = -1;
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