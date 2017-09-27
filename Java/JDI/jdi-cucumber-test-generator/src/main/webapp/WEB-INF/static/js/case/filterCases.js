function filterCases() {
    var isAllChecked = $('.checkedAllTags:checked').map(
        function () {
            return this.value;
        }).get();

    if (isAllChecked == 'true') {
        drawSuitPage(null, false);
    } else {
        var checkedTags = $('.checkedTags:checked').map(
            function () {
                return this.value;
            }).get();

        previouslySelectedTags = checkedTags;

        drawSuitPage(checkedTags, true);
    }

    PopUpHide('#popup_tag_filter');
}