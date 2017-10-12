var removeSuit = new Vue({
    el: '#removeSuit',
    data() {
        return {}
    },
    methods: {
        removeSuit: function() {
            axios.delete('/cucumber/suit/' + suit_id).then(function(response) {
                PopUpHide("#popup_remove");
                $(".tab_name").removeClass("is-active");
                getSuits.getSuits();
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