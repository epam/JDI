/**
 * Created by Dmitry_Lebedev1 on 23/11/2015.
 */
var resetValues = function (data) {
    data.title = "title";
    data.url = "url";
    data.name = "name";
}

var init = function () {
    $.each($("h3"), function (i, e) {
        $(e).parent().children("pre, main").css("display", "none");
        e.onclick = function () {
            if ($(this).attr("hide") !== undefined) {
                $($(this).parent()).children("pre, main").css("display", "block")
                $(this).removeAttr("hide");
                return;
            }
            $($(this).parent()).children("pre, main").css("display", "none")
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

function runTests() {
    var c = $("[id=testData]").children();
    $.each(c, function (i, e) {
        var name = e.getElementsByTagName("h3")[0].innerHTML;
        QUnit.test("test " + name, function (assert) {
            console.log("test " + name);
            var dataContainer = e.getElementsByTagName("pre")[0];
            var resultContainer = $(e.getElementsByTagName("pre")[1]);
            var data = JSON.parse(dataContainer.innerHTML);
            var java = translateToJava(data);
            var text = "";
            $.each(java, function(i, e){
                text += e.data;
            });
            resultContainer.text(text);
            assert.ok(1 === 1,"Passed");
            //assert.ok(jsonGen.getJSON() === e.getElementsByTagName("pre")[0].innerHTML.replace(/(\s|\n|\r)/g, ""), "Passed!");
        });
    });
}
$(document).ready(function () {
    init();
    runTests();
    //var data = JSON.parse($("[id=form]").text())
    //var java = translateToJava(data);
    //java.getCombElements();
    //console.log(java);
    //var a = translateToJava(JSON.parse($("[id=section]").text()));
    //console.log(a.data);
    //var e = $("[id=res]");
    //$.each(a, function (i, elem) {
    //    var temp = e.text();
    //    e.text(temp + elem.data);
    //});
});