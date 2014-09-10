AmCharts.ready(function() {
    // createAmPieChart(feilongChart_come);
    createAmPieChart(feilongChart_evaluationType);

    createAmPieChart(feilongChart_storeCategoryName);

    // 柱状图
    createAmSerialChart(feilongChart_come);
});

$(function() {
    // 初始化评分表
    initScoreTable();
    // 初始化表扬表
    initPraiseTable();
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
	    $(this).css("background-color", "red");
	    $(this).text("一般般");
	}
	if (2 == value) {
	    $(this).css("background-color", "#666");
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
	    $(this).css("background-color", "#666");
	}
	if (1 == value) {
	    $(this).text("水货");
	    $(this).css("background-color", "black");
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
	    $(this).css("background-color", "#666");
	}
	if (1 == value) {
	    $(this).text("没水平");
	    $(this).css("background-color", "black");
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
	    width : 43,
	    sortable : true,
	    align : 'center'
	},

	{
	    display : '现场次序维护',
	    name : 'sceneOrderMaintenanceScore',
	    width : 43,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '表达清晰态度友善',
	    name : 'clearExpressionFriendlyScore',
	    width : 43,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '对授课时间的掌控度',
	    name : 'teachTimeDegreeControlScore',
	    width : 43,
	    sortable : true,
	    align : 'center'
	},

	{
	    display : '现场气氛调节能力',
	    name : 'sceneAtmosphereAdjustmentAbilityScore',
	    width : 43,
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
	width : 800,
	height : 1243,
    // rp: 10,
    // usepager:true
    };

    $("#scoreTable").each(function() {
	var p1 = $.extend({
	    title : "培训反馈评分表",
	// height : $(this).attr("height")
	}, p);
	$(this).flexigrid(p1);
    });

}

function initPraiseTable() {
    var p = {
	colModel : [ {
	    display : '姓名',
	    name : 'name',
	    width : 80,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '评价',
	    name : 'evaluationContent',
	    width : 640,
	    sortable : true,
	    align : 'left'
	} ],
	// sortname : "name",
	// sortorder : "asc",

	blockOpacity : 0.8,
	showTableToggleBtn : true,
	resizable : false,
	width : 760,
	height : 780,
    // rp: 10,
    // usepager:true
    };

    // $("#praiseTable").each(function() {
    // var p1 = $.extend({
    // title : "20140827Java集合框架培训评价-表扬",
    // // height : $(this).attr("height")
    // }, p);
    // $(this).flexigrid(p1);
    // });
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
	height : 100,
    // rp: 10,
    // usepager:true
    };

    $("#goodPerformanceStudentTable").each(function() {
	var p1 = $.extend({
	/* title : "20140827Java集合框架培训评分表", */
	// height : $(this).attr("height")
	}, p);
	$(this).flexigrid(p1);
    });

}