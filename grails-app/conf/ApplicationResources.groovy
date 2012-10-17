modules = {
	application {
		dependsOn 'jquery','jquery-ajaxfileupload'
		resource url: 'js/application.js'
	}
	'jquery-ajaxfileupload' {
		dependsOn 'jquery'
		resource url: [dir: 'js', file: 'jquery.ajaxfileupload.js']
	}
	font {
		resource url: [dir: 'css', file: 'base64_quicksandfont.css']
	}

	'less' {
		dependsOn 'font'
		resource url: [dir: 'less', file: 'main.less'], attrs: [rel: "stylesheet/less", type: 'css']
	}
}
