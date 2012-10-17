modules = {
	application {
		resource url: 'js/application.js'
	}

	'less' {
		resource url: [dir: 'less', file: 'main.less'], attrs: [rel: "stylesheet/less", type: 'css']
	}
}
