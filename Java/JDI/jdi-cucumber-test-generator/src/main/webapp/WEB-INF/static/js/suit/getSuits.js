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
                else {
                     $("#suit_name_info").show();
                     $("#description_info").text("Suit description:");
                     $("#priority_info").text("Suit priority:");
                     $("#create_date_info").text("Suit create date:");
                     $("#tags_info").text("Suit tags:");
                     $("#value_of_name_info").val("");
                     $("#value_of_description_info").val("");
                     $("#value_of_priority_info").val("1");
                     $("#value_of_create_date_info").val("");
                     $("#value_of_tags_info").val("");
                     $("#countCases").text("0");
                     $(".buttons-container").empty();
                     $(".buttons-container").append(
                                                     "<div class='edit-suit-button' onclick='javascript:saveSuit()'>Save</div>" +
                                                     "<div class='delete-suit-button' onclick='javascript:PopUpRemoveSuit()'>" +
                                                     "<img src='/cucumber/static/images/trash-icon.png' style='height: 25px; margin: -5px;'></div>"
                                                      );
                     $("#steps_container").empty();
                     $("#cases_table_body").empty();
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