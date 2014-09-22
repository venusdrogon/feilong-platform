window.console = window.console || {};
console.log || (console.log = opera.postError);

/**
 * 获得最大的行数
 * 
 * @param jqueryFlexigridTableSelector
 *                jquery Flexigrid Table 选择器，支持多组选择
 * @returns 返回其中最大的行 {Number}
 */
function getMaxRows(jqueryFlexigridTableSelector) {
    var max = 0;

    // console.debug($("#trainSignUpFlexigridTable tr").length);
    // console.log($("#listTable table tr").length);

    $(jqueryFlexigridTableSelector).each(function() {
	var l = $(this).find("tr").length;
	console.log("\"%s\" tr's length is:[%i]",jqueryFlexigridTableSelector,l);

	if (l > max) {
	    max = l;
	}
    });
    return max;
}

function logJqueryObjectSize(jqueryObject) {

    var logObject = {};

    logObject.outerHeightTrue = jqueryObject.outerHeight(true);

    // 获取第一个匹配元素外部高度（默认包括补白和边框）。
    // 设置为 true 时,计算边距在内。
    logObject.outerHeight = jqueryObject.outerHeight();

    // 获取匹配元素在当前视口的相对偏移。
    logObject.offset = jqueryObject.offset();

    // 获取匹配元素相对父元素的偏移
    // 为精确计算结果，请在补白、边框和填充属性上使用像素单位。此方法只对可见元素有效。
    logObject.position = jqueryObject.position();

    // 获取匹配元素相对滚动条左侧的偏移
    logObject.scrollLeft = jqueryObject.scrollLeft();

    // 获取匹配元素相对滚动条顶部的偏移。
    logObject.scrollTop = jqueryObject.scrollTop();

    // 取得第一个匹配元素当前计算的高度值（px）
    logObject.height = jqueryObject.height();

    // 取得第一个匹配元素当前计算的宽度值（px）。
    logObject.width = jqueryObject.width();

    // 获取第一个匹配元素内部区域高度（包括补白、不包括边框）
    logObject.innerHeight = jqueryObject.innerHeight();

    // 获取第一个匹配元素外部宽度（默认包括补白和边框）。
    logObject.outerWidth = jqueryObject.outerWidth();
    logObject.outerWidthTrue = jqueryObject.outerWidth(true);
    
    logObject.html = jqueryObject.html();
    logObject.text = jqueryObject.text();

    console.log("logObject:%o", logObject);
}