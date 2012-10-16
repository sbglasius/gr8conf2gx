package gr8conf2gx

import org.springframework.web.multipart.commons.CommonsMultipartFile

class CaptureQTRCodeController {
	def analyzeQRCodeService
	def skanzWebsiteService


	def index() { }


	def analyze() {
		def file = (CommonsMultipartFile) params.file
		def view="index"
		log.debug("Filename uploaded: ${file.originalFilename}")

		def results = analyzeQRCodeService?.decodeMulti(file.inputStream)
		def name = null, email = null
		if(results) {
			(name, email) = skanzWebsiteService.extractUsernamePassword(results.first().text.toURL())
			log.debug("Resolved $name, $email")
		}
		withMobileDevice {
	        view = "index-mob"
	  }

		render(view: view, model: [qrcode: results ? results[0].text : '', name: name, email: email])
	}
}
