var deleteCase = new Vue({
    el: '#deleteCase',
    data() {
        return{}
    },
    methods: {
        deleteCase: function() {
            axios.get('/removeCase/' + $("#caseId").text()).then(function(response) {
                PopUpHide("#popup_delete_case");
                getSuitInfo($(".is-active").text());
            }).catch(function(error) {
                PopUpHide("#popup_delete_case");
            });
        }
    },
    watch: {
        message: function() {
            console.log(this.message)
        }
    }
});