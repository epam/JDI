var newRight = RIGHT_POS;
var leftRoller = document.querySelector('.col-sm-5 .ui-slider-handle');
var rightRoller = leftRoller.nextSibling.nextSibling;
var horizontalLine = document.querySelector('.col-sm-5 .ui-widget-header');
var currentWidth = parseInt(horizontalLine.style['width']);
var currentLeft = parseInt(rightRoller.style['left']);
var rightRollerCurrentLeftPosition = parseInt(rightRoller.style['left']);
var deltaPos = getDeltaWidth(rightRollerCurrentLeftPosition, newRight);
var rollerLabel = rightRoller.firstChild;

if (!(currentWidth - deltaPos < 0)) {
rightRoller.style['left'] = (newRight + '%');
horizontalLine.style['width'] = ((currentWidth - deltaPos) + '%');
rollerLabel.innerText = newRight;
}

function getDeltaWidth(current, newPos) {
    return (current - newPos);
}
