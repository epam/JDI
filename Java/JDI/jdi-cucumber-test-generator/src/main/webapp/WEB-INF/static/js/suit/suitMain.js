 var suit_id = -1;

$(document).ready(function () {
    $('.accordion-tabs').children('li').first().children('a').addClass('is-active').next().addClass('is-open').show();
    $('.accordion-tabs').on('click', 'li > a', function(event) {
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

    var code = $(".codemirror-textarea")[0];
    var editor = CodeMirror.fromTextArea(code, {
        lineNumbers: true,
        value: "function myScript(){return 100;}\n",
        mode:  "gherkin",
        extraKeys: {"Ctrl-Space": "autocomplete"}
    })

    $( ".tab_name" ).click(function() {
        console.log(suit_id);
        suit_id = $(".is-active").parent().children("section").children("#suitId").text();
    });



});