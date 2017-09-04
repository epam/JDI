$(document).ready(function () {
    var code = $(".codemirror-textarea")[0];
    var editor = CodeMirror.fromTextArea(code, {
        lineNumbers: true,
        value: "function myScript(){return 100;}\n",
        mode:  "gherkin",
        extraKeys: {"Ctrl-Space": "autocomplete"}
    });
    // var editor = CodeMirror(document.getElementById("codemirror-textarea"))
})
// var editor = CodeMirror(document.getElementById("codemirror-textarea"));

// var editor = $('.CodeMirror')[0].CodeMirror;