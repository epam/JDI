var newLeft = LEFT_POS;
var leftRoller = document.querySelector('.col-sm-5 .ui-slider-handle');
var horizontalLine = document.querySelector('.col-sm-5 .ui-widget-header');
var currentWidth = parseInt(horizontalLine.style['width']);
var currentLeft = parseInt(leftRoller.style['left']);
var leftRollerCurrentLeftPosition = parseInt(leftRoller.style['left']);
var deltaPos = getDeltaWidth(leftRollerCurrentLeftPosition, newLeft);
var rollerLabel = leftRoller.firstChild;
if (!(currentWidth - deltaPos < 0)) {
leftRoller.style['left'] = (newLeft + '%');
horizontalLine.style['left'] = (newLeft + '%');
horizontalLine.style['width'] = ((currentWidth + deltaPos) + '%');
rollerLabel.innerText = newLeft;
}

function getDeltaWidth(current, newPos) {
return (current - newPos);
}