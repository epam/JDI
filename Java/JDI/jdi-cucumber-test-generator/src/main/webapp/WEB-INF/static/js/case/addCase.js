var addCase = new Vue({
    el: '#addCase',
    data() {
        return{}
    },
    methods: {
        addCase: function() {
            axios.post('/addCase/' + $("#suitId").text(), {description: $("#addDescriptionCase").val()}).then(function(response) {
                PopUpHide("#popup_add_case");
                $("#addDescriptionCase").val("");
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