var highlightColor = 'rgba(219, 104, 68, 0.7)';
var originColors = [];
function originalColor (_locator, _color){
    return{
        locator: _locator,
        color: _color
    };
}

function highlightElement(locator){
    restoreAllElementsBackground();

    try {
        restoreActualElementColor();
        originColors.push(originalColor(locator, $(locator).css("background-color")));
        $(locator).css("background-color", highlightColor);
    }
    catch (e) {}
}

function restoreAllElementsBackground(){
    $.each(originColors, function(ind, val){
        $(val.locator).css("background-color", val.color);
    })
    originColors = [];
}

function  restoreAllElementsHighlighting(elements){
    $.each(elements, function(ind, val){
        $(val.locator).css("background-color", highlightColor);
    })
}