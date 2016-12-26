/* global _sectionTypes, sectionTypes */

var Pages = function () {
        this.pagesArray = [];

        this.addNewPage = function (page) {
            this.pagesArray.push(page);
            return this.getPageIndex(page.url, page.id);
        };
        this.addBean = function (pageId, elSequence, bean) {
            var page = this.getPageByID(pageId).data;

            if (page === undefined) {
                this.getPageByID(pageId).data = {elements: []};
                page = this.getPageByID(pageId).data;
            }
            else {
                for (var ind in elSequence)
                    page = page.elements[elSequence[ind]];
                if (page.elements === undefined)
                    page.elements = [];
                if ($.inArray(bean.type, sectionTypes) !== -1) {
                    var sectionIndex = sections.getSectionIndex(bean.section);
                    if (sectionIndex === -1)
                        sectionIndex = sections.addNewSection(section(bean.section, bean));
                    bean = sections.getSectionByIndex(sectionIndex).data;
                }
            }

            page.elements.push(bean);
        }
        this.addSectionObjects = function (bean, sections) {

            for (var ind in bean.elements) {
                var val = bean.elements[ind];

                if (sectionTypes.indexOf(val.type) > -1) {
                    var sectionIndex = sections.getSectionIndex(val.section);
                    if (sectionIndex === -1)
                        sectionIndex = sections.addNewSection(section(val.section, val));

                    bean.elements[ind] = sections.getSectionByIndex(sectionIndex).data;
                }

                if (val.elements !== undefined && val.elements.length > 0)
                    this.addSectionObjects(bean.elements[ind], sections);

            }
        }

        this.updatePagesAfterSectionDelete = function (sectionIndex) {

            for (var pageInd in this.pagesArray) {
                if (this.pagesArray[pageInd].data !== undefined) {
                    this.updateSubElementAfterSectionDelete(sectionIndex, this.pagesArray[pageInd].data)
                }
            }
        }
        this.updateSubElementAfterSectionDelete = function (sectionIndex, SubElements) {

            var section = sections.getSectionByIndex(sectionIndex).data;

            if (SubElements.elements != undefined && SubElements.elements.length > 0) {
                for (var elInd = SubElements.elements.length - 1; elInd > -1; elInd--) {
                    if (SubElements.elements[elInd] === section)
                        SubElements.elements.splice(elInd, 1);
                    if (SubElements.elements[elInd] !== undefined &&
                        SubElements.elements[elInd].elements !== undefined &&
                        SubElements.elements[elInd].elements.length > 0)
                        this.updateSubElementAfterSectionDelete(sectionIndex, SubElements.elements[elInd]);
                }
            }
        }

        this.linkSection = function (pageId, elSequence, sectionName) {
            var sectionIndex = sections.getSectionIndex(sectionName);
            var elT = this.getPageByID(pageId).data.elements;

            for (var i = 0; i < elSequence.length; i++)
                if (i === elSequence.length - 1)
                    elT.splice(elSequence[i], 1, sections.getSectionByIndex(sectionIndex).data);
                else
                    elT = elT[elSequence[i]].elements;
        }
        this.updatePageData = function (data) {

            var ind = this.getPageIndex(data.url, data.id);
            this.pagesArray[ind].data = data.data;
            this.pagesArray[ind].url = data.url;

            return this.pagesArray[ind];
        }
        this.updateBeanData = function (pageId, elSequence, newValue) {

            var el = new Object();
            el = this.getPageByID(pageId).data;

            for (var ind in elSequence)
                el = el.elements[elSequence[ind]];

            if ('locator' in newValue)
                el.locator = newValue.locator;
            if ('name' in newValue)
                el.name = newValue.name;
            if ('gen' in newValue)
                el.gen = newValue.gen;
            if ('section' in newValue) {
                var sectionIndex = sections.getSectionIndexByData(el);

                if (sectionIndex !== -1) {
                    el.section = newValue.section;
                    sections.getSectionByIndex(sectionIndex).sectionName = el.section;
                }
                else {
                    el.section = newValue.section;
                    var sectionIndex = sections.addNewSection(section(el.section, el));
                    var elT = this.getPageByID(pageId).data.elements;

                    for (var i = 0; i < elSequence.length; i++)
                        if (i === elSequence.length - 1)
                            elT.splice(elSequence[i], 1, sections.getSectionByIndex(sectionIndex).data);
                        else
                            elT = elT[elSequence[i]].elements;
                }

            }
            if ('type' in newValue)
                el.type = newValue.type;

            this.removeBlankChildrenReference(pageId);
        }

        this.removeBean = function (pageId, elSequence) {
            var el = new Object();
            el = this.getPageByID(pageId).data.elements;

            for (var i = 0; i < elSequence.length; i++) {
                if (i === elSequence.length - 1)
                    el.splice(elSequence[i], 1);
                else {
                    el = el[elSequence[i]].elements;
                }
            }
            this.removeBlankChildrenReference(pageId);
        }
        this.upChildren = function (pageId, elSequence) {
            var el = new Object();
            el = this.getPageByID(pageId).data.elements;

            for (var i = 0; i < elSequence.length; i++) {
                if (i === elSequence.length - 1) {

                    var elT = el[elSequence[i]].elements;

                    el.splice(elSequence[i], 1);

                    for (var j = elT.length - 1; j >= 0; j--) {
                        el.splice(elSequence[i], 0, elT[j]);
                    }
                }
                else
                    el = el[elSequence[i]].elements;
            }
        }
        this.removePage = function (pageId) {
            var index = this.getPageIndexById(pageId);

            this.pagesArray[index].url = undefined;
            this.pagesArray[index].data = undefined;
        }
        this.removeBlankChildrenReference = function (pageId) {

            var page = this.getPageByID(pageId);
            if (page.data.elements === undefined | page.data.elements.length === 0)
                this.removePage(pageId)
        }

        this.getPageByID = function (id) {
            return this.pagesArray[getIndex(this.pagesArray, "id", id)];
        }
        this.getPageByURL = function (url) {
            return this.pagesArray[getIndex(this.pagesArray, "url", url)];
        }
        this.getPageIndex = function (url, id) {
            if (id !== undefined)
                return getIndex(this.pagesArray, "id", id)
            else if (url !== undefined)
                return getIndex(this.pagesArray, "url", url)
            else
                return -1;
        }
        this.getPageIndexById = function (id) {
            if (id !== undefined)
                return getIndex(this.pagesArray, "id", id);
            return -1;
        }
        this.getPageIndexByURLandID = function (url, id) {

            indByID = getIndex(this.pagesArray, "id", id);
            indByURL = getIndex(this.pagesArray, "url", url);
            if (indByID === indByURL && indByID !== undefined) {
                return indByID;
            }
            return -1;
        }
    }

function page(id, url, data) {
    return {
        id: id,
        url: url,
        data: data
    };
}

