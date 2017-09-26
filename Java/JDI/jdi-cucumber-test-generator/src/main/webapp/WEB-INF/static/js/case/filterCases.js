function filterCases() {
    var isAllChecked = $('input:checkbox:checked.checkedAllTags').map(
        function () {
            return this.value;
        }).get();

    if (isAllChecked == 'true') {
        drawSuitPage(null, false);
    } else {
        var checkedTags = $('input:checkbox:checked.checkedTags').map(
            function () {
                return this.value;
            }).get();

        drawSuitPage(checkedTags, true);
    }

    PopUpHide('#popup_tag_filter');
}