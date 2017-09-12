var deleteCases = new Vue({
    el: '#deleteCases',
    data() {
        return{}
    },
    methods: {
        deleteCases: function() {
            PopUpHide("#popup_delete_case");

            $('#tableCases input:checkbox').each(function() {
                if ($(this).prop("checked")) {

                    var caseId = $(this).parent().children(".particular_caseId").val();

                    axios.get('/removeCase/' + caseId).then(function(response) {
                        getSuitInfo(suit_id);
                    }).catch(function(error) {
                        $("#popup_delete_case .popup_exception").text("Try again later!");
                    });
                }
            });
        }
    },
    watch: {
        message: function() {
            console.log(this.message)
        }
    }
});