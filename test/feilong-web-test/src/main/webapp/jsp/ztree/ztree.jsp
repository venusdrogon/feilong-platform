<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ztree.jsp</title>
<link rel="stylesheet" href="${domain_css}/res/feilong/js/plugins/jquery.ztree/zTreeStyle.css" type="text/css"></link>
<style type="text/css">
.ztree li span.button.switch.level0 {
	visibility: hidden;
	width: 1px;
}

.ztree li ul.level0 {
	padding: 0;
	background: none;
}
</style>


<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/jquery.ztree/jquery.ztree.all-3.5.js"></script>

<SCRIPT type="text/javascript">
<!--
    var setting = {

	edit : {
	    enable : true,
	    showRemoveBtn : false,
	    showRenameBtn : false
	},
	view : {
	    showLine : true,
	    showIcon : false,
	    nameIsHTML : true,
	    selectedMulti : false,
	    dblClickExpand : dblClickExpand
	},

	callback : {
	    //beforeClick: beforeClick,
	    onClick : onClick,
	    beforeDrag : beforeDrag,
	    beforeDrop : beforeDrop
	}
    };

    var zNodes = [
	    {
		name : "ROOT",
		open : true,
		children : [

			{
			    name : "父节点1 - 展开<span style='color:red;margin-right:0px;'>^_^</span>",
			    open : true,
			    children : [ {
				name : "父节点11 - 折叠",
				children : [ {
				    name : "叶子节点111"
				}, {
				    name : "叶子节点112"
				}, {
				    name : "叶子节点113"
				}, {
				    name : "叶子节点114"
				} ]
			    }, {
				name : "父节点12 - 折叠",
				children : [ {
				    name : "叶子节点121"
				}, {
				    name : "叶子节点122"
				}, {
				    name : "叶子节点123"
				}, {
				    name : "叶子节点124"
				} ]
			    }, {
				name : "父节点13 - 没有子节点",
				isParent : true
			    } ]
			}, {
			    name : "父节点2 - 折叠",
			    children : [ {
				name : "父节点21 - 展开",
				open : true,
				children : [ {
				    name : "叶子节点211"
				}, {
				    name : "叶子节点212"
				}, {
				    name : "叶子节点213"
				}, {
				    name : "叶子节点214"
				} ]
			    }, {
				name : "父节点22 - 折叠",
				children : [ {
				    name : "叶子节点221"
				}, {
				    name : "叶子节点222"
				}, {
				    name : "叶子节点223"
				}, {
				    name : "叶子节点224"
				} ]
			    }, {
				name : "父节点23 - 折叠",
				children : [ {
				    name : "叶子节点231"
				}, {
				    name : "叶子节点232"
				}, {
				    name : "叶子节点233"
				}, {
				    name : "叶子节点234"
				} ]
			    } ]
			}, {
			    name : "父节点3 - 没有子节点",
			    isParent : true
			} ]
	    },

    ];

    var nameCount = 0, iconCount = 1, color = [ 0, 0, 0 ];
    function updateNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), type = e.data.type, nodes = zTree
		.getSelectedNodes();
	if (nodes.length == 0) {
	    alert("请先选择一个节点");
	}
	for (var i = 0, l = nodes.length; i < l; i++) {
	    zTree.setting.view.fontCss = {};
	    if (type == "rename") {
		nodes[i].name = nodes[i].name.replace(/_[\d]*$/g, "") + "_"
			+ (nameCount++);
	    } else if (type == "icon") {
		if (iconCount > 8) {
		    nodes[i].iconSkin = null;
		    iconCount = 1;
		} else if (nodes[i].isParent) {
		    nodes[i].iconSkin = nodes[i].iconSkin ? null : "pIcon01";
		} else {
		    nodes[i].iconSkin = "icon0" + (iconCount++);
		}
	    } else if (type == "color") {
		color = [ 0, 0, 0 ];
		var r1 = Math.round(Math.random() * 3 - 0.5);
		color[r1] = 15;
		var r2 = Math.round(Math.random() * 3 - 0.5);
		while (r2 === r1) {
		    r2 = Math.round(Math.random() * 3 - 0.5);
		}
		color[r2] = Math.round(Math.random() * 16 - 0.5);
		zTree.setting.view.fontCss["color"] = "#"
			+ color[0].toString(16) + color[1].toString(16)
			+ color[2].toString(16);
	    } else if (type == "font") {
		var style = $("#" + nodes[i].tId + "_a").css("font-style");
		style = (style == "italic" ? "normal" : "italic");
		zTree.setting.view.fontCss["font-style"] = style;
	    }
	    zTree.updateNode(nodes[i]);
	}
    }

    var log, className = "dark";
    /* function beforeClick(treeId, treeNode, clickFlag) {
     className = (className === "dark" ? "":"dark");
     alert("[ "+new Date()+" beforeClick ]&nbsp;&nbsp;" + treeNode.name );
     return (treeNode.click != false);
     } */
    function onClick(event, treeId, treeNode, clickFlag) {
	alert(" clickFlag = "
		+ clickFlag
		+ " ("
		+ (clickFlag === 1 ? "普通选中" : (clickFlag === 0 ? "<b>取消选中</b>"
			: "<b>追加选中</b>")) + ")" + treeId + treeNode.name);
    }

    function beforeDrag(treeId, treeNodes) {
	for (var i = 0, l = treeNodes.length; i < l; i++) {
	    if (treeNodes[i].drag === false) {
		return false;
	    }
	}
	return true;
    }
    function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	return targetNode ? targetNode.drop !== false : true;
    }

    function dblClickExpand(treeId, treeNode) {
	return treeNode.level > 0;
    }
    $(document).ready(function() {
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	$("#rename").bind("click", {
	    type : "rename"
	}, updateNode);

	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.setting.edit.showRemoveBtn = true;
	zTree.setting.edit.showRenameBtn = true;
	zTree.setting.edit.removeTitle = "remove";
	zTree.setting.edit.renameTitle = "rename";
    });
//-->
</SCRIPT>

</head>
<body>
	<ul id="treeDemo" class="ztree"></ul>

	<br />

	<a id="rename" href="#" onclick="return false;">换名字</a>
</body>
</html>
