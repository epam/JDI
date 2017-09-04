new Vue({
    el: '#newSuitsApp',
    data: {
        suits: [
            {
                name: 'Suit 1',
                cases: [
                    { name: 'Case 1'}, { name: 'Case 2'}
                ]
            },
            {
                name: 'Suit 2',
                cases: [
                    { name: 'Case 1'}, { name: 'Case 2'}
                ]
            },
            {
                name: 'Suit 3',
                cases: [
                    { name: 'Case 1'}, { name: 'Case 2'}
                ]
            }
        ]
    },
    methods: {
        getCasesAmount: function(suit) {
            return suit.cases.length;
        },
        getSuits: function() {
            axios.get("/suits").then(function(response) {
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
        //this.getSuits();
    }
});