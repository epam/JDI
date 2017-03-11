/**
 * Created by Dmitry_Lebedev1 on 19/11/2015.
 */

Document.prototype.querySelectorAllArray = function (selector) {
    return Array.prototype.slice.call(this.querySelectorAll(selector));
}

Element.prototype.querySelectorAllArray = function (selector) {
    return Array.prototype.slice.call(this.querySelectorAll(selector));
}

Element.prototype.getCAttribute = function (name) {
    return this.hasAttribute(name) ? this.getAttribute(name) : undefined;
}

$.prototype.getCAttribute = function (name) {
    return this.attr(name);
}

var structElement = function (rawElement, spec) {
    var temp = jdiObject(
        rawElement.getCAttribute(spec.jdi_name),
        rawElement.getCAttribute(spec.jdi_type),
        rawElement.getCAttribute(spec.jdi_gen),
        new Array,
        "[{0}={1}]".format(spec.jdi_name, rawElement.getCAttribute(spec.jdi_name))
    );
    temp.parent = rawElement.getCAttribute(spec.jdi_parent);
    temp.toJSON = function () {
        return {
            type: this.type,
            name: this.name,
            gen: this.gen,
            locator: this.locator,
            section: this.section,
            elements: this.elements.length === 0 ? undefined : this.elements,
        }
    }
    return temp;
}

var structPage = function (packageName) {
    this.name = location.pathname;
    this.url = document.URL;
    this.type = "IPage";
    this.packageName = packageName;
    this.elements = [];
    this.title = document.title;
    this.section = document.title.removeIllegalLetter();
    this.toJSON = function () {
        return {
            name: this.name,
            url: this.url,
            type: this.type,
            packageName: this.packageName,
            title: this.title,
            section: this.section,
            elements: this.elements,
        }
    }
}

/**
 * JSON generator. Generate based on jdi attributes truct.
 * @param {object} attrSpec JDI attributes specification.
 * @param {object} options Some generator options.
 * @param {Element} container Search on container.
 */
var jsonPageGenerator = function (attrSpec, options, container) {
    var _container = container instanceof $ ? container.get(0) : container;
    var _attrSpec = attrSpec;
    var _page = undefined;
    var _options = options === undefined ? {packageName: "com.my.test",} : options;

    var getJDIElements = function () {
        return _container.querySelectorAllArray("[" + _attrSpec.jdi_type + "]");
    }

    var rawElement2Json = function (rawElement) {
        return new structElement(rawElement, _attrSpec);
    };

    var filterParent = function (element) {
        if (element.parent !== undefined)
            return element;
    }

    var filterRoot = function (element) {
        if (element.parent === undefined)
            return element;
    }

    var process = function (prn, cld) {
        for (var i = 0; i < cld.length; i++) {
            for (var j = 0; j < prn.length; j++) {
                if (prn[j].elements !== undefined)
                    if (prn[j].elements.length > 0) {
                        process(prn[j].elements, cld);
                    }
                j = (prn[j] === undefined) ? 0 : j;
                i = (cld[i] === undefined) ? 0 : i;
                if (cld.length === 0) break;
                if (prn[j].name === cld[i].parent) {
                    prn[j].elements.push(cld.splice(i, 1)[0]);
                    i = j = -1;
                    break;
                }
            }
        }
    }

    var processPageElements = function (array) {
        try {
            var rawElements = array;
            var allElements = jQuery.map(rawElements, rawElement2Json);
            $.each(allElements, function (i, e) {
                e.section = e.name.capitalizeFirstLetter();
            });
            var rootElements = jQuery.map(allElements, filterRoot);
            var childElements = jQuery.map(allElements, filterParent);
            process(rootElements, childElements);
            return rootElements;
        } catch (e) {
            console.log(e);
        }
    };

    var translatePage2Struct = function () {
        var elements = getJDIElements();
        _page = new structPage();
        _page = new structPage(_options.packageName);
        _page.elements = processPageElements(elements);
    };

    /**
     * Get page struct before stringify.
     */
    this.getPageStruct = function () {
        if (_page === undefined)
            translatePage2Struct();
        return _page;
    };

    /**
     * Get JSON plain text.
     @param {Function} [replacer]
     @param {Number|String} [space]
     */
    this.getJSON = function (replacer, space) {
        if (_page === undefined) {
            translatePage2Struct();
        }
        return JSON.stringify(_page, replacer, space);
    };
};