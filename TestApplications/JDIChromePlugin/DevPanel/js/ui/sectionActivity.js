function drawSectionPage() {

    cleanSectionPage();
    $.each(sections.sectionsArray, function (ind, val) {

        if ($.inArray(val.data.type, sectionTypes) > -1 && val.data.elements !== undefined && val.data.elements.length > 0) {

            var data = deepCopy(val.data);

            for (var i in data.elements)
                if ($.inArray(data.elements[i].type, sectionTypes) > -1)
                    data.elements[i].elements = undefined;

            data = translateToJava(data);

            var template = $('#template-section-nav-tab-link').html().replace(/{section}/g, ind);
            $('#section-nav-tab').append(template);

            if (val.data.type === 'Form') {
                $('#a-section-{0}'.format(ind))
                    .text(data[1].name)
                    .append("<span id='a-close-section-{0}' class='close fa fa-times'></span>".format(ind));

                template = $('#template-sect-nav-cont').html().replace(/{section}/g, ind);
                $('#section-nav-content').append(template);

                fillSection(ind, 1, data[1]);
                fillSection(ind, 2, data[0]);

                downloadAction(ind, 0);
                downloadAction(ind, 1);
            }
            else {
                $('#a-section-{0}'.format(ind))
                    .text(data[0].name)
                    .append("<span id='a-close-section-{0}' class='close fa fa-times'></span>".format(ind))

                template = $('#template-sect-nav-cont-nonForm').html().replace(/{section}/g, ind);
                $('#section-nav-content').append(template);

                $('#section-content-{0}'.format(ind))
                    .find('pre')
                    .text(data[0].data)
                    .each(function (i, block) {
                        hljs.highlightBlock(block);
                    });

                downloadAction(ind, 0);
            }

            $($("[id^='a-section-']")[1]).tab('show');

            addNavBarEvents();

            $('#a-close-section-{0}'.format(ind)).on('click', function () {

                var sectionId = 'a-section-{0}'.format($(this).attr('id').split("-").pop());
                var sectionIndex = $('#' + sectionId).parent().index();

                $('#{0}'.format(sectionId)).parent().remove();
                $('#section-content-{0}'.format($(this).attr('id').split("-").pop())).remove();

                sections.updateSectionsAfterSectionDelete(sectionIndex);
                pages.updatePagesAfterSectionDelete(sectionIndex);
                sections.removeSection(sectionIndex);

                $($('#page-sections ul').children()[0]).tab('show');
            })
        }
    });


}

function cleanSectionPage() {
    $('#section-nav-tab, #section-nav-content').empty();
}
function downloadAction(sectionInd, collapseInd) {
    $('#btn-coll{1}-{0}'.format(sectionInd, collapseInd + 1)).on('click', function () {

        var data = $($(this).parents('[id^=section-content-]').find('pre')[collapseInd]).text()
        var fileName = $($(this).parents('[id=section-nav-content]')).parent().find('ul #a-section-{0}'.format(sectionInd)).text();

        (new saveFile).asJava([data], fileName)
    })
}

function fillSection(sectionInd, collapseInd, data) {
    $('#section-content-{0} #a-coll{1}-{0}'.format(sectionInd, collapseInd)).text(data.name)
    $('#section-content-{0} #coll{1}-{0}  pre'.format(sectionInd, collapseInd))
        .text(data.data)
        .each(function (i, block) {
            hljs.highlightBlock(block);
        });
}
