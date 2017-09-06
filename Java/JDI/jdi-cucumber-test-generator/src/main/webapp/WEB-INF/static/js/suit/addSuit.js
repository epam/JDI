var addSuit = new Vue({
    el: '#addSuit',
    data() {
        return{}
    },
    methods: {
        addSuit: function() {
            axios.post('/addTestSuit', {name: $("#addNameSuit").val(), description: $("#addDescriptionSuit").val()}).then(function(response) {
                PopUpHide("#popup_add");
                $("#addNameSuit").val("");
                $("#addDescriptionSuit").val("");
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