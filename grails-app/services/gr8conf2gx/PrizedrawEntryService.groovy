package gr8conf2gx

class PrizedrawEntryService {

	boolean isDoubleEntry(String url) {
		def competition = PrizedrawEntry.findByUrl(url)
		log.debug("isDoubleEntry: ${competition ? 'yes' : 'no'}")

		return competition ? true : false
	}


	def saveEntry(String url, String name, String email) {
		def entry = new PrizedrawEntry(url: url, name: name, email: email).save(failOnError: true)
		log.debug("Save entry: $entry")
		return entry
	}
}
