AmCharts.ready(function() {

    $(feilongChart_evaluationType.dataProvider).each(function(i, n) {
	switch (n["name"]) {
	case "表扬":
	    n["color"] = "#04D215";
	    break;
	case "建议":
	    n["color"] = "#FF9E01";
	    break;
	case "无":
	    n["color"] = "#FF0F00";
	    break;

	default:
	    n["color"] = "#000";
	    break;
	}

    });

    $(feilongChart_evaluationType.dataProvider).each(function(i, n) {
	console.log(n);
    });
    createAmPieChart(feilongChart_evaluationType);

    createAmPieChart(feilongChart_storeCategoryName);

    $(feilongChart_come.dataProvider).each(function(i, n) {
	switch (n["name"]) {
	case "计划内签到":
	    n["color"] = "green";
	    break;
	case "请假":
	    n["color"] = "blue";
	    break;
	case "未计划签到":
	    n["color"] = "yellow";
	    break;
	case "没参加又没有请假":
	    n["color"] = "red";
	    break;

	default:
	    n["color"] = "#000";
	    break;
	}

    });

    $(feilongChart_come.dataProvider).each(function(i, n) {
	console.log(n);
    });

    // 柱状图
    createAmSerialChart(feilongChart_come);

    // 雷达图
    createAmRadarChart(feilongChart_avgScore);

});

$(function() {
    // 初始化评分表
    initScoreTable();
    // 课前准备表
    initPreparationBeforeClassTable();
    // 签到表
    initSignTable();

    // 初始化评分表格文字和颜色
    initTdTextAndColor();
    // 初始化 表现好的同学
    initGoodPerformanceStudentTable();
});

function initTdTextAndColor() {
    $(".core5Td").each(function() {
	var value = $(this).text();

	if (5 == value) {
	    $(this).css("color", "#666");
	}
	if (4 == value) {
	    $(this).css("background-color", "#E5AA81");
	}
	if (3 == value) {
	    $(this).css("background-color", "red");
	}
	if (2 == value) {
	    $(this).css("background-color", "#666");
	}
	if (1 == value) {
	    $(this).css("background-color", "black");
	}

    });

    // 对我有用
    $(".usefulToMeScoreTd").each(function() {
	var value = $(this).text();

	if (4 == value) {
	    $(this).find("div").text("√");
	    $(this).css("color", "#666");
	}
	if (3 == value) {
	    $(this).css("background-color", "#E5AA81");
	    $(this).text("一般般");
	}
	if (2 == value) {
	    $(this).css("background-color", "red");
	    $(this).text("太easy了");
	}
	if (1 == value) {
	    $(this).css("background-color", "black");
	    $(this).text("天书不懂");
	}

    });

    // 干货
    $(".ganhuoScoreTd").each(function() {
	var value = $(this).text();

	if (3 == value) {
	    $(this).find("div").text("√");
	    $(this).css("color", "#666");
	}
	if (2 == value) {
	    $(this).text("一般般");
	    $(this).css("background-color", "#E5AA81");
	}
	if (1 == value) {
	    $(this).text("水货");
	    $(this).css("background-color", "#222");
	}
    });

    // 是否满意讲师
    $(".satisfiedTeacherScoreTd").each(function() {
	var value = $(this).text();
	if (3 == value) {
	    $(this).find("div").text("√");
	    $(this).css("color", "#666");
	}
	if (2 == value) {
	    $(this).text("一般般");
	    $(this).css("background-color", "#E5AA81");
	}
	if (1 == value) {
	    $(this).text("没水平");
	    $(this).css("background-color", "#222");
	}
    });
}

function initScoreTable() {

    var p = {
	colModel : [ {
	    display : '姓名',
	    name : 'name',
	    width : 80,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '会议通知',
	    name : 'meetingNoticeScore',
	    width : 43,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '培训时间安排',
	    name : 'meetingTimeScore',
	    width : 43,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '培训组织者态度',
	    name : 'trainOrganizersAttitudeScore',
	    width : 43,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '培训教室布置',
	    name : 'trainRoomLayoutScore',
	    width : 53,
	    sortable : true,
	    align : 'center'
	},

	{
	    display : '现场次序维护',
	    name : 'sceneOrderMaintenanceScore',
	    width : 53,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '表达清晰态度友善',
	    name : 'clearExpressionFriendlyScore',
	    width : 53,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '对授课时间的掌控度',
	    name : 'teachTimeDegreeControlScore',
	    width : 53,
	    sortable : true,
	    align : 'center'
	},

	{
	    display : '现场气氛调节能力',
	    name : 'sceneAtmosphereAdjustmentAbilityScore',
	    width : 53,
	    sortable : true,
	    align : 'center'
	}, {
	    display : 'PPT内容',
	    name : 'pptContentScore',
	    width : 43,
	    sortable : true,
	    align : 'center'
	}, {
	    display : 'PPT设计',
	    name : 'pptDesignScore',
	    width : 43,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '对我有用',
	    name : 'usefulToMeScore',
	    width : 43,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '干货',
	    name : 'ganhuoScore',
	    width : 30,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '满意老师',
	    name : 'satisfiedTeacherScore',
	    width : 43,
	    sortable : true,
	    align : 'center'
	} ],
	// sortname : "name",
	// sortorder : "asc",

	blockOpacity : 0.8,
	showTableToggleBtn : true,
	resizable : false,
	width : 880,
    // rp: 10,
    // usepager:true
    };

    var maxRows = getMaxRows("#scoreTable");

    $("#scoreTable").each(function() {
	// console.log("maxRows:%i", maxRows);

	var firstTR = $(this).find("tr").eq(0);
	// console.log("firstTR:%o", firstTR);
	// console.log("firstTR.html():%o", firstTR.html());

	var firstTROuterHeight = firstTR.outerHeight(true);
	// console.log("firstTROuterHeight:%i", firstTROuterHeight);

	logJqueryObjectSize($(this).find("tr").eq(0));

	var height = firstTROuterHeight * maxRows + 12;
	console.log("flexgrid table height:[%i]", height);

	var p1 = $.extend({
	    title : "培训反馈评分明细表",
	    height : "auto"
	}, p);
	$(this).flexigrid(p1);
    });
}
function initPreparationBeforeClassTable() {
    var p = {
	colModel : [ {
	    display : '清单',
	    name : 'name',
	    width : 120,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '状态',
	    name : 'meetingNoticeScore',
	    width : 43,
	    sortable : true,
	    align : 'center'
	} ],
	// sortname : "name",
	// sortorder : "asc",

	blockOpacity : 0.8,
	showTableToggleBtn : true,
	resizable : false,
	width : 200,
    };

    var maxRows = getMaxRows("#preparationBeforeClassTable");

    $("#preparationBeforeClassTable").each(function() {
	var p1 = $.extend({
	    // title : "培训反馈评分明细表",
	    height : "auto"
	}, p);
	$(this).flexigrid(p1);
    });
}
function initSignTable() {
    var p = {
	colModel : [ {
	    display : '类型',
	    name : 'name',
	    width : 200,
	    sortable : true,
	// align : 'center'
	}, {
	    display : '人数',
	    name : 'meetingNoticeScore',
	    width : 43,
	    sortable : true,
	    align : 'center'
	} ],
	// sortname : "name",
	// sortorder : "asc",

	blockOpacity : 0.8,
	showTableToggleBtn : true,
	resizable : false,
	width : 280,
    };

    var maxRows = getMaxRows("#signTable");

    $("#signTable").each(function() {
	var p1 = $.extend({
	    // title : "培训反馈评分明细表",
	    height : "auto"
	}, p);
	$(this).flexigrid(p1);
    });
}

function initGoodPerformanceStudentTable() {
    var p = {
	colModel : [ {
	    display : '姓名',
	    name : 'name',
	    width : 80,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '项目类型',
	    // name : 'evaluationContent',
	    width : 80,
	    sortable : true,
	    align : 'left'
	}, {
	    display : '项目名称',
	    // name : 'evaluationContent',
	    width : 80,
	    sortable : true,
	    align : 'left'
	}

	],
	// sortname : "name",
	// sortorder : "asc",
	blockOpacity : 0.8,
	showTableToggleBtn : true,
	resizable : false,
	width : 300,
    // height : 100,
    // rp: 10,
    // usepager:true
    };

    var maxRows = getMaxRows("#goodPerformanceStudentTable");

    $("#goodPerformanceStudentTable").each(function() {
	console.log("maxRows:%i", maxRows);

	var height = maxRows * 28 + 12;
	console.log("height:%i", height);

	var p1 = $.extend({
	    /* title : "20140827Java集合框架培训评分表", */
	    height : "auto"
	}, p);
	$(this).flexigrid(p1);
    });
}