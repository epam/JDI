function filterCases() {
    var isAllChecked = $('.checkedAllTags').is(":checked");

    if (isAllChecked == true) {
        drawSuitPage(null, false);
        previouslySelectedTags = [];
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