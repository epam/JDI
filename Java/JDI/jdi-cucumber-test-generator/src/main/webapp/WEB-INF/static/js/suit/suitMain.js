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
});

function getSuitInfo(suit_name){
    $.get("/getSuitByName/" + suit_name, function(response){
        $("#suitId").text(response.id);
        $("#nameSuit").text(response.name);
        $("#descriptionSuit").text(response.description);
        $("#countCases").text(response.cases.length);
        console.log(response);
        for(var i = 0; i < response.cases.length; i++){
            $("#tableCases").append($('<tr>')
                                .append($("<td>")
                                    .addClass("small_td")
                                    .append($('<input>')
                                        .attr('type', 'checkbox')
                                    )
                                )
                                .append($("<td>")
                                    .text("Case fljsdnfls")
                                )
                            );
        }

    });
}