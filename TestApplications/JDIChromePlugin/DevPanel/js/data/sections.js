/**
 * Created by Natalia_Grebenshchik on 12/2/2015.
 */

var Sections = function () {

    this.sectionsArray = [];

    this.addNewSection = function (sec) {
        this.sectionsArray.push(sec);
        return this.getSectionIndex(sec.sectionName);
    };

    this.updateSectionsAfterSectionDelete = function (sectionIndex) {
        var section = this.getSectionByIndex(sectionIndex).data;

        for (var ind in this.sectionsArray) {
            if (this.sectionsArray[ind].data !== undefined && sectionIndex !== ind) {
                for (var elInd in this.sectionsArray[ind].data.elements) {
                    if (this.sectionsArray[ind].data.elements[elInd] === section)
                        this.sectionsArray[ind].data.elements.splice(elInd, 1);
                }
            }
        }

    }

    this.removeSection = function (index) {
        this.sectionsArray.splice(index, 1);
    }

    this.getSectionIndex = function (sectionName) {
        return getIndex(this.sectionsArray, "sectionName", sectionName);
    };
    this.getSectionIndexByData = function (sectionData) {
        return getIndex(this.sectionsArray, "data", sectionData);
    }
    this.getSection = function (sectionName) {
        var ind = this.getSectionIndex(sectionName)
        return this.sectionsArray[ind];
    };
    this.getSectionByIndex = function (ind) {
        return this.sectionsArray[ind];
    }

};

function section(sectionName, data) {
    var obj = new Object();

    obj.data = data;
    obj.data.section = sectionName;
    obj.sectionName = obj.data.section;

    return obj;
}


