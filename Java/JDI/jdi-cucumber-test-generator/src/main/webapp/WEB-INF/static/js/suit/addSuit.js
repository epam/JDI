var addSuit = new Vue({
    el: '#addSuit',
    data() {
        return {
            name: '',
            description: ''
        }
    },
    methods: {
        addSuit: function() {
            axios.post('/addTestSuit', {name: this.name, description: this.description}).then(function(response) {
                PopUpHide("#popup_add");
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