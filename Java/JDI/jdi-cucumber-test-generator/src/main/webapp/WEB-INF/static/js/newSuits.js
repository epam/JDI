new Vue({
    el: '#newSuitsApp',
    data: {
        suits: []
    },
    methods: {
        getCasesAmount: function(suit) {
            return suit.cases === null ? 0 : suit.cases.length;
        },
        getSuits: function() {
            axios.get("/getTestSuits").then(function(response) {
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