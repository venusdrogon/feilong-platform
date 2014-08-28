<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>ckeditorTest</title>
		<link rel="stylesheet" href="res/feilong/css/feilong-all.css" type="text/css"></link>
		
		<script type="text/javascript" src="res/feilong/js/plugins/ckeditor-3.6.2/ckeditor.js"></script>

	</head>
	<body>
        <textarea id="TextArea1" cols="20" rows="2" ><p>
	<span style="color:#ff0000;"><span style="font-size:14px;"><span style="font-family:georgia,serif;">asdfasdfasdf</span></span></span></p>
</textarea> 		
		
		<input type="button" id="btn_submit" value="提交" class="feilongButton"/>
		
		
 <textarea id="TextArea2" ></textarea> 		
		<script type="text/javascript">
CKEDITOR.replace( 'TextArea1',
	{extraPlugins : 'uicolor'
	//,
		//toolbar : [ [ 'Bold', 'Italic' ], [ 'UIColor' ] ]
	});
	
	$("#btn_submit").click(function(){ 
    	var oEditor = CKEDITOR.instances.TextArea1; 
     
		
		$("#TextArea2").val(oEditor.getData());
	});
</script>

<input name="data[pictureUrl]" type="text" id="picname" style="width:240px" /> 
<input type="button" name="Submit2" value="站内选择或上传" class="button" style="margin-left:8px;" />


<script type="text/javascript">
function BrowseServer(inputId)
{
      var finder =new CKFinder() ;
       finder.basePath ='../ckfinder/';  //导入CKFinder的路径
       finder.selectActionFunction = SetFileField; //设置文件被选中时的函数
       finder.selectActionData = inputId;  //接收地址的input ID
       finder.popup() ;
}

//文件选中时执行
function SetFileField(fileUrl,data)
{
       document.getElementById(data["selectActionData"]).value = fileUrl ;
}
</script>


	 </body>
</html>
