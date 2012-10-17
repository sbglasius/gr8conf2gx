package gr8conf2gx

import org.springframework.web.multipart.commons.CommonsMultipartFile

class CaptureQRCodeController {
	def analyzeQRCodeService
	def skanzWebsiteService
	def prizedrawEntryService

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
			String url = results.first().text
			if(!prizedrawEntryService.isDoubleEntry(url)) {
				(name, email) = skanzWebsiteService.extractUsernamePassword(url.toURL())
				if(name && email) {
					log.debug("Resolved $name, $email")
					prizedrawEntryService.saveEntry(url, name, email)
					render(template: 'found', model: [name: name, email: email])
				} else {
					render(template: 'common', model: [status: EntryStatus.NAME_OR_EMAIL_MISSING])
				}
			} else {
				log.debug("This is a double entry")
				render(template: 'common', model: [status: EntryStatus.DOUBLE_ENTRY])
			}
		} else {
			render(template: 'common', model: [status: EntryStatus.QR_CODE_UNREADABLE])
		}
	}
}
