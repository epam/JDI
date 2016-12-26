var saveFile = function() {

    this.asJava = function (data, fileName){
        var blob = new Blob(data, {type: "text/plain;charset=utf-8"});
        saveAs(blob, "{0}.java".format(fileName));
    };

    this.asJavaZip = function(data, fileName){
        var zip = new JSZip();
        $.each(data, function (i, val) {
            zip.file("{0}.java".format(val.name), val.data);
        });
        var blob = zip.generate({type:"blob"});
        saveAs(blob, "{0}.zip".format(fileName));
    }

}