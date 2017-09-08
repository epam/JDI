 var suit_id = -1;

$(document).ready(function () {
    $('.accordion-tabs').children('li').first().children('div').addClass('is-active').next().addClass('is-open').show();
    $('.accordion-tabs').on('click', 'li > div', function(event) {
        if (!$(this).hasClass('is-active')) {
            event.preventDefault();
            $('.accordion-tabs .is-open').removeClass('is-open').hide();
            $(this).next().toggleClass('is-open').toggle();
            $('.accordion-tabs').find('.is-active').removeClass('is-active');
            $(this).addClass('is-active');
        } else {
            event.preventDefault();
        }
    });

    $("#tableCases").on("click", "tr", function(){
        $("#tableCases").children("tr").removeClass("is_active");
        $(this).addClass("is_active");
        $("#caseId").text($(this).children("td.small_td").children(".particular_caseId").val());
        $("#code-textarea").val("");
        $("#case-description-textfield").val("");
        $("#case-priority-textfield").val("");
        $.get("/getCase/" + $("#caseId").text(), function(response){
            $("#code-textarea").val(response.steps);
            $("#case-description-textfield").val(response.description);
            $("#case-priority-textfield").val(response.priority);
        });
    });
});

function getSuitInfo(suit_name){
    $.get("/getSuitByName/" + suit_name, function(response){
        $("#suitId").text(response.id);
        $("#nameSuit").text(response.name);
        $("#descriptionSuit").text(response.description);
        $("#countCases").text(response.cases.length);
        $("#code-textarea").val("");
        $("#case-description-textfield").val("");
        $("#tableCases").empty();
        for(var i = 0; i < response.cases.length; i++){
            $("#tableCases").append($('<tr>')
                                .append($('<td>')
                                    .addClass('small_td')
                                    .append($('<input>')
                                        .attr('type', 'checkbox')
                                    )
                                    .append($('<input>')
                                        .addClass('particular_caseId')
                                        .attr('type', 'hidden')
                                        .val(response.cases[i].id)
                                    )
                                )
                                .append($('<td>')
                                    .text(response.cases[i].description)
                                )
                            );
        }
    });
}

function getSuitInfoWithOutCleanCases(suit_name){
    $.get("/getSuitByName/" + suit_name, function(response){
        $("#suitId").text(response.id);
        $("#nameSuit").text(response.name);
        $("#descriptionSuit").text(response.description);
        $("#countCases").text(response.cases.length);
        $("#tableCases").empty();
        for(var i = 0; i < response.cases.length; i++){
            $("#tableCases").append($('<tr>')
                                .append($('<td>')
                                    .addClass('small_td')
                                    .append($('<input>')
                                        .attr('type', 'checkbox')
                                    )
                                    .append($('<input>')
                                        .addClass('particular_caseId')
                                        .attr('type', 'hidden')
                                        .val(response.cases[i].id)
                                    )
                                )
                                .append($('<td>')
                                    .text(response.cases[i].description)
                                )
                            );
        }
    });
}