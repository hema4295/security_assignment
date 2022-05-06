
/**
 * ajax post request
 */
function ajaxPostRequest(url, obj) {
    var rtnobj;
    $.ajax({
        url: url,
        type: 'post',
        data: obj,
        async: false,
        dataType: 'json',
        cache: false,
        error: function () {
            rtnobj = 'error';
        },
        success: function (data) {
            rtnobj = data;
        }
    });
    return rtnobj;
}

/**
 * ajax get request
 */
function ajaxGetRequest(url) {
    var rtnobj;
    $.ajax({
        url: url,
        type: 'GET',
        async: false,
        dataType: 'json',
        cache: false,
        error: function () {
            rtnobj = 'error';
        },
        success: function (data) {
            rtnobj = data;
        }
    });
    return rtnobj;
}

/**
 * ajax get request & response text
 */
function ajaxGetRequestResponseText(url) {
    var rtnobj;
    $.ajax({
        url: url,
        type: 'GET',
        async: false,
        cache: false,
        error: function () {
            rtnobj = 'error';
        },
        success: function (data) {
            rtnobj = data;
        },

    });
    return rtnobj;
}

function ajaxPostRequestJson(url, obj) {
    var rtnobj;
    $.ajax({
        url: url,
        type: 'post',
        data: obj,
        async: false,
        dataType: 'json',
        contentType: 'application/json;charset=utf-8',
        cache: false,
        error: function () {
            rtnobj = 'error';
        },
        success: function (data) {
            rtnobj = data;
        }
    });
    return rtnobj;
}