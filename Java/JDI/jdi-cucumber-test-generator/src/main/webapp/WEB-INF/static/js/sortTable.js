// Сортировать таблицу при загрузке страницы
$(document).ready(function()
    {
        getSuitInfo(suit_id);
        $('.tablesorter').trigger('update');
        $("#tableCases").tablesorter();
    }
);



