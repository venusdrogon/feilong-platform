# Automatic Release notes plugin & website using GitHub milestones

Lets be real, creating release notes by hand is tedious, this jQuery plugin creates release notes automatically from your github milestones repository.  It comes with a nice onepage layout, but you can also integrate it in any website. It also integrates issues, labels & comments


![Screenshot](http://www.position-absolute.com/big_ad.png)

It's a nice middleground where you can show what your team is doing without having to give github access to all your company. You can see an implemented  [example here](http://release.weddingdeck.com).


* Since this plugin uses ajax functionalities, to view the demo your code needs to be on a web server.
** The documentation is also available online at : http://posabsolute.github.com/releasenotes/

*** If you use this plugin in production please consider *[buying me a good pint of beer!!](http://www.position-absolute.com/?p=3992)*
Donate button is at the bottom of the page

[![endorse](http://api.coderwall.com/posabsolute/endorsecount.png)](http://coderwall.com/posabsolute)



## Options

By default your last 10 milestones will be shown. It uses jsonp for fetching the data from github, this is perfect for public repos.

		$(document).ready(function() {
			$("#releaseNotesContainer").releaseNotes({
				milestonesShown			: 10,
				// If you want to show private repo
				// You need to add repo credentials in api.php
				phpApi				: true,
					phpApiPath		: '/',
				showDescription			: true,
				showComments			: true,
				// Used if phpAPI is false
	     			repo					: 'rails',
	     			username				: 'rails'
			});
		});

### milestonesShown (int)

The number of milestone the plugin will show.

### phpApi (boolean)

If set to true, the release notes will be fetch in php, perfect for private repos.

### phpApiPath (string) (required only if phpApi is set to true)

Path to the api.php file, without the good path and the phpApi option active the plugin will fail.

### showDescription (boolean)

Show an issue complete description when you click on it.

### showComments (boolean)

When an issue description is shown, if this issue has comments, a Show Comments button will appear enabling the users to see this issue comments.

### repo (string)

Your github repo name, the plugin will fetch data from this repository.

### username (string)

The github username that is attached to your repo.



## Integration in other websites

You want to integrate this plugin in your website, no problem. First you need to add to your document head a couple of js and one css file. This plugin has 2 javascript dependencies, marked.js and jQuery.

Include Depencies & the plugin

	<script src="jquery/1.4.4/jquery.js" type="text/javascript"></script>
	<script src="js/libs/marked.js"></script>
	<script src="js/releasenotes.js"></script>

Next include the plugin stylesheet

	<link rel="stylesheet" href="css/releasenotes.plugin.css">

Finally extenciate and define your options in the document.ready. 

	<script>
		$(document).ready(function() {
			$("#releaseNotesContainer").releaseNotes({
				milestonesShown			: 10,
				// If you want to show private repo
				// You need to add repo credentials in api.php
				phpApi				: true,
					phpApiPath		: '/',
				showDescription			: true,
				showComments			: true,
				// Used if phpAPI is false
	     			repo					: 'rails',
	     			username				: 'rails'
			});
		});
	</script>

In this example releaseNotesContainer is the div id where the plugin will be created.

## Private Repository

To show release notes from a private repo, first you need to set your phpApi option to true, and to set the good phpApiPath path.

Next, open api.php, there is a variable $config at the top where you will need to put your github credentials.

	$configs = array(
	    "username" => "username",  // Github user
	    "password" => "password",	// Github user password
	    "repo"     => "repo"	// Github Repositoty
	);

Since we use a bridge to get the github data, your credentials will never be exposed to your users.


## Limitations

The plugin works on 
Firefox 3.6+,
Safari,
Chrome,
IE7+


## Licence

Copyright (c) 2012 Cedric Dugas, http://www.position-absolute.com/

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.