var deleteCase = new Vue({
    el: '#deleteCase',
    data() {
        return{}
    },
    methods: {
        deleteCase: function() {
            axios.get('/removeCase/' + case_id).then(function(response) {
                PopUpHide("#popup_delete_case");
                getSuitInfo(suit_id);
            }).catch(function(error) {
                $("#popup_delete_case .popup_exception").text("Try again later!");
            });
        }
    },
    watch: {
        message: function() {
            console.log(this.message)
        }
    }
});