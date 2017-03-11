var $actualBackgroundColore = "";
var $actualElementFromPoint = "";

function addMouseMoveAndKeyPressEvent() {

    $(document).on("mouseout", function (e) {
        restoreActualElementColor();
        restoreAllElementsHighlighting(originColors);
    });

    $(document).on("mouseover", function (e) {
        fillActualElementColor(e.target);
        e.target.style.backgroundColor = "#93DBD5";
        restoreAllElementsHighlighting(originColors);
    });

    $(document).keypress(function (e) {

        if (e.which === 115) {
            var jdi = jdiObject(
                $actualElementFromPoint.getAttribute("jdi-name"),
                $actualElementFromPoint.getAttribute("jdi-type"),
                $actualElementFromPoint.getAttribute("jdi-gen"),
                undefined,
                "[jdi-name=" + $actualElementFromPoint.getAttribute("jdi-name") + ']');
            jdi.section = undefined;

            chrome.runtime.sendMessage({
                name: requestName.jdiFromContentSaveClicked,
                data: jdi
            });
        }
        ;
    });
}

function releaseMouseMoveAndKeyPressEvent() {

    restoreActualElementColor();

    $(document).off("mouseout");
    $(document).off("mouseover");
    $(document).off("keypress");
}

function restoreActualElementColor() {
    if ($actualElementFromPoint !== "" && $actualElementFromPoint !== undefined)
        $actualElementFromPoint.style.backgroundColor = $actualBackgroundColore;

    $actualBackgroundColore = "";
    $actualElementFromPoint = "";
}

function fillActualElementColor(target) {
    $actualElementFromPoint = target;
    $actualBackgroundColore = $(target).css('background-color');
}

