var addSuit = new Vue({
    el: '#addSuit',
    data() {
        return{}
    },
    methods: {
        addSuit: function() {
            var nameSuit = $("#addNameSuit").val();
            var descriptionSuit = $("#addDescriptionSuit").val();
            var prioritySuit = $("#addPrioritySuit").val();
            var tagsSuit = $("#addTagsSuit").val();

            if(nameSuit == "" || prioritySuit == ""){
                $("#popup_add .popup_exception").text("Not filled mandatory fields!");
                return;
            }

            axios.post('/addTestSuit', {name: nameSuit, description: descriptionSuit, priority: prioritySuit, tags: tagsSuit}).then(function(response) {
                PopUpHide("#popup_add");
                getSuits.getSuits();
            }).catch(function(error) {
                $("#popup_add .popup_exception").text("Try again later!");
            });
        }
    },
    watch: {
        message: function() {
            console.log(this.message)
        }
    }
});