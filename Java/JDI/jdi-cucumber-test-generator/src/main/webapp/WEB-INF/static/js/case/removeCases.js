var removeCases = new Vue({
    el: '#removeCases',
    data() {
        return{}
    },
    methods: {
        removeCases: function() {
            PopUpHide("#popup_remove_cases");
            var cases = new Array();
            var i = 0;

            $('#tableCases input:checkbox').each(function() {
                if ($(this).prop("checked")) {
                    cases[i++] = { id: parseInt($(this).parent().children(".particular_caseId").val()) };
                }
            });

            axios.post('/cucumber/suit/' + suit_id + '/cases', {id: suit_id, cases: cases}).then(function(response) {
                successInfoBlock();
                getSuitInfo(suit_id);
                $(".delete-cases-button").addClass("disabled-link");
                $(".generate-feature-button").addClass("disabled-link");
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