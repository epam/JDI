/**
 * Created by Dmitry_Lebedev1 on 10/11/2015.
 */

var IncludesDictionary = {
    ITextArea:      "com.ggasoftware.jdiuitest.core.interfaces.common.ITextArea",
    IButton:        "com.ggasoftware.jdiuitest.core.interfaces.common.IButton",
    IElement:       "com.ggasoftware.jdiuitest.core.interfaces.base.IElement",
    RFileInput:     "com.ggasoftware.jdiuitest.web.robot.RFileInput",
    ITextField:     "com.ggasoftware.jdiuitest.core.interfaces.common.ITextField",
    IFileInput:     "com.ggasoftware.jdiuitest.core.interfaces.common.IFileInput",
    ITimePicker:    "com.ggasoftware.jdiuitests.implementation.selenium.elements.interfaces.common.IDatePicker",
    Form:           "com.ggasoftware.jdiuitests.implementation.selenium.elements.composite.Form",
    Section:        "com.ggasoftware.jdiuitests.implementation.selenium.elements.composite.Section",
    IPagination:    "com.ggasoftware.jdiuitests.implementation.selenium.elements.composite.Pagination",
    IDatePicker:    "com.ggasoftware.jdiuitests.implementation.selenium.elements.interfaces.common.IDatePicker",
    IPage:          "import com.ggasoftware.jdiuitests.implementation.selenium.elements.composite.Page",
    IRange:         "/*com.epam.jdi.IRange*/",
    Page :          "com.ggasoftware.jdiuitests.implementation.selenium.elements.composite.Page",
    by:             "org.openqa.selenium.By",
    fundBy:         "org.openqa.selenium.support.FindBy",
    ITable:         "com.ggasoftware.jdiuitests.implementation.selenium.elements.complex.table.interfaces.ITable",
    ICheckBox:      "com.ggasoftware.jdiuitest.core.interfaces.common.ICheckBox",
    IImage:         "com.ggasoftware.jdiuitest.core.interfaces.common.IImage",
    ILink:          "com.ggasoftware.jdiuitest.core.interfaces.common.ILink",
    ILabel:         "com.ggasoftware.jdiuitest.core.interfaces.common.ILabel",
    IText:          "com.ggasoftware.jdiuitest.core.interfaces.common.IText",
    ISearch:        "com.SEARCH"
}

var ConvertToJavaType = {
    ITextArea: "String",
    ITextField: "String",
}

function simpleFileld(elem) {
    var fby = FindByTemplates.css(elem.locator);
    var field = "\tpublic {0} {1};\n".format(elem.type, elem.name);
    return fby + field;
}

var FieldTemplates = {
    unknown: "\n\t/*{0} {1}*/\n",
    String:  function(elem) {
        return "\tpublic {0} {1};\n".format(elem.type, elem.name);
    },
    Section: simpleFileld,
    ITextArea: simpleFileld,
    IButton: simpleFileld,
    Form: simpleFileld,
    IPage: simpleFileld,
    IElement: simpleFileld,
    ITextField: simpleFileld,
    Pagination: function (elem) {
        return "\n\tpublic Pagination {0} = new Pagination({1}, {2}, {3}, {4}, {5});\n".format(
            elem.name, elem.get("template"), elem.get("next"), elem.get("prev"), elem.get("first"), elem.get("last")
        );
    },
    IDatePicker: simpleFileld,
    RFileInput: simpleFileld,
    ITable: simpleFileld,
    IRange: function (elem) {
        elem.type = "IElement";
        return simpleFileld(elem);
    },
    IFileInput: simpleFileld,
    ICheckBox: simpleFileld,
    IImage:    simpleFileld,
    ILink:     simpleFileld,
    ILabel:    simpleFileld,
    IText:     simpleFileld,
    ISearch:   simpleFileld,
}

var Pagination = function (element) {
    this.name = element.name;
    this.elems = new Array;

    this.get = function (key) {
        return (this.elems[key] === undefined) ? "\n\t\tnull" : FindByTemplates.byCss(this.elems[key].name)
    }
    this.print = function () {
        return FieldTemplates.Pagination(this);
    }

    var res = new Array;
    $.each(element.elements, function (index, value) {
        res[value.name] = value;
    });
    this.elems = res;
}

var FindByTemplates = {
    css: function (selector) {
        return "\n\t@FindBy(css = \"{0}\")\n".format(selector);
    },
    byCss: function (selector) {
        return "\n\t\tBy.cssSelector(\"[jdi-name={0}]\")".format(selector);
    }
}

var createRecord = function(data){
    return {
        classParam: data.classParam,
        type: data.type,
        name : data.name,
        classParam: data.classParam,
        type: data.type,
        data : data.print(),
    }
}

var deepCopy = function (obj) {
    //return JSON.parse(JSON.stringify(obj));
    return jQuery.extend(true, {}, obj)
}

String.prototype.capitalizeFirstLetter = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
}

String.prototype.downFirstLetter = function() {
    return this.charAt(0).toLowerCase() + this.slice(1);
}

String.prototype.removeIllegalLetter = function () {
    return this.replace(/([ .*+?^=!:${}()|\[\]\/\\])/g, "");
}