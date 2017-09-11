var getSuits = new Vue({
    el: '#newSuitsApp',
    data: {
        suits: []
    },
    methods: {
        getSuits: function() {
            axios.get("/getAllSuits").then(function(response) {
                this.suits = response.data;
            }.bind(this));
        }
    },
    watch: {
        message: function() {
            console.log(this.message)
        }
    },
    mounted: function() {
        this.getSuits();
    }
});