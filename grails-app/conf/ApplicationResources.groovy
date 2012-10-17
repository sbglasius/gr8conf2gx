modules = {
	application {
		resource url: 'js/application.js'
	}
	font {
		resource url: [dir: 'css', file: 'base64_quicksandfont.css']
	}

	'less' {
		dependsOn 'font'
		resource url: [dir: 'less', file: 'main.less'], attrs: [rel: "stylesheet/less", type: 'css']
	}
}
