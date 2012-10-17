package gr8conf2gx

import org.springframework.web.multipart.commons.CommonsMultipartFile

class CaptureQRCodeController {
	def analyzeQRCodeService
	def skanzWebsiteService


	def index() {
		log.debug request.getHeader('user-agent')
		def deviceInfo = request.getAttribute("currentDevice")
		log.debug "${deviceInfo.mobile} ${deviceInfo.id}"
		def view = "index"
		withMobileDevice {def device ->
			log.debug("Render for mobile")
			view = "index-mob"
		}
		render(view: view)
	}


	def analyzeAjax() {
		def file = (CommonsMultipartFile) params.file
		log.debug("Filename uploaded: ${file.originalFilename}")

		def results = analyzeQRCodeService?.decodeMulti(file.inputStream)
		def name = null, email = null
		if(results) {
			(name, email) = skanzWebsiteService.extractUsernamePassword(results.first().text.toURL())
			log.debug("Resolved $name, $email")
			render( template: 'found', model: [name: name, email: email]  )
		} else {
			render( template: 'common', model: [notFound: true])

		}


	}
}
