chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
        switch (request.name){
            case requestName.getPageJSONByJDIElements:{
                var json = new jsonPageGenerator(jdiTags, undefined, document);
                sendResponse(json.getJSON());}
                break;
            case requestName.highlightElementOnWeb:{
                highlightElement(request.message.cssLocator);
            }
                break;
            case requestName.restoreAllElementBackgroundColorOnWeb:{
                restoreAllElementsBackground();
                break;
            }
            case requestName.addMouseMoveKeyPressEvent:{
                addMouseMoveAndKeyPressEvent();
                break;
            }
            case requestName.releaseMouseMoveKeyPressEvent:{
                releaseMouseMoveAndKeyPressEvent();
                break;
            }
            case requestName.checkBoxLocationActive:{
                releaseMouseMoveAndKeyPressEvent();
                restoreAllElementsBackground();
                highlightElement(request.message.cssLocator);
                addMouseMoveAndKeyPressEvent();
                break;
            }
            case requestName.checkBoxLocationInactive:{
                restoreAllElementsBackground();
                releaseMouseMoveAndKeyPressEvent();
                break;
            }
            default:
                alert("wrong response");
        }
});
