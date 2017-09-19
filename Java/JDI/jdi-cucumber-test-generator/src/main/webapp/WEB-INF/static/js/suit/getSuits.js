var getSuits = new Vue({
    el: '#newSuitsApp',
    data: {
        suits: []
    },
    methods: {
        getSuits: function() {
            axios.get("/cucumber/suits").then(function(response) {
                this.suits = response.data;
                if(this.suits.length > 0){
                    if($("div.tab_name.is-active").length == 0){
                        getSuitInfo(this.suits[0].id);
                    }
                    else {
                        getSuitInfo(parseInt($("div.tab_name.is-active").parent().children("div.suitId").text()));
                    }
                }
            }.bind(this));
        }
    },
    created: function(){
        this.getSuits();
    },
    watch: {
        suits: function(){
             if($("div.tab_name.is-active").length == 0){
                 $('.accordion-tabs').children('li').first().children('div').addClass('is-active');
             }
        }
    },
    mounted: function() {
        this.getSuits();
    }
});