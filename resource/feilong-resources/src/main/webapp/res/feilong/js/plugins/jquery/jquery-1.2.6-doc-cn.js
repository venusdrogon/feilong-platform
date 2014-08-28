/*
 * Comment version: 1.2.6a
 * $Date: 2008-05-24 14:22:17 -0400 (Sat, 24 May 2008) $
 */
(function(){
// Map over jQuery in case of overwrite
var _jQuery = window.jQuery,
// Map over the $ in case of overwrite
	_$ = window.$;

var jQuery = window.jQuery = window.$ = function( selector, context ) {
	///	<summary>
    ///		1: $(expression, context) - 这个函数接收一个包含 CSS 选择器的字符串，然后用这个字符串去匹配一组元素。
    ///		2: $(html) - 根据提供的原始 HTML 标记字符串，动态创建由 jQuery 对象包装的 DOM 元素。
    ///		3: $(elements) - 将一个或多个DOM元素转化为jQuery对象。
    ///		4: $(callback) - $(document).ready()的简写。
	///	</summary>
	///	<param name="selector" type="String">
    ///		1: expression - 用来查找的表达式。
    ///		2: html -用于动态创建DOM元素的HTML标记字符串
    ///		3: elements - 用于封装成jQuery对象的DOM元素
    ///		4: callback - 当DOM加载完成后，执行其中的函数。
	///	</param>
	///	<param name="context" type="jQuery">
    ///		1: context - (可选) 作为待查找的 DOM 元素集、文档或 jQuery 对象。
	///	</param>
	///	<returns type="jQuery" />

	// The jQuery object is actually just the init constructor 'enhanced'
	return new jQuery.fn.init( selector, context );
};

// A simple way to check for HTML strings or ID strings
// (both of which we optimize for)
var quickExpr = /^[^<]*(<(.|\s)+>)[^>]*$|^#(\w+)$/,

// Is it a simple selector
	isSimple = /^.[^:#\[\.]*$/,

// Will speed up references to undefined, and allows munging its name.
	undefined;

jQuery.fn = jQuery.prototype = {
	init: function( selector, context ) {
		///	<summary>
	    ///		1: $(expression, context) - 这个函数接收一个包含 CSS 选择器的字符串，然后用这个字符串去匹配一组元素。
    	///		2: $(html) - 根据提供的原始 HTML 标记字符串，动态创建由 jQuery 对象包装的 DOM 元素。
	    ///		3: $(elements) - 将一个或多个DOM元素转化为jQuery对象。
	    ///		4: $(callback) - $(document).ready()的简写。
		///	</summary>
		///	<param name="selector" type="String">
	    ///		1: expression - 用来查找的表达式。
	    ///		2: html -用于动态创建DOM元素的HTML标记字符串
	    ///		3: elements - 用于封装成jQuery对象的DOM元素
	    ///		4: callback - 当DOM加载完成后，执行其中的函数。
		///	</param>
		///	<param name="context" type="jQuery">
    	///		1: context - (可选) 作为待查找的 DOM 元素集、文档或 jQuery 对象。
		///	</param>
		///	<returns type="jQuery" />

		// Make sure that a selection was provided
		selector = selector || document;

		// Handle $(DOMElement)
		if ( selector.nodeType ) {
			this[0] = selector;
			this.length = 1;
			return this;
		}
		// Handle HTML strings
		if ( typeof selector == "string" ) {
			// Are we dealing with HTML string or an ID?
			var match = quickExpr.exec( selector );

			// Verify a match, and that no context was specified for #id
			if ( match && (match[1] || !context) ) {

				// HANDLE: $(html) -> $(array)
				if ( match[1] )
					selector = jQuery.clean( [ match[1] ], context );

				// HANDLE: $("#id")
				else {
					var elem = document.getElementById( match[3] );

					// Make sure an element was located
					if ( elem ){
						// Handle the case where IE and Opera return items
						// by name instead of ID
						if ( elem.id != match[3] )
							return jQuery().find( selector );

						// Otherwise, we inject the element directly into the jQuery object
						return jQuery( elem );
					}
					selector = [];
				}

			// HANDLE: $(expr, [context])
			// (which is just equivalent to: $(content).find(expr)
			} else
				return jQuery( context ).find( selector );

		// HANDLE: $(function)
		// Shortcut for document ready
		} else if ( jQuery.isFunction( selector ) )
			return jQuery( document )[ jQuery.fn.ready ? "ready" : "load" ]( selector );

		return this.setArray(jQuery.makeArray(selector));
	},

	// The current version of jQuery being used
	jquery: "1.2.6",

	// The number of elements contained in the matched element set
	size: function() {
		///	<summary>
		///		The number of elements currently matched.
		///		Part of Core
		///	</summary>
		///	<returns type="Number" />

		return this.length;
	},

	// The number of elements contained in the matched element set
	length: 0,

	// Get the Nth element in the matched element set OR
	// Get the whole matched element set as a clean array
	get: function( num ) {
		///	<summary>
		///		Access a single matched element. num is used to access the
		///		Nth element matched.
		///		Part of Core
		///	</summary>
		///	<returns type="Element" />
		///	<param name="num" type="Number">
		///		Access the element in the Nth position.
		///	</param>

		return num == undefined ?

			// Return a 'clean' array
			jQuery.makeArray( this ) :

			// Return just the object
			this[ num ];
	},

	// Take an array of elements and push it onto the stack
	// (returning the new matched element set)
	pushStack: function( elems ) {
		///	<summary>
		///		Set the jQuery object to an array of elements, while maintaining
		///		the stack.
		///		Part of Core
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="elems" type="Elements">
		///		An array of elements
		///	</param>
		
		// Build a new jQuery matched element set
		var ret = jQuery( elems );

		// Add the old object onto the stack (as a reference)
		ret.prevObject = this;

		// Return the newly-formed element set
		return ret;
	},

	// Force the current matched set of elements to become
	// the specified array of elements (destroying the stack in the process)
	// You should use pushStack() in order to do this, but maintain the stack
	setArray: function( elems ) {
		///	<summary>
		///		Set the jQuery object to an array of elements. This operation is
		///		completely destructive - be sure to use .pushStack() if you wish to maintain
		///		the jQuery stack.
		///		Part of Core
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="elems" type="Elements">
		///		An array of elements
		///	</param>
		
		// Resetting the length to 0, then using the native Array push
		// is a super-fast way to populate an object with array-like properties
		this.length = 0;
		Array.prototype.push.apply( this, elems );

		return this;
	},

	// Execute a callback for every element in the matched set.
	// (You can seed the arguments with an array of args, but this is
	// only used internally.)
	each: function( callback, args ) {
		///	<summary>
	    ///		以每一个匹配的元素作为上下文来执行一个函数。
	    ///		意味着，每次执行传递进来的函数时，
	    ///		函数中的this关键字都指向一个不同的DOM元素
	    ///		（每次都是一个不同的匹配元素）。
	    ///		而且，在每次执行函数时，
	    ///		都会给函数传递一个表示作为执行环境的元素在匹配的元素集合中所处位置的数字值作为参数
	    ///		（从零开始的整形）。 
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="callback" type="Function">
	    ///		对于每个匹配的元素所要执行的函数
		///	</param>

		return jQuery.each( this, callback, args );
	},

	// Determine the position of an element within
	// the matched set of elements
	index: function( elem ) {
		///	<summary>
	    ///		搜索与参数表示的对象匹配的元素，
	    ///		并返回相应元素的索引值值。
	    ///		如果找到了匹配的元素，从0开始返回；如果没有找到匹配的元素，返回-1。
		///		Part of Core
		///	</summary>
		///	<returns type="Number" />
		///	<param name="elem" type="Element">
	    ///		要搜索的对象
		///	</param>

		var ret = -1;

		// Locate the position of the desired element
		return jQuery.inArray(
			// If it receives a jQuery object, the first element is used
			elem && elem.jquery ? elem[0] : elem
		, this );
	},

	attr: function( name, value, type ) {
		///	<summary>
	    ///		为所有匹配的元素设置一个计算的属性值。
	    ///		不提供值，而是提供一个函数，由这个函数计算的值作为属性值。
		///		Part of DOM/Attributes
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="name" type="String">
	    ///		属性名称
		///	</param>
		///	<param name="value" type="Function">
	    ///		返回值的函数 范围:当前元素, 参数: 当前元素的索引值
		///	</param>

		var options = name;

		// Look for the case where we're accessing a style value
		if ( name.constructor == String )
			if ( value === undefined )
				return this[0] && jQuery[ type || "attr" ]( this[0], name );

			else {
				options = {};
				options[ name ] = value;
			}

		// Check to see if we're setting style values
		return this.each(function(i){
			// Set all the styles
			for ( name in options )
				jQuery.attr(
					type ?
						this.style :
						this,
					name, jQuery.prop( this, options[ name ], type, i, name )
				);
		});
	},

	css: function( key, value ) {
		///	<summary>
	    ///		在所有匹配的元素中，设置一个样式属性的值。
	    ///		数字将自动转化为像素值
		///		Part of CSS
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="key" type="String">
	    ///		属性名
		///	</param>
		///	<param name="value" type="String">
	    ///		属性值
		///	</param>

		// ignore negative width and height values
		if ( (key == 'width' || key == 'height') && parseFloat(value) < 0 )
			value = undefined;
		return this.attr( key, value, "curCSS" );
	},

	text: function( text ) {
		///	<summary>
	    ///		设置所有匹配元素的文本内容
		///		与 html() 类似, 但将编码 HTML (将 "<" 和 ">" 替换成相应的HTML实体)。
		///		Part of DOM/Attributes
		///	</summary>
		///	<returns type="String" />
		///	<param name="text" type="String">
	    ///		用于设置元素内容的文本
		///	</param>

		if ( typeof text != "object" && text != null )
			return this.empty().append( (this[0] && this[0].ownerDocument || document).createTextNode( text ) );

		var ret = "";

		jQuery.each( text || this, function(){
			jQuery.each( this.childNodes, function(){
				if ( this.nodeType != 8 )
					ret += this.nodeType != 1 ?
						this.nodeValue :
						jQuery.fn.text( [ this ] );
			});
		});

		return ret;
	},

	wrapAll: function( html ) {
		///	<summary>
	    ///		将所有匹配的元素用单个元素包裹起来
	    ///		这于 '.wrap()' 是不同的，
	    ///		'.wrap()'为每一个匹配的元素都包裹一次。
	    ///		这种包装对于在文档中插入额外的结构化标记最有用，
	    ///		而且它不会破坏原始文档的语义品质。
	    ///		这个函数的原理是检查提供的第一个元素并在它的代码结构中找到最上层的祖先元素－－这个祖先元素就是包装元素。
		///		Part of DOM/Manipulation
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="html" type="Element">
	    ///		HTML标记代码字符串，用于动态生成元素并包装目标元素
		///	</param>

		if ( this[0] )
			// The elements to wrap the target around
			jQuery( html, this[0].ownerDocument )
				.clone()
				.insertBefore( this[0] )
				.map(function(){
					var elem = this;

					while ( elem.firstChild )
						elem = elem.firstChild;

					return elem;
				})
				.append(this);

		return this;
	},

	wrapInner: function( html ) {
		///	<summary>
	    ///		将每一个匹配的元素的子内容(包括文本节点)用一个HTML结构包裹起来。
		///	</summary>
		///	<param name="html" type="String">
    	///		HTML标记代码字符串，用于动态生成元素并包装目标元素
		///	</param>
		///	<returns type="jQuery" />

		return this.each(function(){
			jQuery( this ).contents().wrapAll( html );
		});
	},

	wrap: function( html ) {
		///	<summary>
	    ///		把所有匹配的元素用其他元素的结构化标记包裹起来。
	    ///		这种包装对于在文档中插入额外的结构化标记最有用，
	    ///		而且它不会破坏原始文档的语义品质。
	    ///		这个函数的原理是检查提供的第一个元素
	    ///		（它是由所提供的HTML标记代码动态生成的），
	    ///		并在它的代码结构中找到最上层的祖先元素－－这个祖先元素就是包裹元素。
	    ///		当HTML标记代码中的元素包含文本时无法使用这个函数。
	    ///		因此，如果要添加文本应该在包裹完成之后再行添加。
		///		Part of DOM/Manipulation
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="html" type="Element">
	    ///		HTML标记代码字符串，用于动态生成元素并包裹目标元素
		///	</param>
		
		return this.each(function(){
			jQuery( this ).wrapAll( html );
		});
	},

	append: function() {
		///	<summary>
	    ///		向每个匹配的元素内部追加内容。
	    ///		这个操作与对指定的元素执行appendChild方法，
	    ///		将它们添加到文档中的情况类似。
		///		Part of DOM/Manipulation
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="content" type="Content">
	    ///		要追加到目标中的内容
		///	</param>
		
		return this.domManip(arguments, true, false, function(elem){
			if (this.nodeType == 1)
				this.appendChild( elem );
		});
	},

	prepend: function() {
		///	<summary>
	    ///		向每个匹配的元素内部前置内容。
	    ///		这是向所有匹配元素内部的开始处插入内容的最佳方式。
		///		Part of DOM/Manipulation
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="" type="Content">
	    ///		要插入到目标元素内部前端的内容
		///	</param>

		return this.domManip(arguments, true, true, function(elem){
			if (this.nodeType == 1)
				this.insertBefore( elem, this.firstChild );
		});
	},

	before: function() {
		///	<summary>
	    ///		在每个匹配的元素之前插入内容。
		///		Part of DOM/Manipulation
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="" type="Content">
	    ///		在所有段落之前插入一些HTML标记代码。
		///	</param>

		return this.domManip(arguments, false, false, function(elem){
			this.parentNode.insertBefore( elem, this );
		});
	},

	after: function() {
		///	<summary>
	    ///		在每个匹配的元素之后插入内容。
		///		Part of DOM/Manipulation
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="" type="Content">
	    ///		插入到每个目标后的内容
		///	</param>

		return this.domManip(arguments, false, true, function(elem){
			this.parentNode.insertBefore( elem, this.nextSibling );
		});
	},

	end: function() {
		///	<summary>
	    ///		回到最近的一个"破坏性"操作之前。
	    ///		即，将匹配的元素列表变为前一次的状态。
	    ///		如果之前没有破坏性操作，则返回一个空集。
	    ///		所谓的"破坏性"就是指任何改变所匹配的jQuery元素的操作。
	    ///     这包括在 Traversing 中任何返回一个jQuery对象的函数--'add', 'andSelf', 'children', 'filter'
	    ///     , 'find', 'map', 'next', 'nextAll', 'not', 'parent', 'parents', 'prev', 'prevAll'
	    ///     , 'siblings' and 'slice'--再加上 Manipulation 中的 'clone'。
		///		Part of DOM/Traversing
		///	</summary>
		///	<returns type="jQuery" />

		return this.prevObject || jQuery( [] );
	},

	find: function( selector ) {
		///	<summary>
	    ///		搜索所有与指定表达式匹配的元素。
    	///		这个函数是找出正在处理的元素的后代元素的好方法。
    	///		所有搜索都依靠jQuery表达式来完成。
    	///		这个表达式可以使用CSS1-3的选择器，或简单的XPATH语法来写。
		///		Part of DOM/Traversing
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="selector" type="String">
	    ///		用于查找的表达式
		///	</param>
		///	<returns type="jQuery" />
	
		var elems = jQuery.map(this, function(elem){
			return jQuery.find( selector, elem );
		});

		return this.pushStack( /[^+>] [^+>]/.test( selector ) || selector.indexOf("..") > -1 ?
			jQuery.unique( elems ) :
			elems );
	},

	clone: function( events ) {
		///	<summary>
	    ///		克隆匹配的DOM元素并且选中这些克隆的副本。
	    ///		在想把DOM文档中元素的副本添加到其他位置时这个函数非常有用。
		///		Part of DOM/Manipulation
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="deep" type="Boolean" optional="true">
	    ///		(可选) 如果你不想克隆后代的所有节点，除了本身的元素，可以设置为False
		///	</param>

		// Do the clone
		var ret = this.map(function(){
			if ( jQuery.browser.msie && !jQuery.isXMLDoc(this) ) {
				// IE copies events bound via attachEvent when
				// using cloneNode. Calling detachEvent on the
				// clone will also remove the events from the orignal
				// In order to get around this, we use innerHTML.
				// Unfortunately, this means some modifications to
				// attributes in IE that are actually only stored
				// as properties will not be copied (such as the
				// the name attribute on an input).
				var clone = this.cloneNode(true),
					container = document.createElement("div");
				container.appendChild(clone);
				return jQuery.clean([container.innerHTML])[0];
			} else
				return this.cloneNode(true);
		});

		// Need to set the expando to null on the cloned set if it exists
		// removeData doesn't work here, IE removes it from the original as well
		// this is primarily for IE but the data expando shouldn't be copied over in any browser
		var clone = ret.find("*").andSelf().each(function(){
			if ( this[ expando ] != undefined )
				this[ expando ] = null;
		});

		// Copy the events from the original to the clone
		if ( events === true )
			this.find("*").andSelf().each(function(i){
				if (this.nodeType == 3)
					return;
				var events = jQuery.data( this, "events" );

				for ( var type in events )
					for ( var handler in events[ type ] )
						jQuery.event.add( clone[ i ], type, events[ type ][ handler ], events[ type ][ handler ].data );
			});

		// Return the cloned set
		return ret;
	},

	filter: function( selector ) {
		///	<summary>
	    ///		筛选出与指定函数返回值匹配的元素集合
	    ///		这个函数内部将对每个对象计算一次 (正如 '$.each').
    	///		如果调用的函数返回false则这个元素被删除，否则就会保留。
		///		})
		///		Part of DOM/Traversing
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="filter" type="Function">
	    ///		传递进filter的函数
		///	</param>
		///	<returns type="jQuery" />
	
		return this.pushStack(
			jQuery.isFunction( selector ) &&
			jQuery.grep(this, function(elem, i){
				return selector.call( elem, i );
			}) ||

			jQuery.multiFilter( selector, this ) );
	},

	not: function( selector ) {
		///	<summary>
    	///		将元素集合中所有与指定元素匹配的元素删除。
		///		这个方法被用来删除一个jQuery对象中一个或多个元素。
		///		Part of DOM/Traversing
		///	</summary>
		///	<param name="selector" type="jQuery">
	    ///		jQuery对象中一组要被删除的元素。
		///	</param>
		///	<returns type="jQuery" />
	
		if ( selector.constructor == String )
			// test special case where just one selector is passed in
			if ( isSimple.test( selector ) )
				return this.pushStack( jQuery.multiFilter( selector, this, true ) );
			else
				selector = jQuery.multiFilter( selector, this );

		var isArrayLike = selector.length && selector[selector.length - 1] !== undefined && !selector.nodeType;
		return this.filter(function() {
			return isArrayLike ? jQuery.inArray( this, selector ) < 0 : this != selector;
		});
	},

	add: function( selector ) {
		///	<summary>
	    ///		把与表达式匹配的元素添加到jQuery对象中。
	    ///     这个函数可以用于连接分别与两个表达式匹配的元素结果集。
		///		Part of DOM/Traversing
		///	</summary>
		///	<param name="elements" type="Element">
		///		一个或多个要添加的元素
		///	</param>
		///	<returns type="jQuery" />
	
		return this.pushStack( jQuery.unique( jQuery.merge(
			this.get(),
			typeof selector == 'string' ?
				jQuery( selector ) :
				jQuery.makeArray( selector )
		)));
	},

	is: function( selector ) {
		///	<summary>
	    ///		用一个表达式来检查当前选择的元素集合，
	    ///		如果其中至少有一个元素符合这个给定的表达式就返回true。
	    ///		如果没有元素符合，或者表达式无效，都返回'false'.
	    ///		'filter' 内部实际也是在调用这个函数，
	    ///		所以，filter()函数原有的规则在这里也适用。
		///		Part of DOM/Traversing
		///	</summary>
		///	<returns type="Boolean" />
		///	<param name="expr" type="String">
	    ///		 用于筛选的表达式
		///	</param>

		return !!selector && jQuery.multiFilter( selector, this ).length > 0;
	},

	hasClass: function( selector ) {
		///	<summary>
	    ///		检查当前的元素是否含有某个特定的类，如果有，则返回true。这其实就是 is("." + class)。
		///	</summary>
    	///	<param name="selector" type="String">用于匹配的类名</param>
    	///	<returns type="Boolean">如果有，则返回true，否则返回false.</returns>

		return this.is( "." + selector );
	},

	val: function( value ) {
		///	<summary>
	    ///		设置每一个匹配元素的值。在 jQuery 1.2, 这也可以为select元件赋值
		///		Part of DOM/Attributes
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="val" type="String">
    	///		 要设置的值。
		///	</param>

		if ( value == undefined ) {

			if ( this.length ) {
				var elem = this[0];

				// We need to handle select boxes special
				if ( jQuery.nodeName( elem, "select" ) ) {
					var index = elem.selectedIndex,
						values = [],
						options = elem.options,
						one = elem.type == "select-one";

					// Nothing was selected
					if ( index < 0 )
						return null;

					// Loop through all the selected options
					for ( var i = one ? index : 0, max = one ? index + 1 : options.length; i < max; i++ ) {
						var option = options[ i ];

						if ( option.selected ) {
							// Get the specifc value for the option
							value = jQuery.browser.msie && !option.attributes.value.specified ? option.text : option.value;

							// We don't need an array for one selects
							if ( one )
								return value;

							// Multi-Selects return an array
							values.push( value );
						}
					}

					return values;

				// Everything else, we just grab the value
				} else
					return (this[0].value || "").replace(/\r/g, "");

			}

			return undefined;
		}

		if( value.constructor == Number )
			value += '';

		return this.each(function(){
			if ( this.nodeType != 1 )
				return;

			if ( value.constructor == Array && /radio|checkbox/.test( this.type ) )
				this.checked = (jQuery.inArray(this.value, value) >= 0 ||
					jQuery.inArray(this.name, value) >= 0);

			else if ( jQuery.nodeName( this, "select" ) ) {
				var values = jQuery.makeArray(value);

				jQuery( "option", this ).each(function(){
					this.selected = (jQuery.inArray( this.value, values ) >= 0 ||
						jQuery.inArray( this.text, values ) >= 0);
				});

				if ( !values.length )
					this.selectedIndex = -1;

			} else
				this.value = value;
		});
	},

	html: function( value ) {
		///	<summary>
	    ///		设置每一个匹配元素的html内容。
	    ///		这个函数不能用于XML文档。但可以用于XHTML文档。
		///		Part of DOM/Attributes
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="val" type="String">
	    ///		 用于设定HTML内容的值
		///	</param>
		
		return value == undefined ?
			(this[0] ?
				this[0].innerHTML :
				null) :
			this.empty().append( value );
	},

	replaceWith: function( value ) {
		///	<summary>
    	///		将所有匹配的元素替换成指定的HTML或DOM元素。
		///	</summary>
		///	<param name="value" type="String">
	    ///		用于将匹配元素替换掉的内容
		///	</param>
		///	<returns type="jQuery">刚替换的元素</returns>

		return this.after( value ).remove();
	},

	eq: function( i ) {
		///	<summary>
	    ///		匹配一个给定索引值的元素。
	    ///		从 0 开始计数
		///		Part of Core
		///	</summary>
		///	<returns type="jQuery" />
		///	<param name="num" type="Number">
		///		你想要的那个元素的索引值
		///	</param>
	
		return this.slice( i, i + 1 );
	},

	slice: function() {
		///	<summary>
	    ///		选取一个匹配的子集。与原来的slice方法类似。
		///	</summary>
	    ///	<param name="start" type="Number" integer="true">开始选取子集的位置。（从0开始，负数是从集合的尾部开始选起）</param>
	    ///	<param name="end" optional="true" type="Number" integer="true"> (可选) 结束选取自己的位置，
	    ///		如果不指定，则就是本身的结尾。</param>
		///	<returns type="jQuery">被选择的元素</returns>
	
		return this.pushStack(Array.prototype.slice.apply(this, arguments));
	},

	map: function( callback ) {
		///	<summary>
	    ///		将一组元素转换成其他数组（不论是否是元素数组）This member is internal.
	    ///     你可以用这个函数来建立一个列表，不论是值、属性还是CSS样式，或者其他特别形式。
	    ///     这都可以用'$.map()'来方便的建立。
		///	</summary>
		///	<private />
		///	<returns type="jQuery" />
		
		return this.pushStack( jQuery.map(this, function(elem, i){
			return callback.call( elem, i, elem );
		}));
	},

	andSelf: function() {
		///	<summary>
	    ///		加入先前所选的加入当前元素中。
	    ///     对于筛选或查找后的元素，要加入先前所选元素时将会很有用。
		///	</summary>
		///	<returns type="jQuery" />
		
		return this.add( this.prevObject );
	},

	data: function( key, value ){
		///	<summary>
	    ///		在元素上存放数据，同时也返回value。
	    ///     如果jQuery集合指向多个元素，那将在所有元素上设置对应数据。
	    ///     这个函数不用建立一个新的expando，就能在一个元素上存放任何格式的数据，而不仅仅是字符串。
		///	</summary>
	///	<param name="key" type="String">存储的数据名</param>
	///	<param name="value" type="Any">将要存储的任意数据</param>
		///	<returns type="Any">值存储在指定的数据名上</returns>
		
		var parts = key.split(".");
		parts[1] = parts[1] ? "." + parts[1] : "";

		if ( value === undefined ) {
			var data = this.triggerHandler("getData" + parts[1] + "!", [parts[0]]);

			if ( data === undefined && this.length )
				data = jQuery.data( this[0], key );

			return data === undefined && parts[1] ?
				this.data( parts[0] ) :
				data;
		} else
			return this.trigger("setData" + parts[1] + "!", [parts[0], value]).each(function(){
				jQuery.data( this, key, value );
			});
	},

	removeData: function( key ){
		///	<summary>
	///		在元素上移除存放的数据
		///	</summary>
		///	<param name="key" type="Element">存储数据中要被删除的元素</param>
		
		return this.each(function(){
			jQuery.removeData( this, key );
		});
	},

		domManip: function( args, table, reverse, callback ) {
		///	<param name="args" type="Array">
		///		 Args
		///	</param>
		///	<param name="table" type="Boolean">
		///		 如果没有就在table元素中插入tbody。
		///	</param>
		///	<param name="dir" type="Number">
		///		 如果dir小于0，则以相反的程序处理参数
		///	</param>
		///	<param name="fn" type="Function">
		///		 执行DOM处理的函数
		///	</param>
		///	<returns type="jQuery" />
		///	<summary>
		///		Part of Core
		///	</summary>
		
		var clone = this.length > 1, elems;

		return this.each(function(){
			if ( !elems ) {
				elems = jQuery.clean( args, this.ownerDocument );

				if ( reverse )
					elems.reverse();
			}

			var obj = this;

			if ( table && jQuery.nodeName( this, "table" ) && jQuery.nodeName( elems[0], "tr" ) )
				obj = this.getElementsByTagName("tbody")[0] || this.appendChild( this.ownerDocument.createElement("tbody") );

			var scripts = jQuery( [] );

			jQuery.each(elems, function(){
				var elem = clone ?
					jQuery( this ).clone( true )[0] :
					this;

				// execute all scripts after the elements have been injected
				if ( jQuery.nodeName( elem, "script" ) )
					scripts = scripts.add( elem );
				else {
					// Remove any inner scripts for later evaluation
					if ( elem.nodeType == 1 )
						scripts = scripts.add( jQuery( "script", elem ).remove() );

					// Inject the elements into the document
					callback.call( obj, elem );
				}
			});

			scripts.each( evalScript );
		});
	}
};

// Give the init function the jQuery prototype for later instantiation
jQuery.fn.init.prototype = jQuery.fn;

function evalScript( i, elem ) {
	///	<summary>
	///		这是内部方法。
	///	</summary>
	/// <private />
	
	if ( elem.src )
		jQuery.ajax({
			url: elem.src,
			async: false,
			dataType: "script"
		});

	else
		jQuery.globalEval( elem.text || elem.textContent || elem.innerHTML || "" );

	if ( elem.parentNode )
		elem.parentNode.removeChild( elem );
}

function now(){
	///	<summary>
	///		获得当前日期。
	///	</summary>
	///	<returns type="Date">当前日期</returns>
	return +new Date;
}

jQuery.extend = jQuery.fn.extend = function() {
	///	<summary>
    ///		用一个或多个其他对象来扩展一个对象，返回被扩展的对象。
    ///		用于简化继承。
	///		jQuery.extend(settings, options);
	///		var settings = jQuery.extend({}, defaults, options);
	///		Part of JavaScript
	///	</summary>
	///	<param name="target" type="Object">
    ///		 待修改对象。
	///	</param>
	///	<param name="prop1" type="Object">
    ///		 待合并到第一个对象的对象。
	///	</param>
	///	<param name="propN" type="Object" optional="true" parameterArray="true">
    ///		 (可选) 待合并到第一个对象的对象。
	///	</param>
	///	<returns type="Object" />

	// copy reference to target object
	var target = arguments[0] || {}, i = 1, length = arguments.length, deep = false, options;

	// Handle a deep copy situation
	if ( target.constructor == Boolean ) {
		deep = target;
		target = arguments[1] || {};
		// skip the boolean and the target
		i = 2;
	}

	// Handle case when target is a string or something (possible in deep copy)
	if ( typeof target != "object" && typeof target != "function" )
		target = {};

	// extend jQuery itself if only one argument is passed
	if ( length == i ) {
		target = this;
		--i;
	}

	for ( ; i < length; i++ )
		// Only deal with non-null/undefined values
		if ( (options = arguments[ i ]) != null )
			// Extend the base object
			for ( var name in options ) {
				var src = target[ name ], copy = options[ name ];

				// Prevent never-ending loop
				if ( target === copy )
					continue;

				// Recurse if we're merging object values
				if ( deep && copy && typeof copy == "object" && !copy.nodeType )
					target[ name ] = jQuery.extend( deep, 
						// Never move original objects, clone them
						src || ( copy.length != null ? [ ] : { } )
					, copy );

				// Don't bring in undefined values
				else if ( copy !== undefined )
					target[ name ] = copy;

			}

	// Return the modified object
	return target;
};

var expando = "jQuery" + now(), uuid = 0, windowData = {},
	// exclude the following css properties to add px
	exclude = /z-?index|font-?weight|opacity|zoom|line-?height/i,
	// cache defaultView
	defaultView = document.defaultView || {};

jQuery.extend({
	noConflict: function( deep ) {
		///	<summary>
	    ///		扩展jQuery对象本身。
	    ///		用来在jQuery命名空间上增加新函数。
	    ///     使用这个函数必须以jQuery 开头，不能用$开头
		///		Part of Core 
		///	</summary>
		///	<returns type="undefined" />
		
		window.$ = _$;

		if ( deep )
			window.jQuery = _jQuery;

		return jQuery;
	},

	// See test/unit/core.js for details concerning this function.
	isFunction: function( fn ) {
		///	<summary>
	    ///		确定是否通过参数是一个函数。
		///	</summary>
		///	<param name="fn" type="Object">要检查的对象</param>
		///	<returns type="Boolean">参数是函数就返回true，否则返回false。</returns>
		
		return !!fn && typeof fn != "string" && !fn.nodeName &&
			fn.constructor != Array && /^[\s[]?function/.test( fn + "" );
	},

	// check if an element is in a (or is an) XML document
	isXMLDoc: function( elem ) {
		///	<summary>
	    ///		确定是否通过参数是一个XML文档。
		///	</summary>
		///	<param name="elem" type="Object">要监察的对象</param>
	    ///	<returns type="Boolean">参数是XML文档就返回true，否则返回false。</returns>
	
		return elem.documentElement && !elem.body ||
			elem.tagName && elem.ownerDocument && !elem.ownerDocument.body;
	},

	// Evalulates a script in a global context
	globalEval: function( data ) {
		///	<summary>
		///		Internally evaluates a script in a global context.
		///	</summary>
		///	<private />

		data = jQuery.trim( data );

		if ( data ) {
			// Inspired by code by Andrea Giammarchi
			// http://webreflection.blogspot.com/2007/08/global-scope-evaluation-and-dom.html
			var head = document.getElementsByTagName("head")[0] || document.documentElement,
				script = document.createElement("script");

			script.type = "text/javascript";
			if ( jQuery.browser.msie )
				script.text = data;
			else
				script.appendChild( document.createTextNode( data ) );

			// Use insertBefore instead of appendChild  to circumvent an IE6 bug.
			// This arises when a base node is used (#2709).
			head.insertBefore( script, head.firstChild );
			head.removeChild( script );
		}
	},

	nodeName: function( elem, name ) {
		///	<summary>
	    ///		检查指定的元素里是否有指定的DOM节点的名称。
		///	</summary>
		///	<param name="elem" type="Element">要检查的元素</param>
		///	<param name="name" type="String">要确认的节点名称</param>
	    ///	<returns type="Boolean">如果指定的节点名称匹配对应的节点的DOM节点名称返回true; 否则返回 false</returns>

		return elem.nodeName && elem.nodeName.toUpperCase() == name.toUpperCase();
	},

	cache: {},

	data: function( elem, name, data ) {
		///	<summary>
	    ///		在元素上存放数据，同时也返回value。
		///	</summary>
		///	<param name="elem" type="Element">要存放数据的元素</param>
	    ///	<param name="name" type="String">存储的数据名</param>
		///	<param name="data" type="Object">要存储的数据</param>
		///	<returns type="Object">数据参数已经存储</returns>

		elem = elem == window ?
			windowData :
			elem;

		var id = elem[ expando ];

		// Compute a unique ID for the element
		if ( !id )
			id = elem[ expando ] = ++uuid;

		// Only generate the data cache if we're
		// trying to access or manipulate it
		if ( name && !jQuery.cache[ id ] )
			jQuery.cache[ id ] = {};

		// Prevent overriding the named cache with undefined values
		if ( data !== undefined )
			jQuery.cache[ id ][ name ] = data;

		// Return the named cache data, or the ID for the element
		return name ?
			jQuery.cache[ id ][ name ] :
			id;
	},

	removeData: function( elem, name ) {
		///	<summary>
	    ///		在元素上移除存放的数据，与$(...).data(name, value)函数作用相反
		///	</summary>
		///	<param name="elem" type="Element">要删除数据的元素名</param>
		///	<param name="name" type="String">要删除的的数据名</param>

		elem = elem == window ?
			windowData :
			elem;

		var id = elem[ expando ];

		// If we want to remove a specific section of the element's data
		if ( name ) {
			if ( jQuery.cache[ id ] ) {
				// Remove the section of cache data
				delete jQuery.cache[ id ][ name ];

				// If we've removed all the data, remove the element's cache
				name = "";

				for ( name in jQuery.cache[ id ] )
					break;

				if ( !name )
					jQuery.removeData( elem );
			}

		// Otherwise, we want to remove all of the element's data
		} else {
			// Clean up the element expando
			try {
				delete elem[ expando ];
			} catch(e){
				// IE has trouble directly removing the expando
				// but it's ok with using removeAttribute
				if ( elem.removeAttribute )
					elem.removeAttribute( expando );
			}

			// Completely remove the data cache
			delete jQuery.cache[ id ];
		}
	},

	// args is for internal usage only
	each: function( object, callback, args ) {
		///	<summary>
	    ///		以每一个匹配的元素作为上下文来执行一个函数。
	    ///		意味着，每次执行传递进来的函数时，
	    ///		函数中的this关键字都指向一个不同的DOM元素（每次都是一个不同的匹配元素）。
	    ///		而且，在每次执行函数时，都会给函数传递一个表示作为执行环境的元素在匹配的元素集合中所处位置的数字值作为参数（从零开始的整形）。
	    ///		返回 'false' 将停止循环 (就像在普通的循环中使用 'break')。
	    ///		返回 'true' 跳至下一个循环(就像在普通的循环中使用'continue')。
		///		Part of JavaScript
		///	</summary>
		///	<param name="obj" type="Object">
		///		 要迭代的对象或数组
		///	</param>
		///	<param name="fn" type="Function">
	    ///		 对于每个匹配的元素所要执行的函数
		///	</param>
		///	<returns type="Object" />
		
		var name, i = 0, length = object.length;

		if ( args ) {
			if ( length == undefined ) {
				for ( name in object )
					if ( callback.apply( object[ name ], args ) === false )
						break;
			} else
				for ( ; i < length; )
					if ( callback.apply( object[ i++ ], args ) === false )
						break;

		// A special, fast, case for the most common use of each
		} else {
			if ( length == undefined ) {
				for ( name in object )
					if ( callback.call( object[ name ], name, object[ name ] ) === false )
						break;
			} else
				for ( var value = object[0];
					i < length && callback.call( value, i, value ) !== false; value = object[++i] ){}
		}

		return object;
	},

	prop: function( elem, value, type, i, name ) {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		// This member is not documented within the jQuery API: http://docs.jquery.com/action/edit/Internals/jQuery.prop

		// Handle executable functions
		if ( jQuery.isFunction( value ) )
			value = value.call( elem, i );

		// Handle passing in a number to a CSS property
		return value && value.constructor == Number && type == "curCSS" && !exclude.test( name ) ?
			value + "px" :
			value;
	},

	className: {
		// internal only, use addClass("class")
		add: function( elem, classNames ) {
   		///	<summary>
   		///		Internal use only; use addClass('class')
			///	</summary>
   		///	<private />

			jQuery.each((classNames || "").split(/\s+/), function(i, className){
				if ( elem.nodeType == 1 && !jQuery.className.has( elem.className, className ) )
					elem.className += (elem.className ? " " : "") + className;
			});
		},

		// internal only, use removeClass("class")
		remove: function( elem, classNames ) {
   		///	<summary>
   		///		Internal use only; use removeClass('class')
			///	</summary>
   		///	<private />

			if (elem.nodeType == 1)
				elem.className = classNames != undefined ?
					jQuery.grep(elem.className.split(/\s+/), function(className){
						return !jQuery.className.has( classNames, className );
					}).join(" ") :
					"";
		},

		// internal only, use hasClass("class")
		has: function( elem, className ) {
   		///	<summary>
   		///		Internal use only; use hasClass('class')
			///	</summary>
   		///	<private />

			return jQuery.inArray( className, (elem.className || elem).toString().split(/\s+/) ) > -1;
		}
	},

	// A method for quickly swapping in/out CSS properties to get correct calculations
	swap: function( elem, options, callback ) {
		///	<summary>
		///		Swap in/out style options.
		///	</summary>

		var old = {};
		// Remember the old values, and insert the new ones
		for ( var name in options ) {
			old[ name ] = elem.style[ name ];
			elem.style[ name ] = options[ name ];
		}

		callback.call( elem );

		// Revert the old values
		for ( var name in options )
			elem.style[ name ] = old[ name ];
	},

	css: function( elem, name, force ) {
		///	<summary>
	    ///		在所有匹配的元素中，设置或取得一个样式属性的值。数字将自动转化为像素值
		///	</summary>
		///	<private />
		// This method is undocumented in jQuery API: http://docs.jquery.com/action/edit/Internals/jQuery.css

		if ( name == "width" || name == "height" ) {
			var val, props = { position: "absolute", visibility: "hidden", display:"block" }, which = name == "width" ? [ "Left", "Right" ] : [ "Top", "Bottom" ];

			function getWH() {
				val = name == "width" ? elem.offsetWidth : elem.offsetHeight;
				var padding = 0, border = 0;
				jQuery.each( which, function() {
					padding += parseFloat(jQuery.curCSS( elem, "padding" + this, true)) || 0;
					border += parseFloat(jQuery.curCSS( elem, "border" + this + "Width", true)) || 0;
				});
				val -= Math.round(padding + border);
			}

			if ( jQuery(elem).is(":visible") )
				getWH();
			else
				jQuery.swap( elem, props, getWH );

			return Math.max(0, val);
		}

		return jQuery.curCSS( elem, name, force );
	},

	curCSS: function( elem, name, force ) {
		///	<summary>
		///		This method is internal only.
		///	</summary>
		///	<private />
		// This method is undocumented in jQuery API: http://docs.jquery.com/action/edit/Internals/jQuery.curCSS

		var ret, style = elem.style;

		// A helper method for determining if an element's values are broken
		function color( elem ) {
			if ( !jQuery.browser.safari )
				return false;

			// defaultView is cached
			var ret = defaultView.getComputedStyle( elem, null );
			return !ret || ret.getPropertyValue("color") == "";
		}

		// We need to handle opacity special in IE
		if ( name == "opacity" && jQuery.browser.msie ) {
			ret = jQuery.attr( style, "opacity" );

			return ret == "" ?
				"1" :
				ret;
		}
		// Opera sometimes will give the wrong display answer, this fixes it, see #2037
		if ( jQuery.browser.opera && name == "display" ) {
			var save = style.outline;
			style.outline = "0 solid black";
			style.outline = save;
		}

		// Make sure we're using the right name for getting the float value
		if ( name.match( /float/i ) )
			name = styleFloat;

		if ( !force && style && style[ name ] )
			ret = style[ name ];

		else if ( defaultView.getComputedStyle ) {

			// Only "float" is needed here
			if ( name.match( /float/i ) )
				name = "float";

			name = name.replace( /([A-Z])/g, "-$1" ).toLowerCase();

			var computedStyle = defaultView.getComputedStyle( elem, null );

			if ( computedStyle && !color( elem ) )
				ret = computedStyle.getPropertyValue( name );

			// If the element isn't reporting its values properly in Safari
			// then some display: none elements are involved
			else {
				var swap = [], stack = [], a = elem, i = 0;

				// Locate all of the parent display: none elements
				for ( ; a && color(a); a = a.parentNode )
					stack.unshift(a);

				// Go through and make them visible, but in reverse
				// (It would be better if we knew the exact display type that they had)
				for ( ; i < stack.length; i++ )
					if ( color( stack[ i ] ) ) {
						swap[ i ] = stack[ i ].style.display;
						stack[ i ].style.display = "block";
					}

				// Since we flip the display style, we have to handle that
				// one special, otherwise get the value
				ret = name == "display" && swap[ stack.length - 1 ] != null ?
					"none" :
					( computedStyle && computedStyle.getPropertyValue( name ) ) || "";

				// Finally, revert the display styles back
				for ( i = 0; i < swap.length; i++ )
					if ( swap[ i ] != null )
						stack[ i ].style.display = swap[ i ];
			}

			// We should always get a number back from opacity
			if ( name == "opacity" && ret == "" )
				ret = "1";

		} else if ( elem.currentStyle ) {
			var camelCase = name.replace(/\-(\w)/g, function(all, letter){
				return letter.toUpperCase();
			});

			ret = elem.currentStyle[ name ] || elem.currentStyle[ camelCase ];

			// From the awesome hack by Dean Edwards
			// http://erik.eae.net/archives/2007/07/27/18.54.15/#comment-102291

			// If we're not dealing with a regular pixel number
			// but a number that has a weird ending, we need to convert it to pixels
			if ( !/^\d+(px)?$/i.test( ret ) && /^\d/.test( ret ) ) {
				// Remember the original values
				var left = style.left, rsLeft = elem.runtimeStyle.left;

				// Put in the new values to get a computed value out
				elem.runtimeStyle.left = elem.currentStyle.left;
				style.left = ret || 0;
				ret = style.pixelLeft + "px";

				// Revert the changed values
				style.left = left;
				elem.runtimeStyle.left = rsLeft;
			}
		}

		return ret;
	},

	clean: function( elems, context ) {
		///	<summary>
		///		This method is internal only.
		///	</summary>
		///	<private />
		// This method is undocumented in the jQuery API: http://docs.jquery.com/action/edit/Internals/jQuery.clean

		var ret = [];
		context = context || document;
		// !context.createElement fails in IE with an error but returns typeof 'object'
		if (typeof context.createElement == 'undefined')
			context = context.ownerDocument || context[0] && context[0].ownerDocument || document;

		jQuery.each(elems, function(i, elem){
			if ( !elem )
				return;

			if ( elem.constructor == Number )
				elem += '';

			// Convert html string into DOM nodes
			if ( typeof elem == "string" ) {
				// Fix "XHTML"-style tags in all browsers
				elem = elem.replace(/(<(\w+)[^>]*?)\/>/g, function(all, front, tag){
					return tag.match(/^(abbr|br|col|img|input|link|meta|param|hr|area|embed)$/i) ?
						all :
						front + "></" + tag + ">";
				});

				// Trim whitespace, otherwise indexOf won't work as expected
				var tags = jQuery.trim( elem ).toLowerCase(), div = context.createElement("div");

				var wrap =
					// option or optgroup
					!tags.indexOf("<opt") &&
					[ 1, "<select multiple='multiple'>", "</select>" ] ||

					!tags.indexOf("<leg") &&
					[ 1, "<fieldset>", "</fieldset>" ] ||

					tags.match(/^<(thead|tbody|tfoot|colg|cap)/) &&
					[ 1, "<table>", "</table>" ] ||

					!tags.indexOf("<tr") &&
					[ 2, "<table><tbody>", "</tbody></table>" ] ||

				 	// <thead> matched above
					(!tags.indexOf("<td") || !tags.indexOf("<th")) &&
					[ 3, "<table><tbody><tr>", "</tr></tbody></table>" ] ||

					!tags.indexOf("<col") &&
					[ 2, "<table><tbody></tbody><colgroup>", "</colgroup></table>" ] ||

					// IE can't serialize <link> and <script> tags normally
					jQuery.browser.msie &&
					[ 1, "div<div>", "</div>" ] ||

					[ 0, "", "" ];

				// Go to html and back, then peel off extra wrappers
				div.innerHTML = wrap[1] + elem + wrap[2];

				// Move to the right depth
				while ( wrap[0]-- )
					div = div.lastChild;

				// Remove IE's autoinserted <tbody> from table fragments
				if ( jQuery.browser.msie ) {

					// String was a <table>, *may* have spurious <tbody>
					var tbody = !tags.indexOf("<table") && tags.indexOf("<tbody") < 0 ?
						div.firstChild && div.firstChild.childNodes :

						// String was a bare <thead> or <tfoot>
						wrap[1] == "<table>" && tags.indexOf("<tbody") < 0 ?
							div.childNodes :
							[];

					for ( var j = tbody.length - 1; j >= 0 ; --j )
						if ( jQuery.nodeName( tbody[ j ], "tbody" ) && !tbody[ j ].childNodes.length )
							tbody[ j ].parentNode.removeChild( tbody[ j ] );

					// IE completely kills leading whitespace when innerHTML is used
					if ( /^\s/.test( elem ) )
						div.insertBefore( context.createTextNode( elem.match(/^\s*/)[0] ), div.firstChild );

				}

				elem = jQuery.makeArray( div.childNodes );
			}

			if ( elem.length === 0 && (!jQuery.nodeName( elem, "form" ) && !jQuery.nodeName( elem, "select" )) )
				return;

			if ( elem[0] == undefined || jQuery.nodeName( elem, "form" ) || elem.options )
				ret.push( elem );

			else
				ret = jQuery.merge( ret, elem );

		});

		return ret;
	},

	attr: function( elem, name, value ) {
		///	<summary>
	    ///		取得第一个匹配元素的属性值。通过这个方法可以方便地从第一个匹配元素中获取一个属性的值。
	    ///     如果元素没有相应属性，则返回 undefined 。
		///	</summary>
		///	<private />

		// don't set attributes on text and comment nodes
		if (!elem || elem.nodeType == 3 || elem.nodeType == 8)
			return undefined;

		var notxml = !jQuery.isXMLDoc( elem ),
			// Whether we are setting (or getting)
			set = value !== undefined,
			msie = jQuery.browser.msie;

		// Try to normalize/fix the name
		name = notxml && jQuery.props[ name ] || name;

		// Only do all the following if this is a node (faster for style)
		// IE elem.getAttribute passes even for style
		if ( elem.tagName ) {

			// These attributes require special treatment
			var special = /href|src|style/.test( name );

			// Safari mis-reports the default selected property of a hidden option
			// Accessing the parent's selectedIndex property fixes it
			if ( name == "selected" && jQuery.browser.safari )
				elem.parentNode.selectedIndex;

			// If applicable, access the attribute via the DOM 0 way
			if ( name in elem && notxml && !special ) {
				if ( set ){
					// We can't allow the type property to be changed (since it causes problems in IE)
					if ( name == "type" && jQuery.nodeName( elem, "input" ) && elem.parentNode )
						throw "type property can't be changed";

					elem[ name ] = value;
				}

				// browsers index elements by id/name on forms, give priority to attributes.
				if( jQuery.nodeName( elem, "form" ) && elem.getAttributeNode(name) )
					return elem.getAttributeNode( name ).nodeValue;

				return elem[ name ];
			}

			if ( msie && notxml &&  name == "style" )
				return jQuery.attr( elem.style, "cssText", value );

			if ( set )
				// convert the value to a string (all browsers do this but IE) see #1070
				elem.setAttribute( name, "" + value );

			var attr = msie && notxml && special
					// Some attributes require a special call on IE
					? elem.getAttribute( name, 2 )
					: elem.getAttribute( name );

			// Non-existent attributes return null, we normalize to undefined
			return attr === null ? undefined : attr;
		}

		// elem is actually elem.style ... set the style

		// IE uses filters for opacity
		if ( msie && name == "opacity" ) {
			if ( set ) {
				// IE has trouble with opacity if it does not have layout
				// Force it by setting the zoom level
				elem.zoom = 1;

				// Set the alpha filter to set the opacity
				elem.filter = (elem.filter || "").replace( /alpha\([^)]*\)/, "" ) +
					(parseInt( value ) + '' == "NaN" ? "" : "alpha(opacity=" + value * 100 + ")");
			}

			return elem.filter && elem.filter.indexOf("opacity=") >= 0 ?
				(parseFloat( elem.filter.match(/opacity=([^)]*)/)[1] ) / 100) + '':
				"";
		}

		name = name.replace(/-([a-z])/ig, function(all, letter){
			return letter.toUpperCase();
		});

		if ( set )
			elem[ name ] = value;

		return elem[ name ];
	},

	trim: function( text ) {
		///	<summary>
	    ///		去掉字符串起始和结尾的空格。
		///		Part of JavaScript
		///	</summary>
		///	<returns type="String" />
		///	<param name="text" type="String">
		///		要去空格的字符串
		///	</param>

		return (text || "").replace( /^\s+|\s+$/g, "" );
	},

	makeArray: function( array ) {
		///	<summary>
	    ///		将类数组对象转换为数组对象。
	    ///     类数组对象有 length 属性，其成员索引为 0 至 length - 1。实际中此函数在 jQuery 中将自动使用而无需特意转换。
		///	</summary>
	    ///	<param name="array" type="Object">要转换为数组对象的类数组对象。</param>
		///	<returns type="Array" />
		///	<private />

		var ret = [];

		if( array != null ){
			var i = array.length;
			//the window, strings and functions also have 'length'
			if( i == null || array.split || array.setInterval || array.call )
				ret[0] = array;
			else
				while( i )
					ret[--i] = array[i];
		}

		return ret;
	},

	inArray: function( elem, array ) {
		///	<summary>
	    ///		确定第一个参数在数组中的位置(如果没有找到则返回 -1 )。
		///	</summary>
	    ///	<param name="elem">用于在数组中查找是否存在的值</param>
	    ///	<param name="array" type="Array">待处理数组。</param>
	///	<returns type="Number" integer="true">如果找到，则从0开始累计，没有找到则返回 -1</returns>

		for ( var i = 0, length = array.length; i < length; i++ )
		// Use === because on IE, window == document
			if ( array[ i ] === elem )
				return i;

		return -1;
	},

	merge: function( first, second ) {
		///	<summary>
	    ///		两个参数都是数组，排除第二个数组中与第一个相同的，再将两个数组合并
		///		Part of JavaScript
		///	</summary>
		///	<returns type="Array" />
		///	<param name="first" type="Array">
		///		 The first array to merge.
		///	</param>
		///	<param name="second" type="Array">
		///		 The second array to merge.
		///	</param>

		// We have to loop this way because IE & Opera overwrite the length
		// expando of getElementsByTagName
		var i = 0, elem, pos = first.length;
		// Also, we need to make sure that the correct elements are being returned
		// (IE returns comment nodes in a '*' query)
		if ( jQuery.browser.msie ) {
			while ( elem = second[ i++ ] )
				if ( elem.nodeType != 8 )
					first[ pos++ ] = elem;

		} else
			while ( elem = second[ i++ ] )
				first[ pos++ ] = elem;

		return first;
	},

	unique: function( array ) {
		///	<summary>
		///		删除元素数组中所有的重复元素。
		///	</summary>
		///	<param name="array" type="Array&lt;Element&gt;">要转换的数组</param>
		///	<returns type="Array&lt;Element&gt;">转换后的数组</returns>

		var ret = [], done = {};

		try {

			for ( var i = 0, length = array.length; i < length; i++ ) {
				var id = jQuery.data( array[ i ] );

				if ( !done[ id ] ) {
					done[ id ] = true;
					ret.push( array[ i ] );
				}
			}

		} catch( e ) {
			ret = array;
		}

		return ret;
	},

	grep: function( elems, callback, inv ) {
		///	<summary>
	    ///		使用过滤函数过滤数组元素。
	    ///		此函数至少传递两个参数：待过滤数组和过滤函数。
	    ///		过滤函数必须返回 true 以保留元素或 false 以删除元素。
		///		});
		///		Part of JavaScript
		///	</summary>
		///	<returns type="Array" />
		///	<param name="elems" type="Array">
	///		待过滤数组。
		///	</param>
		///	<param name="fn" type="Function">
	    ///		此函数将处理数组每个元素。第一个参数为当前元素，第二个参数而元素索引值。
	    ///     此函数应返回一个布尔值。另外，此函数可设置为一个字符串，当设置为字符串时，将视为“lambda-form”（缩写形式？），
	    ///     其中 a 代表数组元素，i 代表元素索引值。如“a > 0”代表“function(a){ return a > 0; }”。
		///	</param>
		///	<param name="inv" type="Boolean">
	    ///	    (可选) 如果 "invert" 为 false 或为设置，则函数返回数组中由过滤函数返回 true 的元素，
	    ///     当"invert" 为 true，则返回过滤函数中返回 false 的元素集。
		///	</param>
		
		var ret = [];

		// Go through the array, only saving the items
		// that pass the validator function
		for ( var i = 0, length = elems.length; i < length; i++ )
			if ( !inv != !callback( elems[ i ], i ) )
				ret.push( elems[ i ] );

		return ret;
	},

	map: function( elems, callback ) {
		///	<summary>
	    ///		将一个数组中的元素转换到另一个数组中。
	    ///		作为参数的转换函数会为每个数组元素调用，
	    ///		而且会给这个转换函数传递一个表示被转换的元素作为参数。
	    ///		转换函数可以返回转换后的值、null（删除数组中的项目）
	    ///		或一个包含值的数组，并扩展至原始数组中。
		///		Part of JavaScript
		///	</summary>
		///	<returns type="Array" />
		///	<param name="elems" type="Array">
	    ///		待转换数组。
		///	</param>
		///	<param name="fn" type="Function">
	    ///		为每个数组元素调用，而且会给这个转换函数传递一个表示被转换的元素作为参数。函数可返回任何值。
	    ///     另外，此函数可设置为一个字符串，当设置为字符串时，将视为“lambda-form”（缩写形式？）
	    ///     ，其中 a 代表数组元素。如“a * a”代表“function(a){ return a * a; }”。
		///	</param>
		
		var ret = [];

		// Go through the array, translating each of the items to their
		// new value (or values).
		for ( var i = 0, length = elems.length; i < length; i++ ) {
			var value = callback( elems[ i ], i );

			if ( value != null )
				ret[ ret.length ] = value;
		}

		return ret.concat.apply( [], ret );
	}
});

var userAgent = navigator.userAgent.toLowerCase();

// Figure out what browser is being used
jQuery.browser = {
	version: (userAgent.match( /.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/ ) || [])[1],
	safari: /webkit/.test( userAgent ),
	opera: /opera/.test( userAgent ),
	msie: /msie/.test( userAgent ) && !/opera/.test( userAgent ),
	mozilla: /mozilla/.test( userAgent ) && !/(compatible|webkit)/.test( userAgent )
};

var styleFloat = jQuery.browser.msie ?
	"styleFloat" :
	"cssFloat";

jQuery.extend({
	// Check to see if the W3C box model is being used
	boxModel: !jQuery.browser.msie || document.compatMode == "CSS1Compat",

	props: {
		"for": "htmlFor",
		"class": "className",
		"float": styleFloat,
		cssFloat: styleFloat,
		styleFloat: styleFloat,
		readonly: "readOnly",
		maxlength: "maxLength",
		cellspacing: "cellSpacing"
	}
});

jQuery.each({
	parent: function(elem){return elem.parentNode;}
}, function(name, fn){
	jQuery.fn[ name ] = function( selector ) {
		///	<summary>
	    ///		取得一个包含着所有匹配元素的唯一父元素的元素集合。
	    ///		你可以使用可选的表达式来筛选。
		///		Part of DOM/Traversing
		///	</summary>
		///	<param name="expr" type="String" optional="true">
    	///		 (可选)用来筛选的表达式
		///	</param>
		///	<returns type="jQuery" />   
		
		var ret = jQuery.map( this, fn );

		if ( selector && typeof selector == "string" )
			ret = jQuery.multiFilter( selector, ret );

		return this.pushStack( jQuery.unique( ret ) );
	};
});

jQuery.each({
	parents: function(elem){return jQuery.dir(elem,"parentNode");}
}, function(name, fn){
	jQuery.fn[ name ] = function( selector ) {
		///	<summary>
		///		取得一组包含唯一祖先元素的比配元素
		///		（除了根元素）
	    ///		你可以使用可选的表达式来筛选。
		///		Part of DOM/Traversing
		///	</summary>
		///	<param name="expr" type="String" optional="true">
	    ///		(可选) 用来筛选元素的表达式
		///	</param>
		///	<returns type="jQuery" />   
		
		var ret = jQuery.map( this, fn );

		if ( selector && typeof selector == "string" )
			ret = jQuery.multiFilter( selector, ret );

		return this.pushStack( jQuery.unique( ret ) );
	};
});

jQuery.each({
	next: function(elem){return jQuery.nth(elem,2,"nextSibling");}
}, function(name, fn){
	jQuery.fn[ name ] = function( selector ) {
		///	<summary>
	    ///		取得一组包含唯一后一个兄弟元素的比配元素
		///		它只能返回下一个子元素，而不是所有的子元素
    	///		你可以使用可选的表达式来筛选。
		///		Part of DOM/Traversing
		///	</summary>
		///	<param name="expr" type="String" optional="true">
	    ///		(可选) 用来筛选兄弟元素的表达式
		///	</param>
		///	<returns type="jQuery" />
		
		var ret = jQuery.map( this, fn );

		if ( selector && typeof selector == "string" )
			ret = jQuery.multiFilter( selector, ret );

		return this.pushStack( jQuery.unique( ret ) );
	};
});

jQuery.each({
	prev: function(elem){return jQuery.nth(elem,2,"previousSibling");}
}, function(name, fn){
	jQuery.fn[ name ] = function( selector ) {
		///	<summary>
	    ///		取得一组包含唯一前一个兄弟元素的比配元素
	    ///		它只能返回前一个子元素，而不是所有的子元素
	    ///		你可以使用可选的表达式来筛选。
		///		Part of DOM/Traversing
		///	</summary>
		///	<param name="expr" type="String" optional="true">
	    ///		(可选) 用来筛选兄弟元素的表达式
		///	</param>
		///	<returns type="jQuery" />
		
		var ret = jQuery.map( this, fn );

		if ( selector && typeof selector == "string" )
			ret = jQuery.multiFilter( selector, ret );

		return this.pushStack( jQuery.unique( ret ) );
	};
});

jQuery.each({
	nextAll: function(elem){return jQuery.dir(elem,"nextSibling");}
}, function(name, fn){
	jQuery.fn[name] = function(selector) {
		///	<summary>
		///		找出当前元素后的所有元素
    	///		你可以使用可选的表达式来筛选。
		///		Part of DOM/Traversing
		///	</summary>
		///	<param name="expr" type="String" optional="true">
	    ///		(可选) 用来筛选元素的表达式
		///	</param>
		///	<returns type="jQuery" />
		
		var ret = jQuery.map( this, fn );

		if ( selector && typeof selector == "string" )
			ret = jQuery.multiFilter( selector, ret );

		return this.pushStack( jQuery.unique( ret ) );
	};
});

jQuery.each({
	prevAll: function(elem){return jQuery.dir(elem,"previousSibling");}
}, function(name, fn){
	jQuery.fn[ name ] = function( selector ) {
		///	<summary>
		///		找出当前元素前面的所有兄弟元素
    	///		你可以使用可选的表达式来筛选。
		///		Part of DOM/Traversing
		///	</summary>
		///	<param name="expr" type="String" optional="true">
	    ///		(可选) 用来筛选兄弟元素的表达式
		///	</param>
		///	<returns type="jQuery" />
	
		var ret = jQuery.map( this, fn );

		if ( selector && typeof selector == "string" )
			ret = jQuery.multiFilter( selector, ret );

		return this.pushStack( jQuery.unique( ret ) );
	};
});

jQuery.each({
	siblings: function(elem){return jQuery.sibling(elem.parentNode.firstChild,elem);}
}, function(name, fn){
	jQuery.fn[ name ] = function( selector ) {
		///	<summary>
	    ///		迭代并取得一组包含唯一所有兄弟元素的比配元素
    	///		你可以使用可选的表达式来筛选。
		///		Part of DOM/Traversing
		///	</summary>
		///	<param name="expr" type="String" optional="true">
	    ///		(可选) 用来筛选兄弟元素的表达式
		///	</param>
		///	<returns type="jQuery" />
		
		var ret = jQuery.map( this, fn );

		if ( selector && typeof selector == "string" )
			ret = jQuery.multiFilter( selector, ret );

		return this.pushStack( jQuery.unique( ret ) );
	};
});

jQuery.each({
	children: function(elem){return jQuery.sibling(elem.firstChild);}
}, function(name, fn){
	jQuery.fn[ name ] = function( selector ) {
		///	<summary>
	    ///		迭代并取得一组包含唯一所有子元素的比配元素
    	///		你可以使用可选的表达式来筛选。
		///		Part of DOM/Traversing
		///	</summary>
		///	<param name="expr" type="String" optional="true">
    	///		(可选) 用来筛选子元素的表达式
		///	</param>
		///	<returns type="jQuery" />
		
		var ret = jQuery.map( this, fn );

		if ( selector && typeof selector == "string" )
			ret = jQuery.multiFilter( selector, ret );

		return this.pushStack( jQuery.unique( ret ) );
	};
});

jQuery.each({
	contents: function(elem){return jQuery.nodeName(elem,"iframe")?elem.contentDocument||elem.contentWindow.document:jQuery.makeArray(elem.childNodes);}
}, function(name, fn){
	jQuery.fn[ name ] = function( selector ) {
		///	<summary>找出下一结点（如果元素是一个iframe，就是文档内容）中所有匹配元素的子节点</summary>
		///	<returns type="jQuery" />
		
		var ret = jQuery.map( this, fn );

		if ( selector && typeof selector == "string" )
			ret = jQuery.multiFilter( selector, ret );

		return this.pushStack( jQuery.unique( ret ) );
	};
});

jQuery.each({
	appendTo: "append"
}, function(name, original){
	jQuery.fn[ name ] = function() {
		///	<summary>
	    ///		把所有匹配的元素追加到另一个、指定的元素元素集合中。
	    ///		实际上，使用这个方法是颠倒了常规的$(A).append(B)的操作，
	    ///		即不是把B追加到A中，而是把A追加到B中。
		///		Part of DOM/Manipulation
		///	</summary>
		///	<param name="content" type="Content">
	    ///		 用于被追加的内容
		///	</param>
		///	<returns type="jQuery" />
	
		var args = arguments;

		return this.each(function(){
			for ( var i = 0, length = args.length; i < length; i++ )
				jQuery( args[ i ] )[ original ]( this );
		});
	};
});

jQuery.each({
	prependTo: "prepend"
}, function(name, original){
	jQuery.fn[ name ] = function() {
		///	<summary>
	    ///		把所有匹配的元素前置到另一个、指定的元素元素集合中。
	    ///		实际上，使用这个方法是颠倒了常规的$(A).prepend(B)的操作，
	    ///		即不是把B前置到A中，而是把A前置到B中。
		///		Part of DOM/Manipulation
		///	</summary>
		///	<param name="content" type="Content">
	    ///		用于匹配元素的内容
		///	</param> 
		///	<returns type="jQuery" />

		var args = arguments;

		return this.each(function(){
			for ( var i = 0, length = args.length; i < length; i++ )
				jQuery( args[ i ] )[ original ]( this );
		});
	};
});

jQuery.each({
	insertBefore: "before"
}, function(name, original){
	jQuery.fn[ name ] = function() {
		///	<summary>
	    ///		把所有匹配的元素插入到另一个、指定的元素元素集合的前面。
	    ///		实际上，使用这个方法是颠倒了常规的$(A).before(B)的操作，
	    ///		即不是把B插入到A前面，而是把A插入到B前面。
		///		Part of DOM/Manipulation
		///	</summary>
		///	<param name="content" type="Content">
	    ///		 用于匹配元素的内容
		///	</param>
		///	<returns type="jQuery" />

		var args = arguments;

		return this.each(function(){
			for ( var i = 0, length = args.length; i < length; i++ )
				jQuery( args[ i ] )[ original ]( this );
		});
	};
});

jQuery.each({
	insertAfter: "after"
}, function(name, original){
	jQuery.fn[ name ] = function() {
		///	<summary>
	    ///		把所有匹配的元素插入到另一个、指定的元素元素集合的后面。
	    ///		实际上，使用这个方法是颠倒了常规的$(A).after(B)的操作，
	    ///		即不是把B插入到A后面，而是把A插入到B后面。
		///		Part of DOM/Manipulation
		///	</summary>
		///	<param name="content" type="Content">
	    ///		 用于匹配元素的内容
		///	</param>
		///	<returns type="jQuery" />
		var args = arguments;

		return this.each(function(){
			for ( var i = 0, length = args.length; i < length; i++ )
				jQuery( args[ i ] )[ original ]( this );
		});
	};
});

jQuery.each({
	replaceAll: "replaceWith"
}, function(name, original){
	jQuery.fn[ name ] = function() {
	    ///	<summary>用匹配的元素替换掉所有 （selector选择器）匹配到的元素。</summary>
	    ///	<param name="selector" type="String">（selector选择器）用于查找所要被替换的元素</param>
		///	<returns type="jQuery" />
		var args = arguments;

		return this.each(function(){
			for ( var i = 0, length = args.length; i < length; i++ )
				jQuery( args[ i ] )[ original ]( this );
		});
	};
});


jQuery.each({
	removeAttr: function( name ) {
		jQuery.attr( this, name, "" );
		if (this.nodeType == 1)
			this.removeAttribute( name );
	}
}, function(name, fn){
	jQuery.fn[ name ] = function(){
		///	<summary>
	    ///		从每一个匹配的元素中删除一个属性
		///		Part of DOM/Attributes
		///	</summary>
		///	<param name="key" type="String">
	    ///		要删除的属性名
		///	</param>
		///	<returns type="jQuery" />

		return this.each(fn, arguments);
	};
});

jQuery.each({
	addClass: function(classNames) {
		///	<summary>
	    ///		为每个匹配的元素添加指定的类名。
		///		Part of DOM/Attributes
		///	</summary>
		///	<param name="classNames" type="String">
	    ///		一个或多个要添加到元素中的CSS类名，请用空格分开
		///	</param>
		///	<returns type="jQuery" />

		jQuery.className.add(this, classNames);
	}
}, function(name, fn) {
	jQuery.fn[name] = function() {
		///	<summary>
	    ///		为每个匹配的元素添加指定的类名。
		///		Part of DOM/Attributes
		///	</summary>
		///	<param name="classNames" type="String">
    	///		一个或多个要添加到元素中的CSS类名，请用空格分开
		///	</param>
		///	<returns type="jQuery" />
		return this.each(fn, arguments);
	};
});

jQuery.each({
	removeClass: function( classNames ) {
		jQuery.className.remove( this, classNames );
	}
}, function(name, fn){
	jQuery.fn[ name ] = function(){
		///	<summary>
	    ///		从所有匹配的元素中删除全部或者指定的类。
		///		Part of DOM/Attributes
		///	</summary>
		///	<param name="cssClasses" type="String" optional="true">
    	///		(可选) 一个或多个要删除的CSS类名，请用空格分开
		///	</param>
		///	<returns type="jQuery" />
	
		return this.each(fn, arguments);
	};
});

jQuery.each({
	toggleClass: function( classNames ) {
		jQuery.className[ jQuery.className.has( this, classNames ) ? "remove" : "add" ]( this, classNames );
	}
}, function(name, fn){
	jQuery.fn[ name ] = function(){
		///	<summary>
	    ///		如果存在（不存在）就删除（添加）一个类。
		///		Part of DOM/Attributes
		///	</summary>
		///	<param name="cssClass" type="String">
	    ///		CSS类名
		///	</param>
		///	<returns type="jQuery" />
	
		return this.each(fn, arguments);
	};
});

jQuery.each({
	remove: function( selector ) {
		if ( !selector || jQuery.filter( selector, [ this ] ).r.length ) {
			// Prevent memory leaks
			jQuery( "*", this ).add(this).each(function(){
				jQuery.event.remove(this);
				jQuery.removeData(this);
			});
			if (this.parentNode)
				this.parentNode.removeChild( this );
		}
	}
}, function(name, fn){
	jQuery.fn[ name ] = function(){
		///	<summary>
	    ///		从DOM中删除所有匹配的元素。
	    ///		这个方法不会把匹配的元素从jQuery对象中删除，
	    ///		因而可以在将来再使用这些匹配的元素。
		///		Part of DOM/Manipulation
		///	</summary>
		///	<param name="expr" type="String" optional="true">
	    ///		 (可选) 用于筛选元素的jQuery表达式
		///	</param>
		///	<returns type="jQuery" />
	
		return this.each(fn, arguments);
	};
});

jQuery.each({
	empty: function() {
		// Remove element nodes and prevent memory leaks
		jQuery( ">*", this ).remove();

		// Remove any remaining nodes
		while ( this.firstChild )
			this.removeChild( this.firstChild );
	}
}, function(name, fn){
	jQuery.fn[ name ] = function(){
		///	<summary>
	    ///		删除匹配的元素集合中所有的子节点。
		///		Part of DOM/Manipulation
		///	</summary>
		///	<returns type="jQuery" />
		
		return this.each( fn, arguments );
	};
});

jQuery.each([ "Height" ], function(i, name){
	var type = name.toLowerCase();

	jQuery.fn[ type ] = function( size ) {
		///	<summary>
	    ///		为每个匹配的元素设置CSS高度(hidth)属性的值。
	    ///		如果没有明确指定单位（如：em或%），使用px。
		///		如果没有指定参数，就会得到第一个匹配元素的当前计算高度。
		///		Part of CSS
		///	</summary>
		///	<returns type="jQuery" type="jQuery" />
		///	<param name="cssProperty" type="String">
	    ///		设定CSS中 'height' 的值
		///	</param>

		// Get window width or height
		return this[0] == window ?
			// Opera reports document.body.client[Width/Height] properly in both quirks and standards
			jQuery.browser.opera && document.body[ "client" + name ] ||

			// Safari reports inner[Width/Height] just fine (Mozilla and Opera include scroll bar widths)
			jQuery.browser.safari && window[ "inner" + name ] ||

			// Everyone else use document.documentElement or document.body depending on Quirks vs Standards mode
			document.compatMode == "CSS1Compat" && document.documentElement[ "client" + name ] || document.body[ "client" + name ] :

			// Get document width or height
			this[0] == document ?
				// Either scroll[Width/Height] or offset[Width/Height], whichever is greater
				Math.max(
					Math.max(document.body["scroll" + name], document.documentElement["scroll" + name]),
					Math.max(document.body["offset" + name], document.documentElement["offset" + name])
				) :

				// Get or set width or height on the element
				size == undefined ?
					// Get width or height on the element
					(this.length ? jQuery.css( this[0], type ) : null) :

					// Set the width or height on the element (default to pixels if value is unitless)
					this.css( type, size.constructor == String ? size : size + "px" );
	};
});

jQuery.each([ "Width" ], function(i, name){
	var type = name.toLowerCase();

	jQuery.fn[ type ] = function( size ) {
		///	<summary>
	    ///		为每个匹配的元素设置CSS宽度(width)属性的值。
	    ///		如果没有明确指定单位（如：em或%），使用px。
	    ///		如果没有指定参数，就会得到第一个匹配元素的当前计算宽度。
		///		Part of CSS
		///	</summary>
		///	<returns type="jQuery" type="jQuery" />
		///	<param name="cssProperty" type="String">
	    ///		设定 CSS 'width' 的属性值
		///	</param>

		// Get window width or height
		return this[0] == window ?
			// Opera reports document.body.client[Width/Height] properly in both quirks and standards
			jQuery.browser.opera && document.body[ "client" + name ] ||

			// Safari reports inner[Width/Height] just fine (Mozilla and Opera include scroll bar widths)
			jQuery.browser.safari && window[ "inner" + name ] ||

			// Everyone else use document.documentElement or document.body depending on Quirks vs Standards mode
			document.compatMode == "CSS1Compat" && document.documentElement[ "client" + name ] || document.body[ "client" + name ] :

			// Get document width or height
			this[0] == document ?
				// Either scroll[Width/Height] or offset[Width/Height], whichever is greater
				Math.max(
					Math.max(document.body["scroll" + name], document.documentElement["scroll" + name]),
					Math.max(document.body["offset" + name], document.documentElement["offset" + name])
				) :

				// Get or set width or height on the element
				size == undefined ?
					// Get width or height on the element
					(this.length ? jQuery.css( this[0], type ) : null) :

					// Set the width or height on the element (default to pixels if value is unitless)
					this.css( type, size.constructor == String ? size : size + "px" );
	};
});

// Helper function used by the dimensions and offset modules
function num(elem, prop) {
	return elem[0] && parseInt( jQuery.curCSS(elem[0], prop, true), 10 ) || 0;
}var chars = jQuery.browser.safari && parseInt(jQuery.browser.version) < 417 ?
		"(?:[\\w*_-]|\\\\.)" :
		"(?:[\\w\u0128-\uFFFF*_-]|\\\\.)",
	quickChild = new RegExp("^>\\s*(" + chars + "+)"),
	quickID = new RegExp("^(" + chars + "+)(#)(" + chars + "+)"),
	quickClass = new RegExp("^([#.]?)(" + chars + "*)");

jQuery.extend({
	expr: {
		"": function(a,i,m){return m[2]=="*"||jQuery.nodeName(a,m[2]);},
		"#": function(a,i,m){return a.getAttribute("id")==m[2];},
		":": {
			// Position Checks
			lt: function(a,i,m){return i<m[3]-0;},
			gt: function(a,i,m){return i>m[3]-0;},
			nth: function(a,i,m){return m[3]-0==i;},
			eq: function(a,i,m){return m[3]-0==i;},
			first: function(a,i){return i==0;},
			last: function(a,i,m,r){return i==r.length-1;},
			even: function(a,i){return i%2==0;},
			odd: function(a,i){return i%2;},

			// Child Checks
			"first-child": function(a){return a.parentNode.getElementsByTagName("*")[0]==a;},
			"last-child": function(a){return jQuery.nth(a.parentNode.lastChild,1,"previousSibling")==a;},
			"only-child": function(a){return !jQuery.nth(a.parentNode.lastChild,2,"previousSibling");},

			// Parent Checks
			parent: function(a){return a.firstChild;},
			empty: function(a){return !a.firstChild;},

			// Text Check
			contains: function(a,i,m){return (a.textContent||a.innerText||jQuery(a).text()||"").indexOf(m[3])>=0;},

			// Visibility
			visible: function(a){return "hidden"!=a.type&&jQuery.css(a,"display")!="none"&&jQuery.css(a,"visibility")!="hidden";},
			hidden: function(a){return "hidden"==a.type||jQuery.css(a,"display")=="none"||jQuery.css(a,"visibility")=="hidden";},

			// Form attributes
			enabled: function(a){return !a.disabled;},
			disabled: function(a){return a.disabled;},
			checked: function(a){return a.checked;},
			selected: function(a){return a.selected||jQuery.attr(a,"selected");},

			// Form elements
			text: function(a){return "text"==a.type;},
			radio: function(a){return "radio"==a.type;},
			checkbox: function(a){return "checkbox"==a.type;},
			file: function(a){return "file"==a.type;},
			password: function(a){return "password"==a.type;},
			submit: function(a){return "submit"==a.type;},
			image: function(a){return "image"==a.type;},
			reset: function(a){return "reset"==a.type;},
			button: function(a){return "button"==a.type||jQuery.nodeName(a,"button");},
			input: function(a){return /input|select|textarea|button/i.test(a.nodeName);},

			// :has()
			has: function(a,i,m){return jQuery.find(m[3],a).length;},

			// :header
			header: function(a){return /h\d/i.test(a.nodeName);},

			// :animated
			animated: function(a){return jQuery.grep(jQuery.timers,function(fn){return a==fn.elem;}).length;}
		}
	},

	// The regular expressions that power the parsing engine
	parse: [
		// Match: [@value='test'], [@foo]
		/^(\[) *@?([\w-]+) *([!*$^~=]*) *('?"?)(.*?)\4 *\]/,

		// Match: :contains('foo')
		/^(:)([\w-]+)\("?'?(.*?(\(.*?\))?[^(]*?)"?'?\)/,

		// Match: :even, :last-child, #id, .class
		new RegExp("^([:.#]*)(" + chars + "+)")
	],

	multiFilter: function(expr, elems, not) {
		///	<summary>
		///		This member is internal only.
		///	</summary>
		///	<private />
	
		// This member is not documented in the jQuery API: http://docs.jquery.com/action/edit/Internals/jQuery.multiFilter
		var old, cur = [];

		while ( expr && expr != old ) {
			old = expr;
			var f = jQuery.filter( expr, elems, not );
			expr = f.t.replace(/^\s*,\s*/, "" );
			cur = not ? elems = f.r : jQuery.merge( cur, f.r );
		}

		return cur;
	},

	find: function( t, context ) {
		///	<summary>
		///		This member is internal only.
		///	</summary>
		///	<private />
		// This member is not documented in the jQuery API: http://docs.jquery.com/action/edit/Internals/jQuery.find

		// Quickly handle non-string expressions
		if ( typeof t != "string" )
			return [ t ];

		// check to make sure context is a DOM element or a document
		if ( context && context.nodeType != 1 && context.nodeType != 9)
			return [ ];

		// Set the correct context (if none is provided)
		context = context || document;

		// Initialize the search
		var ret = [context], done = [], last, nodeName;

		// Continue while a selector expression exists, and while
		// we're no longer looping upon ourselves
		while ( t && last != t ) {
			var r = [];
			last = t;

			t = jQuery.trim(t);

			var foundToken = false,

			// An attempt at speeding up child selectors that
			// point to a specific element tag
				re = quickChild,

				m = re.exec(t);

			if ( m ) {
				nodeName = m[1].toUpperCase();

				// Perform our own iteration and filter
				for ( var i = 0; ret[i]; i++ )
					for ( var c = ret[i].firstChild; c; c = c.nextSibling )
						if ( c.nodeType == 1 && (nodeName == "*" || c.nodeName.toUpperCase() == nodeName) )
							r.push( c );

				ret = r;
				t = t.replace( re, "" );
				if ( t.indexOf(" ") == 0 ) continue;
				foundToken = true;
			} else {
				re = /^([>+~])\s*(\w*)/i;

				if ( (m = re.exec(t)) != null ) {
					r = [];

					var merge = {};
					nodeName = m[2].toUpperCase();
					m = m[1];

					for ( var j = 0, rl = ret.length; j < rl; j++ ) {
						var n = m == "~" || m == "+" ? ret[j].nextSibling : ret[j].firstChild;
						for ( ; n; n = n.nextSibling )
							if ( n.nodeType == 1 ) {
								var id = jQuery.data(n);

								if ( m == "~" && merge[id] ) break;

								if (!nodeName || n.nodeName.toUpperCase() == nodeName ) {
									if ( m == "~" ) merge[id] = true;
									r.push( n );
								}

								if ( m == "+" ) break;
							}
					}

					ret = r;

					// And remove the token
					t = jQuery.trim( t.replace( re, "" ) );
					foundToken = true;
				}
			}

			// See if there's still an expression, and that we haven't already
			// matched a token
			if ( t && !foundToken ) {
				// Handle multiple expressions
				if ( !t.indexOf(",") ) {
					// Clean the result set
					if ( context == ret[0] ) ret.shift();

					// Merge the result sets
					done = jQuery.merge( done, ret );

					// Reset the context
					r = ret = [context];

					// Touch up the selector string
					t = " " + t.substr(1,t.length);

				} else {
					// Optimize for the case nodeName#idName
					var re2 = quickID;
					var m = re2.exec(t);

					// Re-organize the results, so that they're consistent
					if ( m ) {
						m = [ 0, m[2], m[3], m[1] ];

					} else {
						// Otherwise, do a traditional filter check for
						// ID, class, and element selectors
						re2 = quickClass;
						m = re2.exec(t);
					}

					m[2] = m[2].replace(/\\/g, "");

					var elem = ret[ret.length-1];

					// Try to do a global search by ID, where we can
					if ( m[1] == "#" && elem && elem.getElementById && !jQuery.isXMLDoc(elem) ) {
						// Optimization for HTML document case
						var oid = elem.getElementById(m[2]);

						// Do a quick check for the existence of the actual ID attribute
						// to avoid selecting by the name attribute in IE
						// also check to insure id is a string to avoid selecting an element with the name of 'id' inside a form
						if ( (jQuery.browser.msie||jQuery.browser.opera) && oid && typeof oid.id == "string" && oid.id != m[2] )
							oid = jQuery('[@id="'+m[2]+'"]', elem)[0];

						// Do a quick check for node name (where applicable) so
						// that div#foo searches will be really fast
						ret = r = oid && (!m[3] || jQuery.nodeName(oid, m[3])) ? [oid] : [];
					} else {
						// We need to find all descendant elements
						for ( var i = 0; ret[i]; i++ ) {
							// Grab the tag name being searched for
							var tag = m[1] == "#" && m[3] ? m[3] : m[1] != "" || m[0] == "" ? "*" : m[2];

							// Handle IE7 being really dumb about <object>s
							if ( tag == "*" && ret[i].nodeName.toLowerCase() == "object" )
								tag = "param";

							r = jQuery.merge( r, ret[i].getElementsByTagName( tag ));
						}

						// It's faster to filter by class and be done with it
						if ( m[1] == "." )
							r = jQuery.classFilter( r, m[2] );

						// Same with ID filtering
						if ( m[1] == "#" ) {
							var tmp = [];

							// Try to find the element with the ID
							for ( var i = 0; r[i]; i++ )
								if ( r[i].getAttribute("id") == m[2] ) {
									tmp = [ r[i] ];
									break;
								}

							r = tmp;
						}

						ret = r;
					}

					t = t.replace( re2, "" );
				}

			}

			// If a selector string still exists
			if ( t ) {
				// Attempt to filter it
				var val = jQuery.filter(t,r);
				ret = r = val.r;
				t = jQuery.trim(val.t);
			}
		}

		// An error occurred with the selector;
		// just return an empty set instead
		if ( t )
			ret = [];

		// Remove the root context
		if ( ret && context == ret[0] )
			ret.shift();

		// And combine the results
		done = jQuery.merge( done, ret );

		return done;
	},

	classFilter: function(r,m,not){
		///	<summary>
		///		This member is internal only.
		///	</summary>
		///	<private />
		// This member is not documented in the jQuery API: http://docs.jquery.com/Special:Search?ns0=1&search=classFilter

		m = " " + m + " ";
		var tmp = [];
		for ( var i = 0; r[i]; i++ ) {
			var pass = (" " + r[i].className + " ").indexOf( m ) >= 0;
			if ( !not && pass || not && !pass )
				tmp.push( r[i] );
		}
		return tmp;
	},

	filter: function(t,r,not) {
		///	<summary>
		///		This member is internal only.
		///	</summary>
		///	<private />
		// This member is not documented in the jQuery API: http://docs.jquery.com/action/edit/Internals/jQuery.filter
		var last;

		// Look for common filter expressions
		while ( t && t != last ) {
			last = t;

			var p = jQuery.parse, m;

			for ( var i = 0; p[i]; i++ ) {
				m = p[i].exec( t );

				if ( m ) {
					// Remove what we just matched
					t = t.substring( m[0].length );

					m[2] = m[2].replace(/\\/g, "");
					break;
				}
			}

			if ( !m )
				break;

			// :not() is a special case that can be optimized by
			// keeping it out of the expression list
			if ( m[1] == ":" && m[2] == "not" )
				// optimize if only one selector found (most common case)
				r = isSimple.test( m[3] ) ?
					jQuery.filter(m[3], r, true).r :
					jQuery( r ).not( m[3] );

			// We can get a big speed boost by filtering by class here
			else if ( m[1] == "." )
				r = jQuery.classFilter(r, m[2], not);

			else if ( m[1] == "[" ) {
				var tmp = [], type = m[3];

				for ( var i = 0, rl = r.length; i < rl; i++ ) {
					var a = r[i], z = a[ jQuery.props[m[2]] || m[2] ];

					if ( z == null || /href|src|selected/.test(m[2]) )
						z = jQuery.attr(a,m[2]) || '';

					if ( (type == "" && !!z ||
						 type == "=" && z == m[5] ||
						 type == "!=" && z != m[5] ||
						 type == "^=" && z && !z.indexOf(m[5]) ||
						 type == "$=" && z.substr(z.length - m[5].length) == m[5] ||
						 (type == "*=" || type == "~=") && z.indexOf(m[5]) >= 0) ^ not )
							tmp.push( a );
				}

				r = tmp;

			// We can get a speed boost by handling nth-child here
			} else if ( m[1] == ":" && m[2] == "nth-child" ) {
				var merge = {}, tmp = [],
					// parse equations like 'even', 'odd', '5', '2n', '3n+2', '4n-1', '-n+6'
					test = /(-?)(\d*)n((?:\+|-)?\d*)/.exec(
						m[3] == "even" && "2n" || m[3] == "odd" && "2n+1" ||
						!/\D/.test(m[3]) && "0n+" + m[3] || m[3]),
					// calculate the numbers (first)n+(last) including if they are negative
					first = (test[1] + (test[2] || 1)) - 0, last = test[3] - 0;

				// loop through all the elements left in the jQuery object
				for ( var i = 0, rl = r.length; i < rl; i++ ) {
					var node = r[i], parentNode = node.parentNode, id = jQuery.data(parentNode);

					if ( !merge[id] ) {
						var c = 1;

						for ( var n = parentNode.firstChild; n; n = n.nextSibling )
							if ( n.nodeType == 1 )
								n.nodeIndex = c++;

						merge[id] = true;
					}

					var add = false;

					if ( first == 0 ) {
						if ( node.nodeIndex == last )
							add = true;
					} else if ( (node.nodeIndex - last) % first == 0 && (node.nodeIndex - last) / first >= 0 )
						add = true;

					if ( add ^ not )
						tmp.push( node );
				}

				r = tmp;

			// Otherwise, find the expression to execute
			} else {
				var fn = jQuery.expr[ m[1] ];
				if ( typeof fn == "object" )
					fn = fn[ m[2] ];

				if ( typeof fn == "string" )
					fn = eval("false||function(a,i){return " + fn + ";}");

				// Execute it against the current filter
				r = jQuery.grep( r, function(elem, i){
					return fn(elem, i, m, r);
				}, not );
			}
		}

		// Return an array of filtered elements (r)
		// and the modified expression string (t)
		return { r: r, t: t };
	},

	dir: function( elem, dir ){
		///	<summary>
		///		This member is internal only.
		///	</summary>
		///	<private />
		// This member is not documented in the jQuery API: http://docs.jquery.com/Special:Search?ns0=1&search=dir
		var matched = [],
			cur = elem[dir];
		while ( cur && cur != document ) {
			if ( cur.nodeType == 1 )
				matched.push( cur );
			cur = cur[dir];
		}
		return matched;
	},

	nth: function(cur,result,dir,elem){
		///	<summary>
		///		This member is internal only.
		///	</summary>
		///	<private />
		// This member is not documented in the jQuery API: http://docs.jquery.com/Special:Search?ns0=1&search=nth
		result = result || 1;
		var num = 0;

		for ( ; cur; cur = cur[dir] )
			if ( cur.nodeType == 1 && ++num == result )
				break;

		return cur;
	},
	
	sibling: function( n, elem ) {
		///	<summary>
		///		This member is internal only.
		///	</summary>
		///	<private />
		// This member is not documented in the jQuery API: http://docs.jquery.com/Special:Search?ns0=1&search=sibling
		var r = [];

		for ( ; n; n = n.nextSibling ) {
			if ( n.nodeType == 1 && n != elem )
				r.push( n );
		}

		return r;
	}
});
/*
 * A number of helper functions used for managing events.
 * Many of the ideas behind this code orignated from
 * Dean Edwards' addEvent library.
 */
jQuery.event = {

	// Bind an event to an element
	// Original by Dean Edwards
	add: function(elem, types, handler, data) {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		if ( elem.nodeType == 3 || elem.nodeType == 8 )
			return;

		// For whatever reason, IE has trouble passing the window object
		// around, causing it to be cloned in the process
		if ( jQuery.browser.msie && elem.setInterval )
			elem = window;

		// Make sure that the function being executed has a unique ID
		if ( !handler.guid )
			handler.guid = this.guid++;

		// if data is passed, bind to handler
		if( data != undefined ) {
			// Create temporary function pointer to original handler
			var fn = handler;

			// Create unique handler function, wrapped around original handler
			handler = this.proxy( fn, function() {
				// Pass arguments and context to original handler
				return fn.apply(this, arguments);
			});

			// Store data in unique handler
			handler.data = data;
		}

		// Init the element's event structure
		var events = jQuery.data(elem, "events") || jQuery.data(elem, "events", {}),
			handle = jQuery.data(elem, "handle") || jQuery.data(elem, "handle", function(){
				// Handle the second event of a trigger and when
				// an event is called after a page has unloaded
				if ( typeof jQuery != "undefined" && !jQuery.event.triggered )
					return jQuery.event.handle.apply(arguments.callee.elem, arguments);
			});
		// Add elem as a property of the handle function
		// This is to prevent a memory leak with non-native
		// event in IE.
		handle.elem = elem;

		// Handle multiple events separated by a space
		// jQuery(...).bind("mouseover mouseout", fn);
		jQuery.each(types.split(/\s+/), function(index, type) {
			// Namespaced event handlers
			var parts = type.split(".");
			type = parts[0];
			handler.type = parts[1];

			// Get the current list of functions bound to this event
			var handlers = events[type];

			// Init the event handler queue
			if (!handlers) {
				handlers = events[type] = {};

				// Check for a special event handler
				// Only use addEventListener/attachEvent if the special
				// events handler returns false
				if ( !jQuery.event.special[type] || jQuery.event.special[type].setup.call(elem) === false ) {
					// Bind the global event handler to the element
					if (elem.addEventListener)
						elem.addEventListener(type, handle, false);
					else if (elem.attachEvent)
						elem.attachEvent("on" + type, handle);
				}
			}

			// Add the function to the element's handler list
			handlers[handler.guid] = handler;

			// Keep track of which events have been used, for global triggering
			jQuery.event.global[type] = true;
		});

		// Nullify elem to prevent memory leaks in IE
		elem = null;
	},

	guid: 1,
	global: {},

	// Detach an event or set of events from an element
	remove: function(elem, types, handler) {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		
		// don't do events on text and comment nodes
		if ( elem.nodeType == 3 || elem.nodeType == 8 )
			return;

		var events = jQuery.data(elem, "events"), ret, index;

		if ( events ) {
			// Unbind all events for the element
			if ( types == undefined || (typeof types == "string" && types.charAt(0) == ".") )
				for ( var type in events )
					this.remove( elem, type + (types || "") );
			else {
				// types is actually an event object here
				if ( types.type ) {
					handler = types.handler;
					types = types.type;
				}

				// Handle multiple events seperated by a space
				// jQuery(...).unbind("mouseover mouseout", fn);
				jQuery.each(types.split(/\s+/), function(index, type){
					// Namespaced event handlers
					var parts = type.split(".");
					type = parts[0];

					if ( events[type] ) {
						// remove the given handler for the given type
						if ( handler )
							delete events[type][handler.guid];

						// remove all handlers for the given type
						else
							for ( handler in events[type] )
								// Handle the removal of namespaced events
								if ( !parts[1] || events[type][handler].type == parts[1] )
									delete events[type][handler];

						// remove generic event handler if no more handlers exist
						for ( ret in events[type] ) break;
						if ( !ret ) {
							if ( !jQuery.event.special[type] || jQuery.event.special[type].teardown.call(elem) === false ) {
								if (elem.removeEventListener)
									elem.removeEventListener(type, jQuery.data(elem, "handle"), false);
								else if (elem.detachEvent)
									elem.detachEvent("on" + type, jQuery.data(elem, "handle"));
							}
							ret = null;
							delete events[type];
						}
					}
				});
			}

			// Remove the expando if it's no longer used
			for ( ret in events ) break;
			if ( !ret ) {
				var handle = jQuery.data( elem, "handle" );
				if ( handle ) handle.elem = null;
				jQuery.removeData( elem, "events" );
				jQuery.removeData( elem, "handle" );
			}
		}
	},

	trigger: function(type, data, elem, donative, extra) {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		
		// Clone the incoming data, if any
		data = jQuery.makeArray(data);

		if ( type.indexOf("!") >= 0 ) {
			type = type.slice(0, -1);
			var exclusive = true;
		}

		// Handle a global trigger
		if ( !elem ) {
			// Only trigger if we've ever bound an event for it
			if ( this.global[type] )
				jQuery("*").add([window, document]).trigger(type, data);

		// Handle triggering a single element
		} else {
			// don't do events on text and comment nodes
			if ( elem.nodeType == 3 || elem.nodeType == 8 )
				return undefined;

			var val, ret, fn = jQuery.isFunction( elem[ type ] || null ),
				// Check to see if we need to provide a fake event, or not
				event = !data[0] || !data[0].preventDefault;

			// Pass along a fake event
			if ( event ) {
				data.unshift({
					type: type,
					target: elem,
					preventDefault: function(){},
					stopPropagation: function(){},
					timeStamp: now()
				});
				data[0][expando] = true; // no need to fix fake event
			}

			// Enforce the right trigger type
			data[0].type = type;
			if ( exclusive )
				data[0].exclusive = true;

			// Trigger the event, it is assumed that "handle" is a function
			var handle = jQuery.data(elem, "handle");
			if ( handle )
				val = handle.apply( elem, data );

			// Handle triggering native .onfoo handlers (and on links since we don't call .click() for links)
			if ( (!fn || (jQuery.nodeName(elem, 'a') && type == "click")) && elem["on"+type] && elem["on"+type].apply( elem, data ) === false )
				val = false;

			// Extra functions don't get the custom event object
			if ( event )
				data.shift();

			// Handle triggering of extra function
			if ( extra && jQuery.isFunction( extra ) ) {
				// call the extra function and tack the current return value on the end for possible inspection
				ret = extra.apply( elem, val == null ? data : data.concat( val ) );
				// if anything is returned, give it precedence and have it overwrite the previous value
				if (ret !== undefined)
					val = ret;
			}

			// Trigger the native events (except for clicks on links)
			if ( fn && donative !== false && val !== false && !(jQuery.nodeName(elem, 'a') && type == "click") ) {
				this.triggered = true;
				try {
					elem[ type ]();
				// prevent IE from throwing an error for some hidden elements
				} catch (e) {}
			}

			this.triggered = false;
		}

		return val;
	},

	handle: function(event) {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		
		// returned undefined or false
		var val, ret, namespace, all, handlers;

		event = arguments[0] = jQuery.event.fix( event || window.event );

		// Namespaced event handlers
		namespace = event.type.split(".");
		event.type = namespace[0];
		namespace = namespace[1];
		// Cache this now, all = true means, any handler
		all = !namespace && !event.exclusive;

		handlers = ( jQuery.data(this, "events") || {} )[event.type];

		for ( var j in handlers ) {
			var handler = handlers[j];

			// Filter the functions by class
			if ( all || handler.type == namespace ) {
				// Pass in a reference to the handler function itself
				// So that we can later remove it
				event.handler = handler;
				event.data = handler.data;

				ret = handler.apply( this, arguments );

				if ( val !== false )
					val = ret;

				if ( ret === false ) {
					event.preventDefault();
					event.stopPropagation();
				}
			}
		}

		return val;
	},

	fix: function(event) {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		
		if ( event[expando] == true )
			return event;

		// store a copy of the original event object
		// and "clone" to set read-only properties
		var originalEvent = event;
		event = { originalEvent: originalEvent };
		var props = "altKey attrChange attrName bubbles button cancelable charCode clientX clientY ctrlKey currentTarget data detail eventPhase fromElement handler keyCode metaKey newValue originalTarget pageX pageY prevValue relatedNode relatedTarget screenX screenY shiftKey srcElement target timeStamp toElement type view wheelDelta which".split(" ");
		for ( var i=props.length; i; i-- )
			event[ props[i] ] = originalEvent[ props[i] ];

		// Mark it as fixed
		event[expando] = true;

		// add preventDefault and stopPropagation since
		// they will not work on the clone
		event.preventDefault = function() {
			// if preventDefault exists run it on the original event
			if (originalEvent.preventDefault)
				originalEvent.preventDefault();
			// otherwise set the returnValue property of the original event to false (IE)
			originalEvent.returnValue = false;
		};
		event.stopPropagation = function() {
			// if stopPropagation exists run it on the original event
			if (originalEvent.stopPropagation)
				originalEvent.stopPropagation();
			// otherwise set the cancelBubble property of the original event to true (IE)
			originalEvent.cancelBubble = true;
		};

		// Fix timeStamp
		event.timeStamp = event.timeStamp || now();

		// Fix target property, if necessary
		if ( !event.target )
			event.target = event.srcElement || document; // Fixes #1925 where srcElement might not be defined either

		// check if target is a textnode (safari)
		if ( event.target.nodeType == 3 )
			event.target = event.target.parentNode;

		// Add relatedTarget, if necessary
		if ( !event.relatedTarget && event.fromElement )
			event.relatedTarget = event.fromElement == event.target ? event.toElement : event.fromElement;

		// Calculate pageX/Y if missing and clientX/Y available
		if ( event.pageX == null && event.clientX != null ) {
			var doc = document.documentElement, body = document.body;
			event.pageX = event.clientX + (doc && doc.scrollLeft || body && body.scrollLeft || 0) - (doc.clientLeft || 0);
			event.pageY = event.clientY + (doc && doc.scrollTop || body && body.scrollTop || 0) - (doc.clientTop || 0);
		}

		// Add which for key events
		if ( !event.which && ((event.charCode || event.charCode === 0) ? event.charCode : event.keyCode) )
			event.which = event.charCode || event.keyCode;

		// Add metaKey to non-Mac browsers (use ctrl for PC's and Meta for Macs)
		if ( !event.metaKey && event.ctrlKey )
			event.metaKey = event.ctrlKey;

		// Add which for click: 1 == left; 2 == middle; 3 == right
		// Note: button is not normalized, so don't use it
		if ( !event.which && event.button )
			event.which = (event.button & 1 ? 1 : ( event.button & 2 ? 3 : ( event.button & 4 ? 2 : 0 ) ));

		return event;
	},

	proxy: function( fn, proxy ){
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		
		// Set the guid of unique handler to the same of original handler, so it can be removed
		proxy.guid = fn.guid = fn.guid || proxy.guid || this.guid++;
		// So proxy can be declared as an argument
		return proxy;
	},

	special: {
		ready: {
			setup: function() {
				///	<summary>
				///		This method is internal.
				///	</summary>
				///	<private />
				
				// Make sure the ready event is setup
				bindReady();
				return;
			},

			teardown: function() { return; }
		},

		mouseenter: {
			setup: function() {
				///	<summary>
				///		This method is internal.
				///	</summary>
				///	<private />
				
				if ( jQuery.browser.msie ) return false;
				jQuery(this).bind("mouseover", jQuery.event.special.mouseenter.handler);
				return true;
			},

			teardown: function() {
				///	<summary>
				///		This method is internal.
				///	</summary>
				///	<private />
				
				if ( jQuery.browser.msie ) return false;
				jQuery(this).unbind("mouseover", jQuery.event.special.mouseenter.handler);
				return true;
			},

			handler: function(event) {
				///	<summary>
				///		This method is internal.
				///	</summary>
				///	<private />
				
				// If we actually just moused on to a sub-element, ignore it
				if ( withinElement(event, this) ) return true;
				// Execute the right handlers by setting the event type to mouseenter
				event.type = "mouseenter";
				return jQuery.event.handle.apply(this, arguments);
			}
		},

		mouseleave: {
			setup: function() {
				///	<summary>
				///		This method is internal.
				///	</summary>
				///	<private />
				
				if ( jQuery.browser.msie ) return false;
				jQuery(this).bind("mouseout", jQuery.event.special.mouseleave.handler);
				return true;
			},

			teardown: function() {
				///	<summary>
				///		This method is internal.
				///	</summary>
				///	<private />
				
				if ( jQuery.browser.msie ) return false;
				jQuery(this).unbind("mouseout", jQuery.event.special.mouseleave.handler);
				return true;
			},

			handler: function(event) {
				///	<summary>
				///		This method is internal.
				///	</summary>
				///	<private />
				
				// If we actually just moused on to a sub-element, ignore it
				if ( withinElement(event, this) ) return true;
				// Execute the right handlers by setting the event type to mouseleave
				event.type = "mouseleave";
				return jQuery.event.handle.apply(this, arguments);
			}
		}
	}
};

jQuery.fn.extend({
	bind: function( type, data, fn ) {
		///	<summary>
	    ///		为每一个匹配元素的特定事件（像click）绑定一个事件处理器函数。
	    ///     这个事件处理函数会接收到一个事件对象，可以通过它来阻止（浏览器）默认的行为。
	    ///     如果既想取消默认的行为，又想阻止事件起泡，这个事件处理函数必须返回false。多数情况下，可以把事件处理器函数定义为匿名函数。
	    ///     在不可能定义匿名函数的情况下，可以传递一个可选的数据对象作为第二个参数（而事件处理器函数则作为第三个参数）。
		///	</summary>
	    ///	<param name="type" type="String">一个或多个事件类型  内建事件类型值有: blur, focus, load, resize, scroll, unload, click, dblclick, mousedown, mouseup, mousemove, mouseover, mouseout, mouseenter, mouseleave, change, select, submit, keydown, keypress, keyup, error .</param>
	    ///	<param name="data" optional="true" type="Object"> (可选) 作为event.data属性值传递给事件对象的额外数据对象</param>
	    ///	<param name="fn" type="Function">绑定到每个匹配元素的事件上面的处理函数。回调函数（ eventObject ）等对应的DOM元素。</param>
		
		return type == "unload" ? this.one(type, data, fn) : this.each(function(){
			jQuery.event.add( this, type, fn || data, fn && data );
		});
	},

	one: function( type, data, fn ) {
		///	<summary>
	    ///		为每一个匹配元素的特定事件（像click）绑定一个一次性的事件处理函数。
	    ///     在每个对象上，这个事件处理函数只会被执行一次。其他规则与bind()函数相同。
	    ///     这个事件处理函数会接收到一个事件对象，可以通过它来阻止（浏览器）默认的行为。
	    ///     如果既想取消默认的行为，又想阻止事件起泡，这个事件处理函数必须返回false。
        ///     多数情况下，可以把事件处理函数定义为匿名函数。
        ///     在不可能定义匿名函数的情况下，可以传递一个可选的数据对象作为第二个参数（而事件处理函数则作为第三个参数）。
		///	</summary>
		///	<param name="type" type="String">一个或多个事件类型  内建事件类型的值是: blur, focus, load, resize, scroll, unload, click, dblclick, mousedown, mouseup, mousemove, mouseover, mouseout, mouseenter, mouseleave, change, select, submit, keydown, keypress, keyup, error .</param>
	    ///	<param name="data" optional="true" type="Object">(可选) 作为event.data属性值传递给事件对象的额外数据对象</param>
	    ///	<param name="fn" type="Function">绑定到每个匹配元素的事件上面的处理函数。回调函数（ eventObject ）等对应的DOM元素。</param>
		
		var one = jQuery.event.proxy( fn || data, function(event) {
			jQuery(this).unbind(event, one);
			return (fn || data).apply( this, arguments );
		});
		return this.each(function(){
			jQuery.event.add( this, type, one, fn && data);
		});
	},

	unbind: function( type, fn ) {
		///	<summary>
	    ///		bind()的反向操作，从每一个匹配的元素中删除绑定的事件。
		///	</summary>
	    ///	<param name="type" type="String">一个或多个事件类型  内建事件类型的值是: blur, focus, load, resize, scroll, unload, click, dblclick, mousedown, mouseup, mousemove, mouseover, mouseout, mouseenter, mouseleave, change, select, submit, keydown, keypress, keyup, error .</param>
	    ///	<param name="fn" type="Function">绑定到每个匹配元素的事件上面的处理函数。回调函数（ eventObject ）等对应的DOM元素。</param>
		
		return this.each(function(){
			jQuery.event.remove( this, type, fn );
		});
	},

	trigger: function( type, data, fn ) {
		///	<summary>
		///		在每一个匹配的元素上触发某类事件。
        ///     这个函数也会导致浏览器同名的默认行为的执行。比如，如果用trigger()触发一个'submit'，则同样会导致浏览器提交表单。
        ///     如果要阻止这种默认行为，应返回false。
        ///     你也可以触发由bind()注册的自定义事件
		///	</summary>
	    ///	<param name="type" type="String">一个或多个要触发的事件类型  内建事件类型的值是: blur, focus, load, resize, scroll, unload, click, dblclick, mousedown, mouseup, mousemove, mouseover, mouseout, mouseenter, mouseleave, change, select, submit, keydown, keypress, keyup, error .</param>
		///	<param name="data" optional="true" type="Array">(可选)传递给事件处理函数的附加参数</param>
		///	<param name="fn" type="Function">This parameter is undocumented.</param>
		
		return this.each(function(){
			jQuery.event.trigger( type, data, this, true, fn );
		});
	},

	triggerHandler: function( type, data, fn ) {
		///	<summary>
	    ///		这个特别的方法将会触发指定的事件类型上所有绑定的处理函数。但不会执行浏览器默认动作.
		///	</summary>
	    ///	<param name="type" type="String">一个或多个要触发的事件类型  内建事件类型的值是: blur, focus, load, resize, scroll, unload, click, dblclick, mousedown, mouseup, mousemove, mouseover, mouseout, mouseenter, mouseleave, change, select, submit, keydown, keypress, keyup, error .</param>
	    ///	<param name="data" optional="true" type="Array">(可选)传递给事件处理函数的附加参数</param>
		///	<param name="fn" type="Function">This parameter is undocumented.</param>
	
		return this[0] && jQuery.event.trigger(type, data, this[0], false, fn);
	},

	toggle: function( fn ) {
		///	<summary>
	    ///		每次点击后依次调用函数。
	    ///     如果点击了一个匹配的元素，则触发指定的第一个函数，当再次点击同一元素时，则触发指定的第二个函数，
	    ///     如果有更多函数，则再次触发，直到最后一个。
	    ///     随后的每次点击都重复对这几个函数的轮番调用。
        ///     可以使用unbind("click")来删除。
		///	</summary>
	    ///	<param name="fn" type="Function">要循环执行的函数。</param>
		
		// Save reference to arguments for access in closure
		var args = arguments, i = 1;

		// link all the functions, so any of them can unbind this click handler
		while( i < args.length )
			jQuery.event.proxy( fn, args[i++] );

		return this.click( jQuery.event.proxy( fn, function(event) {
			// Figure out which function to execute
			this.lastToggle = ( this.lastToggle || 0 ) % i;

			// Make sure that clicks stop
			event.preventDefault();

			// and execute the function
			return args[ this.lastToggle++ ].apply( this, arguments ) || false;
		}));
	},

	hover: function(fnOver, fnOut) {
		///	<summary>
	    ///		一个模仿悬停事件（鼠标移动到一个对象上面及移出这个对象）的方法。
	    ///     这是一个自定义的方法，它为频繁使用的任务提供了一种“保持在其中”的状态。
	    ///     当鼠标移动到一个匹配的元素上面时，会触发指定的第一个函数。当鼠标移出这个元素时，会触发指定的第二个函数。
	    ///     而且，会伴随着对鼠标是否仍然处在特定元素中的检测（例如，处在div中的图像），
	    ///     如果是，则会继续保持“悬停”状态，而不触发移出事件（修正了使用mouseout事件的一个常见错误）。
		///	</summary>
	    ///	<param name="fnOver" type="Function"> 鼠标移到元素上要触发的函数</param>
	    ///	<param name="fnOut" type="Function">鼠标移出元素要触发的函数</param>
		
		return this.bind('mouseenter', fnOver).bind('mouseleave', fnOut);
	},

	ready: function(fn) {
		///	<summary>
		///		当DOM载入就绪可以查询及操纵时绑定一个要执行的函数。
        ///     这是事件模块中最重要的一个函数，因为它可以极大地提高web应用程序的响应速度。
        ///     简单地说，这个方法纯粹是对向window.load事件注册事件的替代方法。
        ///     通过使用这个方法，可以在DOM载入就绪能够读取并操纵时立即调用你所绑定的函数，而99.99%的JavaScript函数都需要在那一刻执行。
        ///     有一个参数－－对jQuery函数的引用－－会传递到这个ready事件处理函数中。可以给这个参数任意起一个名字，并因此可以不再担心命名冲突而放心地使用$别名。
        ///     请确保在 <body> 元素的onload事件中没有注册函数，否则不会触发$(document).ready()事件。
        ///     可以在同一个页面中无限次地使用$(document).ready()事件。其中注册的函数会按照（代码中的）先后顺序依次执行。
		///	</summary>
		///	<param name="fn" type="Function">The function to be executed when the DOM is ready.</param>

		// Attach the listeners
		bindReady();

		// If the DOM is already ready
		if ( jQuery.isReady )
			// Execute the function immediately
			fn.call( document, jQuery );

		// Otherwise, remember the function for later
		else
			// Add the function to the wait list
			jQuery.readyList.push( function() { return fn.call(this, jQuery); } );

		return this;
	}
});

jQuery.extend({
	isReady: false,
	readyList: [],
	// Handle when the DOM is ready
	ready: function() {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		
		// Make sure that the DOM is not already loaded
		if ( !jQuery.isReady ) {
			// Remember that the DOM is ready
			jQuery.isReady = true;

			// If there are functions bound, to execute
			if ( jQuery.readyList ) {
				// Execute all of them
				jQuery.each( jQuery.readyList, function(){
					this.call( document );
				});

				// Reset the list of functions
				jQuery.readyList = null;
			}

			// Trigger any bound ready events
			jQuery(document).triggerHandler("ready");
		}
	}
});

var readyBound = false;

function bindReady(){
	///	<summary>
	///		This method is internal.
	///	</summary>
	///	<private />
	if ( readyBound ) return;
	readyBound = true;

	// Mozilla, Opera (see further below for it) and webkit nightlies currently support this event
	if ( document.addEventListener && !jQuery.browser.opera)
		// Use the handy event callback
		document.addEventListener( "DOMContentLoaded", jQuery.ready, false );

	// If IE is used and is not in a frame
	// Continually check to see if the document is ready
	if ( jQuery.browser.msie && window == top ) (function(){
		if (jQuery.isReady) return;
		try {
			// If IE is used, use the trick by Diego Perini
			// http://javascript.nwbox.com/IEContentLoaded/
			document.documentElement.doScroll("left");
		} catch( error ) {
			setTimeout( arguments.callee, 0 );
			return;
		}
		// and execute any waiting functions
		jQuery.ready();
	})();

	if ( jQuery.browser.opera )
		document.addEventListener( "DOMContentLoaded", function () {
			if (jQuery.isReady) return;
			for (var i = 0; i < document.styleSheets.length; i++)
				if (document.styleSheets[i].disabled) {
					setTimeout( arguments.callee, 0 );
					return;
				}
			// and execute any waiting functions
			jQuery.ready();
		}, false);

	if ( jQuery.browser.safari ) {
		var numStyles;
		(function(){
			if (jQuery.isReady) return;
			if ( document.readyState != "loaded" && document.readyState != "complete" ) {
				setTimeout( arguments.callee, 0 );
				return;
			}
			if ( numStyles === undefined )
				numStyles = jQuery("style, link[rel=stylesheet]").length;
			if ( document.styleSheets.length != numStyles ) {
				setTimeout( arguments.callee, 0 );
				return;
			}
			// and execute any waiting functions
			jQuery.ready();
		})();
	}

	// A fallback to window.onload, that will always work
	jQuery.event.add( window, "load", jQuery.ready );
}

// [vsdoc] The following section has been denormalized from original sources for IntelliSense.

jQuery.fn["blur"] = function(fn) {
	///	<summary>
    ///		1: blur() - 触发每一个匹配元素的blur事件。这个函数会调用执行绑定到blur事件的所有函数，包括浏览器的默认行为。
    ///     可以通过返回false来防止触发浏览器的默认行为。blur事件会在元素失去焦点的时候触发，既可以是鼠标行为，也可以是按tab键离开的
    ///		2: blur(fn) - 在每一个匹配元素的blur事件中绑定一个处理函数。blur事件会在元素失去焦点的时候触发，既可以是鼠标行为，也可以是按tab键离开的
	///	</summary>
	///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("blur", fn) : this.trigger(name);
};

jQuery.fn["focus"] = function(fn) {
	///	<summary>
    ///		1: focus() - 触发每一个匹配元素的focus事件。这将触发所有绑定的focus函数，注意，某些对象不支持focus方法。
    ///		2: focus(fn) - 在每一个匹配元素的focus事件中绑定一个处理函数。focus事件可以通过鼠标点击或者键盘上的TAB导航触发
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("focus", fn) : this.trigger(name);
};

jQuery.fn["load"] = function(fn) {
	///	<summary>
    ///		1: load() - 触发每一个匹配元素的laod事件。
    ///		2: load(fn) - 在每一个匹配元素的load事件中绑定一个处理函数。
    ///     如果绑定给window对象，则会在所有内容加载后触发，包括窗口，框架，对象和图像。如果绑定在元素上，则当元素的内容加载完毕后触发。
    ///     注意，只有当在这个元素完全加载完之前绑定load的处理函数,才会在他加载完后触发。如果之后再绑定就永远不会触发了。
    ///     所以不要在$(document).ready()里绑定load事件，因为jQuery会在所有DOM加载完成后再绑定load事件。
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("load", fn) : this.trigger(name);
};

jQuery.fn["resize"] = function(fn) {
	///	<summary>
    ///		1: resize() - 触发每一个匹配元素的resize事件。
    ///		2: resize(fn) - 在每一个匹配元素的resize事件中绑定一个处理函数。 当文档窗口改变大小时触发
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("resize", fn) : this.trigger(name);
};

jQuery.fn["scroll"] = function(fn) {
	///	<summary>
    ///		1: scroll() - 触发每一个匹配元素的scroll事件。
    ///		2: scroll(fn) - 在每一个匹配元素的scroll事件中绑定一个处理函数。当滚动条发生变化时触发
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("scroll", fn) : this.trigger(name);
};

jQuery.fn["unload"] = function(fn) {
	///	<summary>
    ///		1: unload() - 触发每一个匹配元素的unload事件。
    ///		2: unload(fn) - 在每一个匹配元素的unload事件中绑定一个处理函数。页面卸载时执行
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("unload", fn) : this.trigger(name);
};

jQuery.fn["click"] = function(fn) {
	///	<summary>
    ///		1: click() - 触发每一个匹配元素的click事件。
    ///     单击的定义是在屏幕的同一点触发了mousedown和mouseup.几个事件触发的顺序是：mousedown mouseup click
    ///		2: click(fn) - 在每一个匹配元素的click事件中绑定一个处理函数。点击事件会在你的指针设备的按钮在元素上单击时触发。
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("click", fn) : this.trigger(name);
};

jQuery.fn["dblclick"] = function(fn) {
	///	<summary>
    ///		1: dblclick() - 触发每一个匹配元素的dblclick事件。
    ///     这个函数会调用执行绑定到dblclick事件的所有函数，包括浏览器的默认行为。
    ///     可以通过在某个绑定的函数中返回false来防止触发浏览器的默认行为。dblclick事件会在元素的同一点双击时触发。
    ///		2: dblclick(fn) - 在每一个匹配元素的dblclick事件中绑定一个处理函数。
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("dblclick", fn) : this.trigger(name);
};

jQuery.fn["mousedown"] = function(fn) {
	///	<summary>
    ///		在每一个匹配元素的mousedown事件中绑定一个处理函数。
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("mousedown", fn) : this.trigger(name);
};

jQuery.fn["mouseup"] = function(fn) {
	///	<summary>
    ///		在每一个匹配元素的mouseup事件中绑定一个处理函数。
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("mouseup", fn) : this.trigger(name);
};

jQuery.fn["mousemove"] = function(fn) {
	///	<summary>
    ///		在每一个匹配元素的mousemove事件中绑定一个处理函数。
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("mousemove", fn) : this.trigger(name);
};

jQuery.fn["mouseover"] = function(fn) {
	///	<summary>
    ///		在每一个匹配元素的mouseover事件中绑定一个处理函数。
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("mouseover", fn) : this.trigger(name);
};

jQuery.fn["mouseout"] = function(fn) {
	///	<summary>
    ///		在每一个匹配元素的mouseout事件中绑定一个处理函数。 
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("mouseout", fn) : this.trigger(name);
};

jQuery.fn["change"] = function(fn) {
	///	<summary>
    ///		1: change() - 触发每一个匹配元素的change事件。这个函数会调用执行绑定到change事件的所有函数，包括浏览器的默认行为。
    ///     可以通过在某个绑定的函数中返回false来防止触发浏览器的默认行为。change事件会在元素失去焦点的时候触发，也会当其值在获得焦点后改变时触发。
    ///		2: change(fn) - 在每一个匹配元素的change事件中绑定一个处理函数。 
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("change", fn) : this.trigger(name);
};

jQuery.fn["select"] = function(fn) {
	///	<summary>
    ///		1: select() - 触发每一个匹配元素的select事件。
    ///     这个函数会调用执行绑定到select事件的所有函数，包括浏览器的默认行为。
    ///     可以通过在某个绑定的函数中返回false来防止触发浏览器的默认行为。
    ///		2: select(fn) - 在每一个匹配元素的select事件中绑定一个处理函数。
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("select", fn) : this.trigger(name);
};

jQuery.fn["submit"] = function(fn) {
	///	<summary>
    ///		1: submit() - 触发每一个匹配元素的submit事件。
    ///     这个函数会调用执行绑定到submit事件的所有函数，包括浏览器的默认行为。
    ///     可以通过在某个绑定的函数中返回false来防止触发浏览器的默认行为。
    ///		2: submit(fn) - 在每一个匹配元素的submit事件中绑定一个处理函数。
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("submit", fn) : this.trigger(name);
};

jQuery.fn["keydown"] = function(fn) {
	///	<summary>
    ///		1: keydown() - 触发每一个匹配元素的keydown事件。
    ///		2: keydown(fn) - 在每一个匹配元素的keydown事件中绑定一个处理函数。
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("keydown", fn) : this.trigger(name);
};

jQuery.fn["keypress"] = function(fn) {
	///	<summary>
    ///		1: keypress() - 触发每一个匹配元素的keypress事件。
    ///		2: keypress(fn) - 在每一个匹配元素的keypress事件中绑定一个处理函数。
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("keypress", fn) : this.trigger(name);
};

jQuery.fn["keyup"] = function(fn) {
	///	<summary>
    ///		1: keyup() - 触发每一个匹配元素的keyup事件。
    ///		2: keyup(fn) - 在每一个匹配元素的keyup事件中绑定一个处理函数。
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("keyup", fn) : this.trigger(name);
};

jQuery.fn["error"] = function(fn) {
	///	<summary>
    ///		1: error() - 触发每一个匹配元素的error事件。
    ///		2: error(fn) - 在每一个匹配元素的error事件中绑定一个处理函数。
    ///     对于error事件，没有一个公众的标准。在大多数浏览器中，当页面的JavaScript发生错误时，window对象会触发error事件;
    ///     当图像的src属性无效时，比如文件不存在或者图像数据错误时，也会触发图像对象的error事件。
    ///     如果异常是由window对象抛出，事件处理函数将会被传入三个参数：(
    ///     1. 描述事件的信息 ("varName is not defined", "missing operator in expression", 等等.),
    ///     2. 包含错误的文档的完整URL
    ///     3. 异常发生的行数。如果事件处理函数返回true，则表示事件已经被处理，浏览器将认为没有异常。)
	///	</summary>
    ///	<param name="fn" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return fn ? this.bind("error", fn) : this.trigger(name);
};


// Checks if an event happened on an element within another element
// Used in jQuery.event.special.mouseenter and mouseleave handlers
var withinElement = function(event, elem) {
	///	<summary>
	///		This method is internal.
	///	</summary>
	///	<private />

	// Check if mouse(over|out) are still within the same parent element
	var parent = event.relatedTarget;
	// Traverse up the tree
	while ( parent && parent != elem ) try { parent = parent.parentNode; } catch(error) { parent = elem; }
	// Return true if we actually just moused on to a sub-element
	return parent == elem;
};

// Prevent memory leaks in IE
// And prevent errors on refresh with events like mouseover in other browsers
// Window isn't included so as not to unbind existing unload events
jQuery(window).bind("unload", function() {
	jQuery("*").add(document).unbind();
});
jQuery.fn.extend({
	// Keep a copy of the old load
	_load: jQuery.fn.load,

	load: function( url, params, callback ) {
		///	<summary>
	    ///		载入远程 HTML 文件代码并插入至 DOM 中。 默认使用 GET 方式 - 传递附加参数时自动转换为 POST 方式。
	    ///		jQuery 1.2 中，可以指定选择符，来筛选载入的 HTML 文档，DOM 中将仅插入筛选出的 HTML 代码。语法形如 "url #some > selector"。
		///	</summary>
	    ///	<param name="url" type="String">待装入 HTML 网页网址。</param>
	    ///	<param name="data" optional="true" type="Map">(可选) 发送至服务器的 key/value 数据。</param>
	    ///	<param name="callback" optional="true" type="Function">(可选) 载入成功时回调函数。</param>
		///	<returns type="jQuery" />
		
		if ( typeof url != 'string' )
			return this._load( url );

		var off = url.indexOf(" ");
		if ( off >= 0 ) {
			var selector = url.slice(off, url.length);
			url = url.slice(0, off);
		}

		callback = callback || function(){};

		// Default to a GET request
		var type = "GET";

		// If the second parameter was provided
		if ( params )
			// If it's a function
			if ( jQuery.isFunction( params ) ) {
				// We assume that it's the callback
				callback = params;
				params = null;

			// Otherwise, build a param string
			} else {
				params = jQuery.param( params );
				type = "POST";
			}

		var self = this;

		// Request the remote document
		jQuery.ajax({
			url: url,
			type: type,
			dataType: "html",
			data: params,
			complete: function(res, status){
				// If successful, inject the HTML into all the matched elements
				if ( status == "success" || status == "notmodified" )
					// See if a selector was specified
					self.html( selector ?
						// Create a dummy div to hold the results
						jQuery("<div/>")
							// inject the contents of the document in, removing the scripts
							// to avoid any 'Permission Denied' errors in IE
							.append(res.responseText.replace(/<script(.|\s)*?\/script>/g, ""))

							// Locate the specified elements
							.find(selector) :

						// If not, just inject the full result
						res.responseText );

				self.each( callback, [res.responseText, status, res] );
			}
		});
		return this;
	},

	serialize: function() {
		///	<summary>
	    ///		序列表表格内容为字符串。
		///	</summary>
		///	<returns type="String">序列后的结果</returns>
	
		return jQuery.param(this.serializeArray());
	},
	serializeArray: function() {
		///	<summary>
	    ///		序列化表格元素 (类似 '.serialize()' 方法) 返回 JSON 数据结构数据。
		///	</summary>
	    ///	<returns type="String">返回的JSON 数据结构数据。</returns>
	
		return this.map(function(){
			return jQuery.nodeName(this, "form") ?
				jQuery.makeArray(this.elements) : this;
		})
		.filter(function(){
			return this.name && !this.disabled &&
				(this.checked || /select|textarea/i.test(this.nodeName) ||
					/text|hidden|password/i.test(this.type));
		})
		.map(function(i, elem){
			var val = jQuery(this).val();
			return val == null ? null :
				val.constructor == Array ?
					jQuery.map( val, function(val, i){
						return {name: elem.name, value: val};
					}) :
					{name: elem.name, value: val};
		}).get();
	}
});

// Attach a bunch of functions for handling common AJAX events
// [vsdoc] The following section has been denormalized from original sources for IntelliSense.

jQuery.fn["ajaxStart"] = function(callback) {
	///	<summary>
    ///		AJAX 请求开始时执行函数。Ajax 事件。
	///	</summary>
	///	<param name="callback" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return this.bind("ajaxStart", f);
};

jQuery.fn["ajaxStop"] = function(callback) {
	///	<summary>
    ///		AJAX 请求结束时执行函数。Ajax 事件。
	///	</summary>
    ///	<param name="callback" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return this.bind("ajaxStop", f);
};

jQuery.fn["ajaxComplete"] = function(callback) {
	///	<summary>
	///		Attach a function to be executed whenever an AJAX request completes. This is an Ajax Event.
	///	</summary>
	///	<param name="callback" type="Function">The function to execute.</param>
	///	<returns type="jQuery" />
	return this.bind("ajaxComplete", f);
};

jQuery.fn["ajaxError"] = function(callback) {
	///	<summary>
	///		AJAX 请求完成时执行函数。Ajax 事件。XMLHttpRequest 对象和设置作为参数传递给回调函数。
	///	</summary>
    ///	<param name="callback" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return this.bind("ajaxError", f);
};

jQuery.fn["ajaxSuccess"] = function(callback) {
	///	<summary>
    ///		AJAX 请求成功时执行函数。Ajax 事件。XMLHttpRequest 对象和设置作为参数传递给回调函数。
	///	</summary>
    ///	<param name="callback" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return this.bind("ajaxSuccess", f);
};

jQuery.fn["ajaxSend"] = function(callback) {
	///	<summary>
	///		AJAX 请求发送前执行函数。Ajax 事件。XMLHttpRequest 对象和设置作为参数传递给回调函数。
	///	</summary>
    ///	<param name="callback" type="Function">要执行的函数</param>
	///	<returns type="jQuery" />
	return this.bind("ajaxSend", f);
};

var jsc = now();

jQuery.extend({
	get: function( url, data, callback, type ) {
		///	<summary>
		///		通过远程 HTTP GET 请求载入信息。这是一个简单的 GET 请求功能以取代复杂 $.ajax 。
		///     请求成功时可调用回调函数。如果需要在出错时执行函数，请使用 $.ajax。
		///	</summary>
	    ///	<param name="url" type="String">待载入页面的URL地址</param>
	    ///	<param name="data" optional="true" type="Map"> (可选) 待发送 Key/value 参数。</param>
		///	<param name="callback" optional="true" type="Function"> (可选) 载入成功时回调函数。</param>
		///	<param name="type" optional="true" type="String">回调函数的类型，默认有： xml, html, script, json, text, _default.</param>
		///	<returns type="XMLHttpRequest" />
		
		// shift arguments if data argument was ommited
		if ( jQuery.isFunction( data ) ) {
			callback = data;
			data = null;
		}

		return jQuery.ajax({
			type: "GET",
			url: url,
			data: data,
			success: callback,
			dataType: type
		});
	},

	getScript: function( url, callback ) {
		///	<summary>
	    ///		通过 HTTP GET 请求载入并执行一个 JavaScript 文件。
	    ///     jQuery 1.2 版本之前，getScript 只能调用同域 JS 文件。 1.2中，您可以跨域调用 JavaScript 文件。
	    ///     注意：Safari 2 或更早的版本不能在全局作用域中同步执行脚本。如果通过 getScript 加入脚本，请加入延时函数。
		///	</summary>
	    ///	<param name="url" type="String">待载入 JS 文件地址。</param>
	    ///	<param name="callback" optional="true" type="Function">(可选) 成功载入后回调函数。</param>
		///	<returns type="XMLHttpRequest" />
	
		return jQuery.get(url, null, callback, "script");
	},

	getJSON: function( url, data, callback ) {
		///	<summary>
	    ///		通过 HTTP GET 请求载入 JSON 数据。
	    ///     在 jQuery 1.2 中，您可以通过使用JSONP 形式的回调函数来加载其他网域的JSON数据，
	    ///     如 "myurl?callback=?"。jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。 
        ///     注意：此行以后的代码将在这个回调函数执行前执行。
		///	</summary>
	    ///	<param name="url" type="String">发送请求地址。</param>
	    ///	<param name="data" optional="true" type="Map"> (可选) 待发送 Key/value 参数。</param>
	    ///	<param name="callback" optional="true" type="Function">(可选) 载入成功时回调函数。</param>
		///	<returns type="XMLHttpRequest" />
	
		return jQuery.get(url, data, callback, "json");
	},

	post: function( url, data, callback, type ) {
		///	<summary>
	    ///		通过远程 HTTP POST 请求载入信息。
	    ///     这是一个简单的 POST 请求功能以取代复杂 $.ajax 。请求成功时可调用回调函数。如果需要在出错时执行函数，请使用 $.ajax。
		///	</summary>
	    ///	<param name="url" type="String">发送请求地址。</param>
	    ///	<param name="data" optional="true" type="Map"> (可选) 待发送 Key/value 参数。</param>
	    ///	<param name="callback" optional="true" type="Function">(可选) 发送成功时回调函数。</param>
		///	<param name="type" optional="true" type="String">回调函数的数据类型，默认值有： xml, html, script, json, text, _default.</param>
		///	<returns type="XMLHttpRequest" />
		
		if ( jQuery.isFunction( data ) ) {
			callback = data;
			data = {};
		}

		return jQuery.ajax({
			type: "POST",
			url: url,
			data: data,
			success: callback,
			dataType: type
		});
	},

	ajaxSetup: function( settings ) {
		///	<summary>
	    ///		设置全局 AJAX 默认选项。
		///	</summary>
	    ///	<param name="settings" type="Options">选项设置。所有设置项均为可选设置。.</param>
	
		jQuery.extend( jQuery.ajaxSettings, settings );
	},

	ajaxSettings: {
		url: location.href,
		global: true,
		type: "GET",
		timeout: 0,
		contentType: "application/x-www-form-urlencoded",
		processData: true,
		async: true,
		data: null,
		username: null,
		password: null,
		accepts: {
			xml: "application/xml, text/xml",
			html: "text/html",
			script: "text/javascript, application/javascript",
			json: "application/json, text/javascript",
			text: "text/plain",
			_default: "*/*"
		}
	},

	// Last-Modified header cache for next request
	lastModified: {},

	ajax: function( s ) {
		///	<summary>
		///		This member is internal.
		///	</summary>
		///	<private />
		
		// Extend the settings, but re-extend 's' so that it can be
		// checked again later (in the test suite, specifically)
		s = jQuery.extend(true, s, jQuery.extend(true, {}, jQuery.ajaxSettings, s));

		var jsonp, jsre = /=\?(&|$)/g, status, data,
			type = s.type.toUpperCase();

		// convert data if not already a string
		if ( s.data && s.processData && typeof s.data != "string" )
			s.data = jQuery.param(s.data);

		// Handle JSONP Parameter Callbacks
		if ( s.dataType == "jsonp" ) {
			if ( type == "GET" ) {
				if ( !s.url.match(jsre) )
					s.url += (s.url.match(/\?/) ? "&" : "?") + (s.jsonp || "callback") + "=?";
			} else if ( !s.data || !s.data.match(jsre) )
				s.data = (s.data ? s.data + "&" : "") + (s.jsonp || "callback") + "=?";
			s.dataType = "json";
		}

		// Build temporary JSONP function
		if ( s.dataType == "json" && (s.data && s.data.match(jsre) || s.url.match(jsre)) ) {
			jsonp = "jsonp" + jsc++;

			// Replace the =? sequence both in the query string and the data
			if ( s.data )
				s.data = (s.data + "").replace(jsre, "=" + jsonp + "$1");
			s.url = s.url.replace(jsre, "=" + jsonp + "$1");

			// We need to make sure
			// that a JSONP style response is executed properly
			s.dataType = "script";

			// Handle JSONP-style loading
			window[ jsonp ] = function(tmp){
				data = tmp;
				success();
				complete();
				// Garbage collect
				window[ jsonp ] = undefined;
				try{ delete window[ jsonp ]; } catch(e){}
				if ( head )
					head.removeChild( script );
			};
		}

		if ( s.dataType == "script" && s.cache == null )
			s.cache = false;

		if ( s.cache === false && type == "GET" ) {
			var ts = now();
			// try replacing _= if it is there
			var ret = s.url.replace(/(\?|&)_=.*?(&|$)/, "$1_=" + ts + "$2");
			// if nothing was replaced, add timestamp to the end
			s.url = ret + ((ret == s.url) ? (s.url.match(/\?/) ? "&" : "?") + "_=" + ts : "");
		}

		// If data is available, append data to url for get requests
		if ( s.data && type == "GET" ) {
			s.url += (s.url.match(/\?/) ? "&" : "?") + s.data;

			// IE likes to send both get and post data, prevent this
			s.data = null;
		}

		// Watch for a new set of requests
		if ( s.global && ! jQuery.active++ )
			jQuery.event.trigger( "ajaxStart" );

		// Matches an absolute URL, and saves the domain
		var remote = /^(?:\w+:)?\/\/([^\/?#]+)/;

		// If we're requesting a remote document
		// and trying to load JSON or Script with a GET
		if ( s.dataType == "script" && type == "GET"
				&& remote.test(s.url) && remote.exec(s.url)[1] != location.host ){
			var head = document.getElementsByTagName("head")[0];
			var script = document.createElement("script");
			script.src = s.url;
			if (s.scriptCharset)
				script.charset = s.scriptCharset;

			// Handle Script loading
			if ( !jsonp ) {
				var done = false;

				// Attach handlers for all browsers
				script.onload = script.onreadystatechange = function(){
					if ( !done && (!this.readyState ||
							this.readyState == "loaded" || this.readyState == "complete") ) {
						done = true;
						success();
						complete();
						head.removeChild( script );
					}
				};
			}

			head.appendChild(script);

			// We handle everything using the script element injection
			return undefined;
		}

		var requestDone = false;

		// Create the request object; Microsoft failed to properly
		// implement the XMLHttpRequest in IE7, so we use the ActiveXObject when it is available
		var xhr = window.ActiveXObject ? new ActiveXObject("Microsoft.XMLHTTP") : new XMLHttpRequest();

		// Open the socket
		// Passing null username, generates a login popup on Opera (#2865)
		if( s.username )
			xhr.open(type, s.url, s.async, s.username, s.password);
		else
			xhr.open(type, s.url, s.async);

		// Need an extra try/catch for cross domain requests in Firefox 3
		try {
			// Set the correct header, if data is being sent
			if ( s.data )
				xhr.setRequestHeader("Content-Type", s.contentType);

			// Set the If-Modified-Since header, if ifModified mode.
			if ( s.ifModified )
				xhr.setRequestHeader("If-Modified-Since",
					jQuery.lastModified[s.url] || "Thu, 01 Jan 1970 00:00:00 GMT" );

			// Set header so the called script knows that it's an XMLHttpRequest
			xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");

			// Set the Accepts header for the server, depending on the dataType
			xhr.setRequestHeader("Accept", s.dataType && s.accepts[ s.dataType ] ?
				s.accepts[ s.dataType ] + ", */*" :
				s.accepts._default );
		} catch(e){}

		// Allow custom headers/mimetypes
		if ( s.beforeSend && s.beforeSend(xhr, s) === false ) {
			// cleanup active request counter
			s.global && jQuery.active--;
			// close opended socket
			xhr.abort();
			return false;
		}

		if ( s.global )
			jQuery.event.trigger("ajaxSend", [xhr, s]);

		// Wait for a response to come back
		var onreadystatechange = function(isTimeout){
			// The transfer is complete and the data is available, or the request timed out
			if ( !requestDone && xhr && (xhr.readyState == 4 || isTimeout == "timeout") ) {
				requestDone = true;

				// clear poll interval
				if (ival) {
					clearInterval(ival);
					ival = null;
				}

				status = isTimeout == "timeout" && "timeout" ||
					!jQuery.httpSuccess( xhr ) && "error" ||
					s.ifModified && jQuery.httpNotModified( xhr, s.url ) && "notmodified" ||
					"success";

				if ( status == "success" ) {
					// Watch for, and catch, XML document parse errors
					try {
						// process the data (runs the xml through httpData regardless of callback)
						data = jQuery.httpData( xhr, s.dataType, s.dataFilter );
					} catch(e) {
						status = "parsererror";
					}
				}

				// Make sure that the request was successful or notmodified
				if ( status == "success" ) {
					// Cache Last-Modified header, if ifModified mode.
					var modRes;
					try {
						modRes = xhr.getResponseHeader("Last-Modified");
					} catch(e) {} // swallow exception thrown by FF if header is not available

					if ( s.ifModified && modRes )
						jQuery.lastModified[s.url] = modRes;

					// JSONP handles its own success callback
					if ( !jsonp )
						success();
				} else
					jQuery.handleError(s, xhr, status);

				// Fire the complete handlers
				complete();

				// Stop memory leaks
				if ( s.async )
					xhr = null;
			}
		};

		if ( s.async ) {
			// don't attach the handler to the request, just poll it instead
			var ival = setInterval(onreadystatechange, 13);

			// Timeout checker
			if ( s.timeout > 0 )
				setTimeout(function(){
					// Check to see if the request is still happening
					if ( xhr ) {
						// Cancel the request
						xhr.abort();

						if( !requestDone )
							onreadystatechange( "timeout" );
					}
				}, s.timeout);
		}

		// Send the data
		try {
			xhr.send(s.data);
		} catch(e) {
			jQuery.handleError(s, xhr, null, e);
		}

		// firefox 1.5 doesn't fire statechange for sync requests
		if ( !s.async )
			onreadystatechange();

		function success(){
			// If a local callback was specified, fire it and pass it the data
			if ( s.success )
				s.success( data, status );

			// Fire the global callback
			if ( s.global )
				jQuery.event.trigger( "ajaxSuccess", [xhr, s] );
		}

		function complete(){
			// Process result
			if ( s.complete )
				s.complete(xhr, status);

			// The request was completed
			if ( s.global )
				jQuery.event.trigger( "ajaxComplete", [xhr, s] );

			// Handle the global AJAX counter
			if ( s.global && ! --jQuery.active )
				jQuery.event.trigger( "ajaxStop" );
		}

		// return XMLHttpRequest to allow aborting the request etc.
		return xhr;
	},

	handleError: function( s, xhr, status, e ) {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />

		// If a local callback was specified, fire it
		if ( s.error ) s.error( xhr, status, e );

		// Fire the global callback
		if ( s.global )
			jQuery.event.trigger( "ajaxError", [xhr, s, e] );
	},

	// Counter for holding the number of active queries
	active: 0,

	// Determines if an XMLHttpRequest was successful or not
	httpSuccess: function( xhr ) {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		
		try {
			// IE error sometimes returns 1223 when it should be 204 so treat it as success, see #1450
			return !xhr.status && location.protocol == "file:" ||
				( xhr.status >= 200 && xhr.status < 300 ) || xhr.status == 304 || xhr.status == 1223 ||
				jQuery.browser.safari && xhr.status == undefined;
		} catch(e){}
		return false;
	},

	// Determines if an XMLHttpRequest returns NotModified
	httpNotModified: function( xhr, url ) {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		
		try {
			var xhrRes = xhr.getResponseHeader("Last-Modified");

			// Firefox always returns 200. check Last-Modified date
			return xhr.status == 304 || xhrRes == jQuery.lastModified[url] ||
				jQuery.browser.safari && xhr.status == undefined;
		} catch(e){}
		return false;
	},

	httpData: function( xhr, type, filter ) {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		
		var ct = xhr.getResponseHeader("content-type"),
			xml = type == "xml" || !type && ct && ct.indexOf("xml") >= 0,
			data = xml ? xhr.responseXML : xhr.responseText;

		if ( xml && data.documentElement.tagName == "parsererror" )
			throw "parsererror";
			
		// Allow a pre-filtering function to sanitize the response
		if( filter )
			data = filter( data, type );

		// If the type is "script", eval it in global context
		if ( type == "script" )
			jQuery.globalEval( data );

		// Get the JavaScript object, if JSON is used.
		if ( type == "json" )
			data = eval("(" + data + ")");

		return data;
	},

	// Serialize an array of form elements or a set of
	// key/values into a query string
	param: function( a ) {
		///	<summary>
		///		This method is internal.  Use serialize() instead.
		///	</summary>
		///	<param name="a" type="Map">A map of key/value pairs to serialize into a string.</param>'
		///	<returns type="String" />
		///	<private />
	
		var s = [];

		// If an array was passed in, assume that it is an array
		// of form elements
		if ( a.constructor == Array || a.jquery )
			// Serialize the form elements
			jQuery.each( a, function(){
				s.push( encodeURIComponent(this.name) + "=" + encodeURIComponent( this.value ) );
			});

		// Otherwise, assume that it's an object of key/value pairs
		else
			// Serialize the key/values
			for ( var j in a )
				// If the value is an array then the key names need to be repeated
				if ( a[j] && a[j].constructor == Array )
					jQuery.each( a[j], function(){
						s.push( encodeURIComponent(j) + "=" + encodeURIComponent( this ) );
					});
				else
					s.push( encodeURIComponent(j) + "=" + encodeURIComponent( jQuery.isFunction(a[j]) ? a[j]() : a[j] ) );

		// Return the resulting serialization
		return s.join("&").replace(/%20/g, "+");
	}

});
jQuery.fn.extend({
	show: function(speed,callback){
		///	<summary>
	    ///		以优雅的动画显示所有匹配的元素，并在显示完成后可选地触发一个回调函数。
		///	</summary>
		///	<param name="speed" type="String">三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</param>
	    ///	<param name="callback" optional="true" type="Function">在动画完成时执行的函数，每个元素执行一次。</param>
		///	<returns type="jQuery" />
		
		return speed ?
			this.animate({
				height: "show", width: "show", opacity: "show"
			}, speed, callback) :

			this.filter(":hidden").each(function(){
				this.style.display = this.oldblock || "";
				if ( jQuery.css(this,"display") == "none" ) {
					var elem = jQuery("<" + this.tagName + " />").appendTo("body");
					this.style.display = elem.css("display");
					// handle an edge condition where css is - div { display:none; } or similar
					if (this.style.display == "none")
						this.style.display = "block";
					elem.remove();
				}
			}).end();
	},

	hide: function(speed,callback){
		///	<summary>
	    ///		以优雅的动画隐藏所有匹配的元素，并在显示完成后可选地触发一个回调函数。
		///	</summary>
	    ///	<param name="speed" type="String">三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000) </param>
	    ///	<param name="callback" optional="true" type="Function">在动画完成时执行的函数，每个元素执行一次。</param>
		///	<returns type="jQuery" />
	
		return speed ?
			this.animate({
				height: "hide", width: "hide", opacity: "hide"
			}, speed, callback) :

			this.filter(":visible").each(function(){
				this.oldblock = this.oldblock || jQuery.css(this,"display");
				this.style.display = "none";
			}).end();
	},

	// Save the old toggle function
	_toggle: jQuery.fn.toggle,

	toggle: function( fn, fn2 ){
		///	<summary>
	    ///		切换元素的可见状态。 如果元素是可见的，切换为隐藏的；如果元素是隐藏的，切换为可见的。
		///	</summary>
		///	<returns type="jQuery" />
		
		return jQuery.isFunction(fn) && jQuery.isFunction(fn2) ?
			this._toggle.apply( this, arguments ) :
			fn ?
				this.animate({
					height: "toggle", width: "toggle", opacity: "toggle"
				}, fn, fn2) :
				this.each(function(){
					jQuery(this)[ jQuery(this).is(":hidden") ? "show" : "hide" ]();
				});
	},

	slideDown: function(speed,callback){
		///	<summary>
		///		通过高度变化（向下增大）来动态地显示所有匹配的元素，在显示完成后可选地触发一个回调函数。
        ///     这个动画效果只调整元素的高度，可以使匹配的元素以“滑动”的方式显示出来。
		///	</summary>
		///	<param name="speed" type="String">三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</param>
		///	<param name="callback" optional="true" type="Function">(可选) 在动画完成时执行的函数</param>
		///	<returns type="jQuery" />
		return this.animate({height: "show"}, speed, callback);
	},

	slideUp: function(speed,callback){
		///	<summary>
		///		通过高度变化（向上减小）来动态地隐藏所有匹配的元素，在隐藏完成后可选地触发一个回调函数。
        ///     这个动画效果只调整元素的高度，可以使匹配的元素以“滑动”的方式隐藏起来。
		///	</summary>
		///	<param name="speed" type="String">三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</param>
		///	<param name="callback" optional="true" type="Function">(可选) 在动画完成时执行的函数</param>
		///	<returns type="jQuery" />
		return this.animate({height: "hide"}, speed, callback);
	},

	slideToggle: function(speed, callback){
		///	<summary>
		///		通过高度变化来切换所有匹配元素的可见性，并在切换完成后可选地触发一个回调函数。
        ///     这个动画效果只调整元素的高度，可以使匹配的元素以“滑动”的方式隐藏或显示。
		///	</summary>
	    ///	<param name="speed" type="String">三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</param>
	    ///	<param name="callback" optional="true" type="Function">(可选) 在动画完成时执行的函数</param>
		///	<returns type="jQuery" />
		return this.animate({height: "toggle"}, speed, callback);
	},

	fadeIn: function(speed, callback){
		///	<summary>
		///		通过不透明度的变化来实现所有匹配元素的淡入效果，并在动画完成后可选地触发一个回调函数。
        ///     这个动画只调整元素的不透明度，也就是说所有匹配的元素的高度和宽度不会发生变化。
		///	</summary>
	    ///	<param name="speed" type="String">三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</param>
	    ///	<param name="callback" optional="true" type="Function">(可选) 在动画完成时执行的函数</param>
		///	<returns type="jQuery" />
		return this.animate({opacity: "show"}, speed, callback);
	},

	fadeOut: function(speed, callback){
		///	<summary>
		///		Fades out all matched elements by adjusting their opacity.
		///	</summary>
		///	<param name="speed" type="String">A string representing one of three predefined speeds ('slow', 'normal', or 'fast'), or
		///		the number of milliseconds to run the animation</param>
		///	<param name="callback" optional="true" type="Function">A function to be executed whenever the animation completes, once for each animated element.  It should map function callback() such that this is the DOM element being animated.</param>
		///	<returns type="jQuery" />
		return this.animate({opacity: "hide"}, speed, callback);
	},

	fadeTo: function(speed,to,callback){
		///	<summary>
		///		通过不透明度的变化来实现所有匹配元素的淡出效果，并在动画完成后可选地触发一个回调函数。
        ///     这个动画只调整元素的不透明度，也就是说所有匹配的元素的高度和宽度不会发生变化。
		///	</summary>
	    ///	<param name="speed" type="String">三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</param>
	    ///	<param name="callback" optional="true" type="Function">(可选) 在动画完成时执行的函数</param>
		///	<returns type="jQuery" />
		return this.animate({opacity: to}, speed, callback);
	},

	animate: function( prop, speed, easing, callback ) {
		///	<summary>
		///		用于创建自定义动画的函数。
        ///     这个函数的关键在于指定动画形式及结果样式属性对象。这个对象中每个属性都表示一个可以变化的样式属性（如“height”、“top”或“opacity”）。
        ///     注意：所有指定的属性必须用骆驼形式，比如用marginLeft代替margin-left. 
        ///     而每个属性的值表示这个样式属性到多少时动画结束。如果是一个数值，样式属性就会从当前的值渐变到指定的值。
        ///     如果使用的是“hide”、“show”或“toggle”这样的字符串值，则会为该属性调用默认的动画形式。
        ///     在 jQuery 1.2 中，你可以使用 em 和 % 单位。另外，在 jQuery 1.2 中，你可以通过在属性值前面指定 "+=" 或 "-=" 来让元素做相对运动。
		///	</summary>
	    ///	<param name="prop" type="Options">一组包含作为动画属性和终值的样式属性和及其值的集合</param>
		///	<param name="speed" optional="true" type="String">(可选) 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000)</param>
	    ///	<param name="easing" optional="true" type="String"> (可选) 要使用的擦除效果的名称(需要插件支持).默认jQuery提供"linear" 和 "swing".</param>
	    ///	<param name="callback" optional="true" type="Function"> (可选) 在动画完成时执行的函数</param>
		///	<returns type="jQuery" />

		var optall = jQuery.speed(speed, easing, callback);

		return this[ optall.queue === false ? "each" : "queue" ](function(){
			if ( this.nodeType != 1)
				return false;

			var opt = jQuery.extend({}, optall), p,
				hidden = jQuery(this).is(":hidden"), self = this;

			for ( p in prop ) {
				if ( prop[p] == "hide" && hidden || prop[p] == "show" && !hidden )
					return opt.complete.call(this);

				if ( p == "height" || p == "width" ) {
					// Store display property
					opt.display = jQuery.css(this, "display");

					// Make sure that nothing sneaks out
					opt.overflow = this.style.overflow;
				}
			}

			if ( opt.overflow != null )
				this.style.overflow = "hidden";

			opt.curAnim = jQuery.extend({}, prop);

			jQuery.each( prop, function(name, val){
				var e = new jQuery.fx( self, opt, name );

				if ( /toggle|show|hide/.test(val) )
					e[ val == "toggle" ? hidden ? "show" : "hide" : val ]( prop );
				else {
					var parts = val.toString().match(/^([+-]=)?([\d+-.]+)(.*)$/),
						start = e.cur(true) || 0;

					if ( parts ) {
						var end = parseFloat(parts[2]),
							unit = parts[3] || "px";

						// We need to compute starting value
						if ( unit != "px" ) {
							self.style[ name ] = (end || 1) + unit;
							start = ((end || 1) / e.cur(true)) * start;
							self.style[ name ] = start + unit;
						}

						// If a +=/-= token was provided, we're doing a relative animation
						if ( parts[1] )
							end = ((parts[1] == "-=" ? -1 : 1) * end) + start;

						e.custom( start, end, unit );
					} else
						e.custom( start, val, "" );
				}
			});

			// For JS strict compliance
			return true;
		});
	},

	queue: function(type, fn){
		///	<summary>
	    ///		1: queue() - 返回指向第一个匹配元素的队列(将是一个函数数组)
	    ///		2: queue(callback) - 在匹配的元素的动画队列中添加一个函数
	    ///		3: queue(queue) - 将匹配元素的动画队列用新的一个队列来代替(函数数组).
		///	</summary>
		///	<param name="type" type="Function">要添加进队列的函数</param>
		///	<returns type="jQuery" />
		if ( jQuery.isFunction(type) || ( type && type.constructor == Array )) {
			fn = type;
			type = "fx";
		}

		if ( !type || (typeof type == "string" && !fn) )
			return queue( this[0], type );

		return this.each(function(){
			if ( fn.constructor == Array )
				queue(this, type, fn);
			else {
				queue(this, type).push( fn );

				if ( queue(this, type).length == 1 )
					fn.call(this);
			}
		});
	},

	stop: function(clearQueue, gotoEnd){
		///	<summary>
		///		停止所有在指定元素上正在运行的动画。如果队列中有等待执行的动画，他们将被马上执行
		///	</summary>
		///	<param name="clearQueue" optional="true" type="Boolean">True就清楚所有被停止的动画</param>
		///	<param name="gotoEnd" optional="true" type="Boolean">True就把元素的值放到动画的结尾</param>
		///	<returns type="jQuery" />
		
		var timers = jQuery.timers;

		if (clearQueue)
			this.queue([]);

		this.each(function(){
			// go in reverse order so anything added to the queue during the loop is ignored
			for ( var i = timers.length - 1; i >= 0; i-- )
				if ( timers[i].elem == this ) {
					if (gotoEnd)
						// force the next step to be the last
						timers[i](true);
					timers.splice(i, 1);
				}
		});

		// start the next in the queue if the last step wasn't forced
		if (!gotoEnd)
			this.dequeue();

		return this;
	}

});

var queue = function( elem, type, array ) {
	///	<summary>
	///		This member is internal.
	///	</summary>
	///	<private />

	if ( elem ){

		type = type || "fx";

		var q = jQuery.data( elem, type + "queue" );

		if ( !q || array )
			q = jQuery.data( elem, type + "queue", jQuery.makeArray(array) );

	}
	return q;
};

jQuery.fn.dequeue = function(type){
	///	<summary>
    ///		从动画队列中移除一个队列函数.
	///	</summary>
	///	<param name="type" type="String" optional="true">进入队列的类型</param>
	///	<returns type="jQuery" />
	
	type = type || "fx";

	return this.each(function(){
		var q = queue(this, type);

		q.shift();

		if ( q.length )
			q[0].call( this );
	});
};

jQuery.extend({

	speed: function(speed, easing, fn) {
		///	<summary>
		///		This member is internal.
		///	</summary>
		///	<private />
		var opt = speed && speed.constructor == Object ? speed : {
			complete: fn || !fn && easing ||
				jQuery.isFunction( speed ) && speed,
			duration: speed,
			easing: fn && easing || easing && easing.constructor != Function && easing
		};

		opt.duration = (opt.duration && opt.duration.constructor == Number ?
			opt.duration :
			jQuery.fx.speeds[opt.duration]) || jQuery.fx.speeds.def;

		// Queueing
		opt.old = opt.complete;
		opt.complete = function(){
			if ( opt.queue !== false )
				jQuery(this).dequeue();
			if ( jQuery.isFunction( opt.old ) )
				opt.old.call( this );
		};

		return opt;
	},

	easing: {
		linear: function( p, n, firstNum, diff ) {
   		///	<summary>
   		///		This member is internal.
			///	</summary>
   		///	<private />
			return firstNum + diff * p;
		},
		swing: function( p, n, firstNum, diff ) {
   		///	<summary>
   		///		This member is internal.
			///	</summary>
   		///	<private />
			return ((-Math.cos(p*Math.PI)/2) + 0.5) * diff + firstNum;
		}
	},

	timers: [],
	timerId: null,

	fx: function( elem, options, prop ){
		///	<summary>
		///		This member is internal.
		///	</summary>
		///	<private />
		this.options = options;
		this.elem = elem;
		this.prop = prop;

		if ( !options.orig )
			options.orig = {};
	}

});

jQuery.fx.prototype = {

	// Simple function for setting a style value
	update: function(){
		///	<summary>
		///		This member is internal.
		///	</summary>
		///	<private />
		if ( this.options.step )
			this.options.step.call( this.elem, this.now, this );

		(jQuery.fx.step[this.prop] || jQuery.fx.step._default)( this );

		// Set display property to block for height/width animations
		if ( this.prop == "height" || this.prop == "width" )
			this.elem.style.display = "block";
	},

	// Get the current size
	cur: function(force){
		///	<summary>
		///		This member is internal.
		///	</summary>
		///	<private />
		if ( this.elem[this.prop] != null && this.elem.style[this.prop] == null )
			return this.elem[ this.prop ];

		var r = parseFloat(jQuery.css(this.elem, this.prop, force));
		return r && r > -10000 ? r : parseFloat(jQuery.curCSS(this.elem, this.prop)) || 0;
	},

	// Start an animation from one number to another
	custom: function(from, to, unit){
		///	<summary>
		///		This member is internal.
		///	</summary>
		///	<private />
		this.startTime = now();
		this.start = from;
		this.end = to;
		this.unit = unit || this.unit || "px";
		this.now = this.start;
		this.pos = this.state = 0;
		this.update();

		var self = this;
		function t(gotoEnd){
			return self.step(gotoEnd);
		}

		t.elem = this.elem;

		jQuery.timers.push(t);

		if ( jQuery.timerId == null ) {
			jQuery.timerId = setInterval(function(){
				var timers = jQuery.timers;

				for ( var i = 0; i < timers.length; i++ )
					if ( !timers[i]() )
						timers.splice(i--, 1);

				if ( !timers.length ) {
					clearInterval( jQuery.timerId );
					jQuery.timerId = null;
				}
			}, 13);
		}
	},

	// Simple 'show' function
	show: function(){
		///	<summary>
		///		显示隐藏的匹配元素。这个就是 'show( speed, [callback] )' 无动画的版本。如果选择的元素是可见的，这个方法将不会改变任何东西。
		///     无论这个元素是通过hide()方法隐藏的还是在CSS里设置了display:none;，这个方法都将有效。
		///	</summary>
		// Remember where we started, so that we can go back to it later
		this.options.orig[this.prop] = jQuery.attr( this.elem.style, this.prop );
		this.options.show = true;

		// Begin the animation
		this.custom(0, this.cur());

		// Make sure that we start at a small width/height to avoid any
		// flash of content
		if ( this.prop == "width" || this.prop == "height" )
			this.elem.style[this.prop] = "1px";

		// Start by showing the element
		jQuery(this.elem).show();
	},

	// Simple 'hide' function
	hide: function(){
		///	<summary>
		///		隐藏显示的元素这个就是 'hide( speed, [callback] )' 的无动画版。如果选择的元素是隐藏的，这个方法将不会改变任何东西。
		///	</summary>

		// Remember where we started, so that we can go back to it later
		this.options.orig[this.prop] = jQuery.attr( this.elem.style, this.prop );
		this.options.hide = true;

		// Begin the animation
		this.custom(this.cur(), 0);
	},

	// Each step of an animation
	step: function(gotoEnd){
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		var t = now();

		if ( gotoEnd || t > this.options.duration + this.startTime ) {
			this.now = this.end;
			this.pos = this.state = 1;
			this.update();

			this.options.curAnim[ this.prop ] = true;

			var done = true;
			for ( var i in this.options.curAnim )
				if ( this.options.curAnim[i] !== true )
					done = false;

			if ( done ) {
				if ( this.options.display != null ) {
					// Reset the overflow
					this.elem.style.overflow = this.options.overflow;

					// Reset the display
					this.elem.style.display = this.options.display;
					if ( jQuery.css(this.elem, "display") == "none" )
						this.elem.style.display = "block";
				}

				// Hide the element if the "hide" operation was done
				if ( this.options.hide )
					this.elem.style.display = "none";

				// Reset the properties, if the item has been hidden or shown
				if ( this.options.hide || this.options.show )
					for ( var p in this.options.curAnim )
						jQuery.attr(this.elem.style, p, this.options.orig[p]);
			}

			if ( done )
				// Execute the complete function
				this.options.complete.call( this.elem );

			return false;
		} else {
			var n = t - this.startTime;
			this.state = n / this.options.duration;

			// Perform the easing function, defaults to swing
			this.pos = jQuery.easing[this.options.easing || (jQuery.easing.swing ? "swing" : "linear")](this.state, n, 0, 1, this.options.duration);
			this.now = this.start + ((this.end - this.start) * this.pos);

			// Perform the next step of the animation
			this.update();
		}

		return true;
	}

};

jQuery.extend( jQuery.fx, {
	speeds:{
		slow: 600,
 		fast: 200,
 		// Default speed
 		def: 400
	},
	step: {
		scrollLeft: function(fx){
   		///	<summary>
   		///		This method is internal.
			///	</summary>
   		///	<private />

			fx.elem.scrollLeft = fx.now;
		},

		scrollTop: function(fx){
			///	<summary>
			///		This method is internal.
			///	</summary>
			///	<private />
			fx.elem.scrollTop = fx.now;
		},

		opacity: function(fx){
			///	<summary>
			///		This method is internal.
			///	</summary>
			///	<private />
			jQuery.attr(fx.elem.style, "opacity", fx.now);
		},

		_default: function(fx) {
			///	<summary>
			///		This method is internal.
			///	</summary>
			///	<private />
			fx.elem.style[ fx.prop ] = fx.now + fx.unit;
		}
	}
});
// The Offset Method
// Originally By Brandon Aaron, part of the Dimension Plugin
// http://jquery.com/plugins/project/dimensions
jQuery.fn.offset = function() {
	///	<summary>
    ///		获取匹配元素在当前视口的相对偏移。
	///	</summary>
    ///	<returns type="Object">返回的对象包含两个整形属性：top 和 left。此方法只对可见元素有效。</returns>

	var left = 0, top = 0, elem = this[0], results;

	if ( elem ) with ( jQuery.browser ) {
		var parent	   = elem.parentNode,
			offsetChild  = elem,
			offsetParent = elem.offsetParent,
			doc		  = elem.ownerDocument,
			safari2	  = safari && parseInt(version) < 522 && !/adobeair/i.test(userAgent),
			css		  = jQuery.curCSS,
			fixed		= css(elem, "position") == "fixed";

		// Use getBoundingClientRect if available
		if ( elem.getBoundingClientRect ) {
			var box = elem.getBoundingClientRect();

			// Add the document scroll offsets
			add(box.left + Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft),
				box.top  + Math.max(doc.documentElement.scrollTop,  doc.body.scrollTop));

			// IE adds the HTML element's border, by default it is medium which is 2px
			// IE 6 and 7 quirks mode the border width is overwritable by the following css html { border: 0; }
			// IE 7 standards mode, the border is always 2px
			// This border/offset is typically represented by the clientLeft and clientTop properties
			// However, in IE6 and 7 quirks mode the clientLeft and clientTop properties are not updated when overwriting it via CSS
			// Therefore this method will be off by 2px in IE while in quirksmode
			add( -doc.documentElement.clientLeft, -doc.documentElement.clientTop );

		// Otherwise loop through the offsetParents and parentNodes
		} else {

			// Initial element offsets
			add( elem.offsetLeft, elem.offsetTop );

			// Get parent offsets
			while ( offsetParent ) {
				// Add offsetParent offsets
				add( offsetParent.offsetLeft, offsetParent.offsetTop );

				// Mozilla and Safari > 2 does not include the border on offset parents
				// However Mozilla adds the border for table or table cells
				if ( mozilla && !/^t(able|d|h)$/i.test(offsetParent.tagName) || safari && !safari2 )
					border( offsetParent );

				// Add the document scroll offsets if position is fixed on any offsetParent
				if ( !fixed && css(offsetParent, "position") == "fixed" )
					fixed = true;

				// Set offsetChild to previous offsetParent unless it is the body element
				offsetChild  = /^body$/i.test(offsetParent.tagName) ? offsetChild : offsetParent;
				// Get next offsetParent
				offsetParent = offsetParent.offsetParent;
			}

			// Get parent scroll offsets
			while ( parent && parent.tagName && !/^body|html$/i.test(parent.tagName) ) {
				// Remove parent scroll UNLESS that parent is inline or a table to work around Opera inline/table scrollLeft/Top bug
				if ( !/^inline|table.*$/i.test(css(parent, "display")) )
					// Subtract parent scroll offsets
					add( -parent.scrollLeft, -parent.scrollTop );

				// Mozilla does not add the border for a parent that has overflow != visible
				if ( mozilla && css(parent, "overflow") != "visible" )
					border( parent );

				// Get next parent
				parent = parent.parentNode;
			}

			// Safari <= 2 doubles body offsets with a fixed position element/offsetParent or absolutely positioned offsetChild
			// Mozilla doubles body offsets with a non-absolutely positioned offsetChild
			if ( (safari2 && (fixed || css(offsetChild, "position") == "absolute")) ||
				(mozilla && css(offsetChild, "position") != "absolute") )
					add( -doc.body.offsetLeft, -doc.body.offsetTop );

			// Add the document scroll offsets if position is fixed
			if ( fixed )
				add(Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft),
					Math.max(doc.documentElement.scrollTop,  doc.body.scrollTop));
		}

		// Return an object with top and left properties
		results = { top: top, left: left };
	}

	function border(elem) {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		add( jQuery.curCSS(elem, "borderLeftWidth", true), jQuery.curCSS(elem, "borderTopWidth", true) );
	}

	function add(l, t) {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		left += parseInt(l, 10) || 0;
		top += parseInt(t, 10) || 0;
	}

	return results;
};


jQuery.fn.extend({
	position: function() {
		///	<summary>
	    ///		得到当前元素相对于其offsetParent的offset值
		///	</summary>
		///	<returns type="Object">有 'top'和'left'值的一个对象.</returns>
		var left = 0, top = 0, results;

		if ( this[0] ) {
			// Get *real* offsetParent
			var offsetParent = this.offsetParent(),

			// Get correct offsets
			offset	   = this.offset(),
			parentOffset = /^body|html$/i.test(offsetParent[0].tagName) ? { top: 0, left: 0 } : offsetParent.offset();

			// Subtract element margins
			// note: when an element has margin: auto the offsetLeft and marginLeft 
			// are the same in Safari causing offset.left to incorrectly be 0
			offset.top  -= num( this, 'marginTop' );
			offset.left -= num( this, 'marginLeft' );

			// Add offsetParent borders
			parentOffset.top  += num( offsetParent, 'borderTopWidth' );
			parentOffset.left += num( offsetParent, 'borderLeftWidth' );

			// Subtract the two offsets
			results = {
				top:  offset.top  - parentOffset.top,
				left: offset.left - parentOffset.left
			};
		}

		return results;
	},

	offsetParent: function() {
		///	<summary>
		///		This method is internal.
		///	</summary>
		///	<private />
		var offsetParent = this[0].offsetParent;
		while ( offsetParent && (!/^body|html$/i.test(offsetParent.tagName) && jQuery.css(offsetParent, 'position') == 'static') )
			offsetParent = offsetParent.offsetParent;
		return jQuery(offsetParent);
	}
});


// Create scrollLeft and scrollTop methods
jQuery.each( ['Left' ], function(i, name) {
	var method = 'scroll' + name;
	
	jQuery.fn[ method ] = function(val) {
		///	<summary>
		///		得到并可以选择设置第一个匹配元素的滚动条的 left offset。
		///	</summary>
		///	<param name="val" type="Number" integer="true" optional="true">想要滚动条left offset的数值</param>
		///	<returns type="Number" integer="true">第一个匹配元素滚动条的left offset值</returns>
		if (!this[0]) return;

		return val != undefined ?

			// Set the scroll offset
			this.each(function() {
				this == window || this == document ?
					window.scrollTo(
						!i ? val : jQuery(window).scrollLeft(),
						 i ? val : jQuery(window).scrollTop()
					) :
					this[ method ] = val;
			}) :

			// Return the scroll offset
			this[0] == window || this[0] == document ?
				self[ i ? 'pageYOffset' : 'pageXOffset' ] ||
					jQuery.boxModel && document.documentElement[ method ] ||
					document.body[ method ] :
				this[0][ method ];
	};
});
// Create scrollLeft and scrollTop methods
jQuery.each( [ 'Top'], function(i, name) {
	var method = 'scroll' + name;
	
	jQuery.fn[ method ] = function(val) {
		///	<summary>
	    ///		得到并可以选择设置第一个匹配元素的滚动条的 top offset。
		///	</summary>
	    ///	<param name="val" type="Number" integer="true" optional="true">想要滚动条left offset的数值</param>
	    ///	<returns type="Number" integer="true">第一个匹配元素滚动条的left offset值</returns>
		if (!this[0]) return;

		return val != undefined ?

			// Set the scroll offset
			this.each(function() {
				this == window || this == document ?
					window.scrollTo(
						!i ? val : jQuery(window).scrollLeft(),
						 i ? val : jQuery(window).scrollTop()
					) :
					this[ method ] = val;
			}) :

			// Return the scroll offset
			this[0] == window || this[0] == document ?
				self[ i ? 'pageYOffset' : 'pageXOffset' ] ||
					jQuery.boxModel && document.documentElement[ method ] ||
					document.body[ method ] :
				this[0][ method ];
	};
});

// Create innerHeight, innerWidth, outerHeight and outerWidth methods
jQuery.each([ "Height" ], function(i, name){

	var tl = i ? "Left"  : "Top",  // top or left
		br = i ? "Right" : "Bottom"; // bottom or right

	// innerHeight and innerWidth
	jQuery.fn["inner" + name] = function(){
		///	<summary>
	    ///     得到第一个匹配元素的inner height，不包括边框，包括padding
		///	</summary>
		///	<returns type="Number" integer="true">第一个匹配元素的outer height</returns>
		return this[ name.toLowerCase() ]() +
			num(this, "padding" + tl) +
			num(this, "padding" + br);
	};

	// outerHeight and outerWidth
	jQuery.fn["outer" + name] = function(margin) {
		///	<summary>
		///     得到第一个匹配元素的outer height，默认包括边框和padding
		///	</summary>
		///	<param name="margins" type="Map">一组指定选择的key/value(名值对)</param>
	    ///	<returns type="Number" integer="true">第一个匹配元素的outer height</returns>
		return this["inner" + name]() +
			num(this, "border" + tl + "Width") +
			num(this, "border" + br + "Width") +
			(margin ?
				num(this, "margin" + tl) + num(this, "margin" + br) : 0);
	};
});

// Create innerHeight, innerWidth, outerHeight and outerWidth methods
jQuery.each( [ "Width" ], function(i, name) {

	var tl = i ? "Left"  : "Top",  // top or left
		br = i ? "Right" : "Bottom"; // bottom or right

	// innerHeight and innerWidth
	jQuery.fn["inner" + name] = function(){
		///	<summary>
	    ///		得到第一个匹配元素的inner width，不包括边框，包括padding
		///	</summary>
	    ///	<returns type="Number" integer="true">第一个匹配元素的outer width</returns>

		return this[ name.toLowerCase() ]() +
			num(this, "padding" + tl) +
			num(this, "padding" + br);
	};

	// outerHeight and outerWidth
	jQuery.fn["outer" + name] = function(margin) {
		///	<summary>
	    ///		得到第一个匹配元素的outer width，默认包括边框和padding
		///	</summary>
	    ///	<param name="margins" type="Map">一组指定选择的key/value(名值对)</param>
	    ///	<returns type="Number" integer="true">第一个匹配元素的outer width</returns>
		return this["inner" + name]() +
			num(this, "border" + tl + "Width") +
			num(this, "border" + br + "Width") +
			(margin ?
				num(this, "margin" + tl) + num(this, "margin" + br) : 0);
	};

});})();
