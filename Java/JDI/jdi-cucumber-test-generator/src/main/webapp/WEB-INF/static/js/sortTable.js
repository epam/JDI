// Сортировать таблицу при загрузке страницы
$(document).ready(function()
    {
        $("#tableCases").tablesorter();
    }
);

$(document).ready(function()
    {
        $("#tableCases").tablesorter( {sortList: [[1,0]]} );
    }
);

