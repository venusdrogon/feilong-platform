window.console = window.console || {};
console.log || (console.log = opera.postError);

$(function() {
    var p = {
	colModel : [ {
	    display : '编号',
	    name : 'id',
	    width : 42,
	    sortable : true,
	    align : 'center'
	}, {
	    display : '姓名/邮箱前缀',
	    name : 'name',
	    width : 140,
	    sortable : true,
	    align : 'left'
	}, {
	    display : '部门',
	    name : 'storeCategoryName',
	    width : 96,
	    sortable : true,
	    align : 'left'
	}, ],
	// sortname : "name",
	// sortorder : "asc",

	blockOpacity : 0.8,
	//showTableToggleBtn : true,
	resizable : false,
	width : 360,
    // height : 826,
    // rp: 10,
    // usepager:true
    };

    var maxRows = getMaxRows("#trainSignUpFlexigridTable,#nextTrainSignUpFlexigridTable") ;
    $("#trainSignUpFlexigridTable,#nextTrainSignUpFlexigridTable").each(function() {
	var p1 = $.extend({
	    title : $(this).attr("titleValue"),
	    // height : $(this).attr("height")
	    height : maxRows *41 + 12
	}, p);
	$(this).flexigrid(p1);
    });
});