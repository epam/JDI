/**
 * Created by Natalia_Grebenshchikova on 12/11/2015.
 */

function getBeanAsJDIObject(beanID) {
    var ind = beanID.split("-").pop();

    var jdi  = jdiObject(
        $('#PO-name-' + ind).val(),
        $('#PO-type-' + ind).val(),
        $('#PO-gen-' + ind).val(),
        [],
        $('#PO-locator-' + ind).val());

    jdi.section = $('#PO-section-' + ind).val();

    return jdi;
}
function getJSONFromTree(parentID, json, pageIndex) {

    if (json === undefined) {
        json = [];
        json.push(getBeanAsJDIObject(parentID));
        json = json[0];
    }

    $.each($('#' + parentID).children('ul').children('li'), function (ind, val) {
        var id = val.getAttribute("id");
        json.elements.push(getBeanAsJDIObject(id))
        if ($(val).children('ul').length !== 0)
            json.elements[ind] = getJSONFromTree(id, json.elements[ind], pageIndex)
    });

    if (parentID === 'tree-{0}'.format(pageIndex))
        return {
            name: $('#txt-name-{0}'.format(pageIndex)).val(),
            url: $('#txt-URL-{0}'.format(pageIndex)).val(),
            packageName: $('#txt-package-{0}'.format(pageIndex)).val(),
            elements: json
        };

    return json;
}