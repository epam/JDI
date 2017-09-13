var removeCases = new Vue({
    el: '#removeCases',
    data() {
        return{}
    },
    methods: {
        removeCases: function() {
            PopUpHide("#popup_remove_cases");

            $('#tableCases input:checkbox').each(function() {
                if ($(this).prop("checked")) {
                    var caseId = $(this).parent().children(".particular_caseId").val();
                    axios.delete('/suit/' + suit_id + '/case/' + caseId).then(function(response) {
                        getSuitInfo(suit_id);
                        successInfoBlock();
                    }).catch(function(error) {
                        errorInfoBlock("Fail updating! Try again later!");
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