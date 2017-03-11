/**
 * Created by Natalia_Grebenshchikova on 12/24/2015.
 */
var strPattern = function () {
    this.className = function (str) {
        if (str.length > 0)
            str = (str[0].toUpperCase() + str.substr(1))
                .replace(/[ !@#$%^*&(){}~:;<>,.?\-+/[]|]/g, "")
                .replace(/^[0-9]/g, "");

        return str;
    }
    this.variableName = function (str) {
        if (str.length > 0)
            str = (str[0].toLowerCase() + str.substr(1))
                .replace(/[ !@#$%^*&(){}~:;<>,.?\-+/[]|]/g, "")
                .replace(/^[0-9]/g, "");

        return str;
    }

    this.packageName = function (str) {
        if (str.length > 0)
            str = (str.toLowerCase()).replace(/[ !@#$%^*&(){}~:;<>,?\-+/[]|]/g, "").replace(/[0-9]/g, "");

        return str;
    }

}

var inputAction = function () {
    this.filter = function (el, toUpdateObject, inputType) {
        var pageId = getCurrentPageId();
        var cursorInd = el.selectionStart;
        var str;

        switch (inputType) {
            case 'class':
                str = (new strPattern).className($(el).val());
                toUpdateObject.section = str;
                break;
            case 'variable':
                str = (new strPattern).variableName($(el).val());
                toUpdateObject.section = str;
                break;
            case 'package':
                str = (new strPattern).packageName($(el).val());
                toUpdateObject.packageName = str;
                break;
            default:
                console.log("wrong inputType");
        }

        fillPageObjectPre(pages.getPageByID(pageId).data, pageId);

        if ($(el).val().length > str.length)
            cursorInd--;
        $(el).val(str);
        el.selectionStart = el.selectionEnd = cursorInd;
    }
    this.lostFocus = function (e){
        if (e.which === 13)
            $(e.target).blur();
    }
}


function getInd(el){
    return $(el).attr('id').split("-").pop();
}

