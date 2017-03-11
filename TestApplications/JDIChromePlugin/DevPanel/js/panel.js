var treeElementCount = 0;
var sections = new Sections();
var pages = new Pages();

document.addEventListener('DOMContentLoaded', function (e) {

    var template = $("#template-page").html().replace(/{page}/g, 0);
    $('#page-0').append(template);
    (new PageBuilder("page-0")).buildPageContent();

    $('#add-new-tag').on('click', function (e) {
        addNewTabLinkEvent();
    })

    $('#a-tab-sections').on('click', function (e) {
        drawSectionPage();
        scroll(0, 0);
        $("html, body").animate({scrollTop: 0});
    })

    navigateToWebPageEvent("#a-page-0");

    chrome.storage.onChanged.addListener(function (changed, e1) {
        if ('jdi_page' in changed) {
            if (changed.jdi_page.newValue.tabId === chrome.devtools.inspectedWindow.tabId) {

                var jdi_page_json = changed.jdi_page.newValue.data;
                var jsonObject = $.parseJSON(jdi_page_json);

                var pageId = pages.getPageByURL(jsonObject.url).id;
                var pageIndex = pageId.split("-").pop();

                pages.updatePageData(page("page-{0}".format(pageIndex),
                    jsonObject.url,
                    jsonObject));

                pages.addSectionObjects(pages.getPageByID("page-{0}".format(pageIndex)).data, sections);

                drawJDITree(jsonObject, "#tree-{0}".format(pageIndex));
                fillPageInfo(jsonObject, pageId);
                fillPageObjectPre(jsonObject, pageId);

                chrome.storage.local.remove('jdi_page');
            }
        }
        if ('jdi_object' in changed) {
            if (changed.jdi_object.newValue.tabId == chrome.devtools.inspectedWindow.tabId) {
                var ind = $(".staticHighlight [id^='PO-name-']")[0].getAttribute("id").split("-").pop();
                var elPath = getBeanIndexSequenceOnPage("main-div-{0}".format(ind));
                var pageId = getCurrentPageId();

                fillJDIBean(ind, changed.jdi_object.newValue.data);
                pages.updateBeanData(pageId, elPath, changed.jdi_object.newValue.data);

                fillPageObjectPre(pages.getPageByID(pageId).data, pageId);
            }
        }
    });
});

document.addEventListener('unload', function(){
    console.log("Closed");
})

function addNewTabLinkEvent() {

    var pageIndex = pages.pagesArray.length;

    $.each($('#main-tab-content > div'), function (ind, val) {
        $(val).removeClass("in active");
    })

    $('#main-tab-content').append('<div id="page-{0}" class="tab-pane fade in active"></div>'.format(pageIndex));

    $('#main-tab-content')

    $('#add-new-tag')
        .attr('id', "a-page-{0}".format(pageIndex))
        .attr('href', "#page-{0}".format(pageIndex))
        .empty()
        .text('New Tab')
        .off('click')
        .append('<span id="a-close-page-{0}" class="close fa fa-times"></span>'.format(pageIndex));

    addCloseTabEvent(pageIndex);

    navigateToWebPageEvent("#a-page-{0}".format(pageIndex));

    $('#main-nav-tabs').append('<li><a id="add-new-tag"><span class="glyphicon glyphicon-plus"></span></a></li>');

    $('#page-{0}'.format(pageIndex)).append($("#template-page").html().replace(/{page}/g, pageIndex));

    new PageBuilder('page-{0}'.format(pageIndex)).buildPageContent();

    $('#add-new-tag').on('click', function (e) {
        addNewTabLinkEvent();
    })

    scroll(0, 0);
    $("html, body").animate({scrollTop: 0});
}

function navigateToWebPageEvent(a) {
    $(a).click(function (e) {
        $(this).tab('show');

        var url = $($(e.target).attr('href')).find("[id^='txt-URL-']").val();

        chrome.devtools.inspectedWindow.eval(
            "window.location.href='{0}'".format(url),
            function (result, isException) {
                console.log(result);
            });

        scroll(0, 0);
        $("html, body").animate({scrollTop: 0});
    })
}

function renameTab(NewName, linkId){
    var linkContent = $('#'+linkId).html();
    linkContent = linkContent.substring(linkContent.indexOf("<"));

    $('#'+linkId).html(NewName+linkContent);

    addCloseTabEvent(linkId.split("-").pop());
}

function addCloseTabEvent(pageIndex) {
    $('#a-close-page-{0}'.format(pageIndex)).on('click', function () {

        var pageIndex = $(this).attr('id').split("-").pop();
        var pageId = 'page-{0}'.format(pageIndex);

        $('#{0}, [href="#{0}"]'.format(pageId)).remove();
        pages.removePage(pageId);
    })
}

