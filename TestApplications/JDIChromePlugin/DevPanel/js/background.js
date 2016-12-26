chrome.runtime.onMessage.addListener(
    function (request, sender, sendResponse) {

        switch (request.name) {
            case requestName.executeContentScript:
                executeContextScript(request);
                break;
            case requestName.releaseElementLocationState:
                executeContextScript(request);
                break;
            case requestName.addMouseMoveKeyPressEvent:
                sendMessageToContent(request);
                break;
            case requestName.releaseMouseMoveKeyPressEvent:
                sendMessageToContent(request);
                break;
            case requestName.jdiFromContentSaveClicked:
                saveJDIObjectToStorage(request.data, sender);
                break;
            case requestName.savePageJSONByJDIElementsToStorage:
                savePageJSONByJDIElementsToStorage(request.tabId);
                break;
            case requestName.highlightElementOnWeb:
                sendMessageToContent(request);
                break;
            case requestName.restoreAllElementBackgroundColorOnWeb:
                sendMessageToContent(request);
                break;
            case requestName.checkBoxLocationActive:{
                sendMessageToContent(request);
                break;
            }case requestName.checkBoxLocationInactive:{
                sendMessageToContent(request);
                break;
            }
            default :
                alert("request "+request.name+" not supported in background")
        }

    });

function savePageJSONByJDIElementsToStorage(tabId){
    chrome.tabs.query({active: true, currentWindow: true}, function(tabs) {
        chrome.tabs.sendMessage(tabId,{name: requestName.getPageJSONByJDIElements}, function(response) {
            saveToLocalStorage({data: response, tabId: tabId}, storageSegment.jdi_page);
        });
    });
}

function sendMessageToContent(data){
    chrome.tabs.query({active: true, currentWindow: true}, function(tabs) {
        chrome.tabs.sendMessage(data.tabId, {name: data.name, message: data});
    });
}

function saveToLocalStorage(obj, segmentName){

    chrome.storage.local.set({jdi_page: obj});
}

function saveJDIObjectToStorage(data, sender) {
    chrome.storage.local.set({'jdi_object': {data: data, tabId: sender.tab.id}});
   // saveToLocalStorage({data: data, pageId: pageId}, storageSegment.jdi_object)
}

function addJDIObjectToStorage(data) {
    var $a = [];
    chrome.storage.local.get("jdi_object", function (e2) {
        if (e2.jdi_object != undefined)
            $a = e2.jdi_object;
        console.log("2 from array " + $a);
        $a.push(data);
        console.log("3 from array second" + $a);
        chrome.storage.local.set({'jdi_object': $a});
    });

}

function addMouseMoveKeyPressToPage(data) {
    chrome.tabs.executeScript(data.tabId, {file: data.scriptToExecute});
}

function executeContextScript(data) {
    chrome.tabs.executeScript(data.tabId, {file: data.scriptToExecute});
}