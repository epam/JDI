new Vue({
    el: '#suitsApp',
    data: {
        suits: [{ id: 1 }, { id: 2 }, { id: 3 }, { id: 4 }],
        cases: [{}, {}]
    },
    methods: {
        addTabPrefix: function(id) {
            return 'tab' + id;
        },
        addContentTabPrefix: function(id) {
          return 'content-tab' + id;
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