var draggingStarted = false;

//draw JDI Tree
function drawJDITree(jsonElements, parentID) {
    if (jsonElements !== undefined)
        if (jsonElements.elements !== undefined)
            $.each(jsonElements.elements, function (ind, el) {
                if (el.elements === undefined)
                    addNewJDIBeanToTree(parentID, el);
                else {
                    var drawnEl = addNewJDIBeanToTree(parentID, el);
                    var ind = drawnEl.getAttribute("id").split("-").pop();
                    drawJDITree(el, '#main-div-{0}'.format(ind));
                }
            });
}

//JDI Bean
function addNewJDIBeanToTree(parentCSS, jdiElement) {

    var template = $("#template").html().replace(/{i}/g, treeElementCount);

    if ($(parentCSS).children('ul').length === 0)
        $(parentCSS).append("<ul>" + template + "</ul>")
    else
        $(parentCSS).children('ul').append(template)

    addJDIBeanEvents(treeElementCount);
    makeJDIBeanDraggableDroppable(treeElementCount);
    fillJDIBean(treeElementCount, jdiElement)

    addJDIBeanEditEvent_DataEdit(treeElementCount);

    treeElementCount++;

    return $(template)[0];
}
function clearSelectionToDrop() {

    while ($("[id^=div-col-]").hasClass("selectedToDrop"))
        $("[id^=div-col-]").removeClass("selectedToDrop");
}

//JDI-Bean Events
function addJDIBeanEvents(index) {

    $('#btn-col-{0}'.format(index)).on('click', function () {

        var ind = this.getAttribute("id").split("-").pop();

        if ($(this).hasClass('glyphicon-expand')) {
            $(this).removeClass('glyphicon-expand');
            $(this).addClass('glyphicon-collapse-down');
            $("#div-col-{0}".format(ind)).css("display", "block");
            $("#div-col-none-{0}".format(ind)).css("display", "none");
        } else {
            $(this).removeClass('glyphicon-collapse-down');
            $(this).addClass('glyphicon-expand');
            $("#div-col-{0}".format(ind)).css("display", "none");
            $("#div-col-none-{0}".format(ind)).css("display", "block");
        }
    });

    $('#modal-btn-delete-{0}'.format(index)).on('click', function () {
        var ind = this.getAttribute("id").split("-").pop();
        var pageId = getCurrentPageId();
        var elPath = getBeanIndexSequenceOnPage("main-div-{0}".format(ind));

        pages.removeBean(pageId, elPath);

        $('#modal-' + ind).modal("hide");
        $('#main-div-' + ind).remove();

        fillPageObjectPre(pages.getPageByID(pageId).data, pageId);
    })

    $('#modal-btn-up-{0}'.format(index)).on('click', function () {
        var ind = this.getAttribute("id").split("-").pop();

        $('#modal-' + ind).modal("hide");

        var elPath = getBeanIndexSequenceOnPage("main-div-{0}".format(ind));
        var pageId = getCurrentPageId();

        pages.upChildren(pageId, elPath);

        var elements = $('#main-div-' + ind).children('ul').children();

        $.each(elements, function (index, val) {
            $(val).insertBefore('#main-div-' + ind);
        })

        $('#main-div-' + ind).remove();

        fillPageObjectPre(pages.getPageByID(pageId).data, pageId);
    })

    $('#btn-remove-{0}'.format(index)).on('click', function () {
        var ind = this.getAttribute("id").split("-").pop();

        if ($('#main-div-' + ind).children('ul').children().length > 0)
            $("#modal-" + ind).modal("show");
        else {
            var elPath = getBeanIndexSequenceOnPage("main-div-{0}".format(ind));
            var pageId = getCurrentPageId();

            pages.removeBean(pageId, elPath);
            $('#main-div-' + ind).remove();

            fillPageObjectPre(pages.getPageByID(pageId).data, pageId);
        }


    })

    $('#btn-add-{0}'.format(index)).on('click', function () {
        var ind = this.getAttribute("id").split("-").pop();
        addNewJDIBeanToTree('#main-div-{0}'.format(ind));
    })

    $('#div-col-{0}, #div-col-none-{0}'.format(index)).on("mouseover", function () {
        $(this).addClass("highlight");
    });
    $('#div-col-{0}, #div-col-none-{0}'.format(index)).on("mouseout", function () {
        $(this).removeClass("highlight");
    })

    $('#cb-locate-{0}'.format(index)).on('change', function () {
        addEventToBeansCheckBox(this.getAttribute("id").split("-").pop());
    })

    $("#PO-section-{0}, #PO-gen-{0}, #PO-name-{0}, #PO-type-{0}, #PO-locator-{0}".format(index)).keypress(function (e) {
        (new inputAction).lostFocus(e);
    })
}
function addEventToBeansCheckBox(ind) {
    if ($('#cb-locate-' + ind).is(':checked')) {

        $.each($('[id^=tree]  .staticHighlight'), function (i, val) {
            $(this).removeClass("staticHighlight");
        });
        $.each($('[id^=tree] input[id^=cb-locate]'), function (i, val) {
            if (this.getAttribute("id").split("-").pop() !== ind)
                $(this).removeAttr("checked");
        });

        chrome.runtime.sendMessage({
            name: requestName.checkBoxLocationActive,
            tabId: chrome.devtools.inspectedWindow.tabId,
            cssLocator: $("#PO-locator-" + ind).val()
        });

        $('#div-col-none-{0}, #div-col-{0}'.format(ind)).addClass("staticHighlight");
    } else {
        $('#div-col-none-{0}, #div-col-{0}'.format(ind)).removeClass("staticHighlight");

        chrome.runtime.sendMessage({
            name: requestName.checkBoxLocationInactive,
            tabId: chrome.devtools.inspectedWindow.tabId
        });
    }
}

function makeJDIBeanDraggableDroppable(index) {

    $('#main-div-' + index).draggable({
        stop: function (event, index) {

            var pageId = getCurrentPageId();
            var bean = getJSONFromTree($(event.target).attr('id'), undefined, pageId.split("-").pop());

            if (draggingStarted) {
                $('[id^="tree-"] > ul').append(event.target);
                $(event.target).css({left: 0, top: "auto"});

                pages.getPageByID(pageId).data.elements.push(bean);
            }
            else {
                var elPath = getBeanIndexSequenceOnPage($(event.target).attr('id'));
                elPath.splice(elPath.length - 1, 1);

                pages.addBean(pageId, elPath, bean);
            }

            pages.addSectionObjects(pages.getPageByID(pageId).data, sections);
            fillPageObjectPre(pages.getPageByID(pageId).data, pageId);
            draggingStarted = false;
        },
        start: function (event, index) {
            var elPath = getBeanIndexSequenceOnPage($(event.target).attr('id'));
            pages.removeBean(getCurrentPageId(), elPath);

            draggingStarted = true;
        }
    });
    $('#main-div-' + index).droppable({
        drop: function (event, ui) {

            clearSelectionToDrop()

            if ($(event.target).children('ul').length === 0)
                $(event.target).append("<ul></ul>");

            $($(event.target).children('ul')[0]).append(ui.draggable);
            $(ui.draggable).css({left: "auto", top: "auto"});

            draggingStarted = false;
        },
        over: function (event) {

            ind = $(event.target).attr("id").split("-").pop();

            $(event.target).find('#div-col-none-' + ind).addClass("selectedToDrop");
            $(event.target).find('#div-col-' + ind).addClass("selectedToDrop");
        },
        out: function (event) {

            ind = $(event.target).attr("id").split("-").pop();

            clearSelectionToDrop()
        }
    });
}

function addJDIBeanEditEvent_DataEdit(index) {
    addNewBeanEvent_DataEdit(index);
    editBeanTxtFieldEvent_DataEdit(index);
}
function addNewBeanEvent_DataEdit(index) {
    $('#btn-add-{0}'.format(index)).on('click', function () {
        var indexParent = $(this).attr('id').split("-").pop();
        var elPath = getBeanIndexSequenceOnPage("main-div-{0}".format(indexParent));
        var pageId = getCurrentPageId();
        var beanID = $("#main-div-{0} > ul > li:nth-last-child(1)".format(indexParent)).attr('id');

        pages.addBean(pageId, elPath, getBeanAsJDIObject(beanID));

        fillPageObjectPre(pages.getPageByID(pageId).data, pageId);
    })
}
function editBeanTxtFieldEvent_DataEdit(index) {

    $('#PO-locator-{0}'.format(index)).on('input', function () {
        var elPath = getBeanIndexSequenceOnPage("main-div-{0}".format(getInd(this)));
        var pageId = getCurrentPageId();

        pages.updateBeanData(pageId, elPath, {locator: $(this).val()});
        fillPageObjectPre(pages.getPageByID(pageId).data, pageId);
    });
    $('#PO-name-{0}'.format(index)).on('input', function () {
        var elPath = getBeanIndexSequenceOnPage("main-div-{0}".format(getInd(this)));

        $(this).removeClass("warningText");
        $('#jdi-name-col-{0}'.format(getInd(this))).text($(this).val()).removeClass("warningText");

        (new inputAction).filter(this, {}, 'variable');
        pages.updateBeanData(getCurrentPageId(), elPath, {name: $(this).val()});

        fillPageObjectPre(pages.getPageByID(getCurrentPageId()).data, getCurrentPageId());
    });
    $('#PO-type-{0}, #PO-gen-{0}, #PO-section-{0}'.format(index)).on('input', function () {
        var elPath = getBeanIndexSequenceOnPage("main-div-{0}".format(getInd(this)));

        (new inputAction).filter(this, {}, 'class');

        if ($(this).attr('id').match('PO-type-[0-9]*'))
            pages.updateBeanData(getCurrentPageId(), elPath, {type: $(this).val()});
        else if ($(this).attr('id').match('PO-section-[0-9]*'))
            pages.updateBeanData(getCurrentPageId(), elPath, {section: $(this).val()});
        else if ($(this).attr('id').match('PO-gen-[0-9]*'))
            pages.updateBeanData(getCurrentPageId(), elPath, {gen: $(this).val()});

        fillPageObjectPre(pages.getPageByID(getCurrentPageId()).data, getCurrentPageId());
    })
}

//populate
function fillJDIBean(index, jdiObj) {

    $('#PO-type-{0}'.format(index)).val(jdiObj === undefined ? "no type" : jdiObj.type);

    fillPOTypeDropDown($("#PO-type-{0}".format(index)).parent(), index);

    if (jdiObj === undefined) {
        $('#PO-name-{0}'.format(index)).val("no name").addClass("warningText");
        $('#jdi-name-col-{0}'.format(index)).text("no name").addClass("warningText");
    } else {

        if (sectionTypes.indexOf(jdiObj.type) !== -1) {
            $('#PO-gen-{0}'.format(index)).val(jdiObj.gen === undefined ? "" : jdiObj.gen);
            $('#PO-section-{0}'.format(index)).val(jdiObj.section === undefined ? "" : jdiObj.section);

            $('#tr-PO-section-{0}, #tr-PO-gen-{0}'.format(index)).css("display", "table-row");
            fillSectionTypeDropDown($("#PO-section-{0}".format(index)).parent(), index);
        }

        if (jdiObj.name === "" | jdiObj.name === null | jdiObj.name === undefined) {
            $('#PO-name-' + index).val("no name").addClass("warningText");
            $('#jdi-name-col-' + index).text("no name").addClass("warningText");
        } else {
            $('#PO-name-{0}'.format(index)).val(jdiObj.name).removeClass("warningText");
            $('#jdi-name-col-{0}'.format(index)).text(jdiObj.name).removeClass("warningText");
        }

        if (jdiObj.locator === "" | jdiObj.locator === null | jdiObj.locator === undefined) {
            $('#PO-locator-{0}'.format(index)).val("no locator").addClass("warningText");
        } else {
            $('#PO-locator-{0}'.format(index)).val(jdiObj.locator).removeClass("warningText");
            addEventToBeansCheckBox(index);
        }
    }
}
function fillPOTypeDropDown(dropDown, index) {
    var ul = $(dropDown).find('ul');

    ul.empty();

    ul.append('<li class="dropdown-header">Sections</li>');
    for (var i = 0, len = sectionTypes.length; i < len; i++) {
        var a = '<li><a id="a-PO-section-type-{0}-{1}">{2}</a></li>'.format(index, i, sectionTypes[i]);

        ul.append(a)

        $('#a-PO-section-type-{0}-{1}'.format(index, i)).on('click', function () {

            var i = $(this).attr('id').split('-').pop()

            $(this).parents('.dropdown').find('input').val(sectionTypes[i])

            var elPath = getBeanIndexSequenceOnPage("main-div-{0}".format(index));
            var pageId = getCurrentPageId();

            pages.updateBeanData(pageId, elPath, {type: $(this).text()});
            try {
                fillPageObjectPre(pages.getPageByID(pageId).data, pageId);
            } catch (e) {
                console.log("could not display java Class: {0}".format(e))
            }

            $('#tr-PO-section-{0}, #tr-PO-gen-{0}'.format(index)).css("display", "table-row");
            fillSectionTypeDropDown($("#PO-section-{0}".format(index)).parent(), index);
        })
    }

    ul.append('<li class="divider"></li>');
    ul.append('<li class="dropdown-header">Elements</li>');
    for (var i = 0, len = elementTypes.length; i < len; i++) {
        var a = '<li><a id="a-PO-elements-type-{0}-{1}">{2}</a></li>'.format(index, i, elementTypes[i]);

        ul.append(a)

        $('#a-PO-elements-type-{0}-{1}'.format(index, i)).on('click', function () {

            var i = $(this).attr('id').split('-').pop()

            $(this).parents('.dropdown').find('input').val(elementTypes[i]);

            var elPath = getBeanIndexSequenceOnPage("main-div-{0}".format(index));
            var pageId = getCurrentPageId();

            pages.updateBeanData(pageId, elPath, {type: $(this).text()});
            try {
                fillPageObjectPre(pages.getPageByID(pageId).data, pageId);
            } catch (e) {
                console.log("could not display java Class: {0}".format(e))
            }

            $('#tr-PO-section-{0}, #tr-PO-gen-{0}'.format(index)).css("display", "none");
        })
    }
}
function fillSectionTypeDropDown(dropDown, index) {
    var beanType = $(dropDown).parents('table').find("[id^='PO-type-']").val();
    var ul = $(dropDown).find('ul');

    ul.empty();

    for (var i = 0, len = sections.sectionsArray.length; i < len; i++) {

        if (beanType === sections.getSectionByIndex(i).data.type) {
            var a = '<li><a id="a-PO-section-name-{0}-{1}">{2}</a></li>'.format(index, i, sections.getSectionByIndex(i).sectionName);

            ul.append(a)

            $('#a-PO-section-name-{0}-{1}'.format(index, i)).on('click', function () {

                var pageId = getCurrentPageId();
                var pageIndex = pageId.split("-").pop();
                var i = $(this).attr('id').split('-').pop();

                $(this).parents('.dropdown').find('input').val(sections.getSectionByIndex(i).sectionName);

                var elPath = getBeanIndexSequenceOnPage("main-div-{0}".format(index));

                pages.linkSection(pageId, elPath, $(this).text());

                cleanTree(pageId);
                drawJDITree(pages.getPageByID(pageId).data, "#tree-{0}".format(pageIndex));
                fillPageObjectPre(pages.getPageByID(pageId).data, pageId);
            })
        }
    }

}

