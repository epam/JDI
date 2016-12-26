/**
 * Created by Dmitry_Lebedev1 on 23/11/2015.
 */
var resetValues = function (data) {
    data.title = "title";
    data.url = "url";
    data.name = "name";
    data.section = "section";
}

var init = function () {
    $.each($("h3"), function (i, e) {
        $(e).parent().children("pre, main, div").css("display", "none");
        e.onclick = function () {
            if ($(this).attr("hide") !== undefined) {
                $($(this).parent()).children("pre, main, div").css("display", "block")
                $(this).removeAttr("hide");
                return;
            }
            $($(this).parent()).children("pre, main, div").css("display", "none")
            $(this).attr("hide", true);
        }
    });
}

var jsonGen;
var opt;

QUnit.testStart(function () {
    opt = {
        packageName: "com.my.test",
    }
});

$(document).ready(function () {
    init();
    var c = $("[id=testData]").children();
    $.each(c, function (i, e) {
        QUnit.test("test " + e.getElementsByTagName("h3")[0].innerHTML, function (assert) {
            console.log("test " + e.getElementsByTagName("h3")[0].innerHTML)
            jsonGen = new jsonPageGenerator(jdiTags, opt, e.getElementsByTagName("main")[0]);
            resetValues(jsonGen.getPageStruct());
            var res = jsonGen.getJSON(undefined, 4);
            e.getElementsByTagName("pre")[1].innerHTML = res;
            assert.ok(jsonGen.getJSON() === e.getElementsByTagName("pre")[0].innerHTML.replace(/(\s|\n|\r)/g, ""), "Passed!");
        });
    });
});