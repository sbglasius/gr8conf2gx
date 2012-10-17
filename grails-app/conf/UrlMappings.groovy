class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: 'captureQRCode', action:"index")
		"500"(view:'/error')
	}
}
