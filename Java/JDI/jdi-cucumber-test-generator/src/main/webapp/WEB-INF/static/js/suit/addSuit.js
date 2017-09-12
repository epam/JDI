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
                if(nameSuit == ""){
                    $('#addNameSuit').addClass("emptyField");
                    setTimeout(function() {$("#addNameSuit").removeClass("emptyField");}, 1000);
                }
                if(prioritySuit == ""){
                    $('#addPrioritySuit').addClass("emptyField");
                    setTimeout(function() {$("#addPrioritySuit").removeClass("emptyField");}, 1000);
                }
                $("#popup_add .popup_exception").text("Not filled mandatory fields!");
                return;
            }

            axios.post('/addSuit', {name: nameSuit, description: descriptionSuit, priority: prioritySuit, tags: tagsSuit}).then(function(response) {
                PopUpHide("#popup_add");
                successInfoBlock();
                getSuits.getSuits();
            }).catch(function(error) {
                errorInfoBlock();
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