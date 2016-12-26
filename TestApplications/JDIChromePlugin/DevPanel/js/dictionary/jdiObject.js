function jdiObject(_name, _type, _gen, _elements, _locator) {
    return {
        name: _name,
        type: _type,
        gen: _gen,
        elements: _elements,
        locator: _locator,
        section: _name
    };
}

var jdiTags = {
    jdi_type: "jdi-type",
    jdi_name: "jdi-name",
    jdi_parent: "jdi-parent",
    jdi_gen: "jdi-gen"
}

var sectionTypes = ['Form', 'Section', 'IPagination', 'ISearch'];
var elementTypes = ['ITextArea', 'IButton', 'ITimePicker',
    'IDatePicker', 'IElement',  'ITextField', 'RFileInput', 'IRange', 'ITable'];
