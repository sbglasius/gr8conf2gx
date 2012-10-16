import gr8conf2gx.AnalyzeQRCodeService
import com.google.zxing.Result
import gr8conf2gx.SkanzWebsiteService

new File('/Users/sbg/Desktop/1350354309763.jpg').toURL().withInputStream {
	def qrCodeService = new AnalyzeQRCodeService()
	def skanzWebsiteService = new SkanzWebsiteService()
	Result[] multi = qrCodeService.decodeMulti(it, [:])
	if(multi) {
		println skanzWebsiteService.extractUsernamePassword(multi[0].text.toURL())

	}
}

