<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>ckfinderTest</title>
		<link rel="stylesheet" href="${domain_css}/res/feilong/css/feilong-all.css" type="text/css"></link>
		<%@ include file="res/feilong/include/js/feilong-js.inc"%>
		<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/ckeditor-3.6.2/ckeditor.js"></script>

	<script type="text/javascript" src="${domain_js}/res/feilong/js/plugins/ckfinder-2.1.1/ckfinder.js"></script>
 
	</head>
	<body> 
	<h1 class="samples">
		CKFinder - Sample - Standalone
	</h1>
	<div class="description">
		CKFinder may be used in standalone mode inside any page, to create a repository
		manager with ease. You may define a custom JavaScript function to be called when
		an image is selected (double-clicked).</div>
	<p style="padding-left: 30px; padding-right: 30px;">
		<script type="text/javascript">

			// This is a sample function which is called when a file is selected in CKFinder.
			function showFileInfo( fileUrl, data )
			{
				var msg = 'The selected URL is: <a href="' + fileUrl + '">' + fileUrl + '</a><br /><br />';
				// Display additional information available in the "data" object.
				// For example, the size of a file (in KB) is available in the data["fileSize"] variable.
				if ( fileUrl != data['fileUrl'] )
					msg += '<b>File url:</b> ' + data['fileUrl'] + '<br />';
				msg += '<b>File size:</b> ' + data['fileSize'] + 'KB<br />';
				msg += '<b>Last modified:</b> ' + data['fileDate'];

				// this = CKFinderAPI object
				this.openMsgDialog( "Selected file", msg );
			}

			// You can use the "CKFinder" class to render CKFinder in a page:
			var finder = new CKFinder();
			// The path for the installation of CKFinder (default = "/ckfinder/").
			finder.basePath = '../';
			// The default height is 400.
			finder.height = 600;
			// This is a sample function which is called when a file is selected in CKFinder.
			finder.selectActionFunction = showFileInfo;
			finder.create();

			// It can also be done in a single line, calling the "static"
			// create( basePath, width, height, selectActionFunction ) function:
			// CKFinder.create( '../', null, null, showFileInfo );

			// The "create" function can also accept an object as the only argument.
			// CKFinder.create( { basePath : '../', selectActionFunction : showFileInfo } );

		</script>
	</p> 
      


	 </body>
</html>
