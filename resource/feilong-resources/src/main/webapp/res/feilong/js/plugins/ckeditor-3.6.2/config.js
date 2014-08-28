/*
 * Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	config.language = 'zh-cn';

	config.disableNativeSpellChecker = false;
	config.scayt_autoStartup = false;
	config.font_names = '宋体/宋体;微软雅黑/微软雅黑;黑体/黑体;仿宋/仿宋_UTF-8;楷体/楷体_UTF-8;隶书/隶书;幼圆/幼圆;'
			+ config.font_names;
	// config.width = 900;   
	// config.height = 300;   
	config.skin = 'kama';
	// 背景颜色   
	// config.uiColor = '#BFEFFF';   
	config.toolbar_Full = [
			['PasteText', 'PasteFromWord'], // 'Cut','Copy','Paste',,,'-','Print',
											// 'SpellChecker','Scayt'
			//  ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],  
			['Bold', 'Italic', 'Underline'], // ,'-','Subscript','Superscript','Strike'
			['Font', 'FontSize'], // 'Styles','Format',
			['TextColor', 'BGColor'],
			// ['Form', 'Checkbox', 'Radio', 'TextField','Textarea', 'Select',
			// 'Button', 'ImageButton', 'HiddenField'],        
			['Link'], // ,'Unlink','Anchor'
			['Image', 'Table'], // ,'HorizontalRule','Smiley','SpecialChar','PageBreak'
			['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
			['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent'], // ,'Blockquote'

			['Source'] // ,'-','Templates' ,'Save','NewPage',,'-','Preview'
	//   '/',   

	];

};
