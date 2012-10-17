package gr8conf2gx

import grails.events.Listener

class PrizedrawEntryService {

	boolean isDoubleEntry(String url) {
		def competition = PrizedrawEntry.findByUrl(url)
		log.debug("isDoubleEntry: ${competition ? 'yes' : 'no'}")

		return competition ? true : false
	}


	def saveEntry(String url, String name, String email) {
		def entry = new PrizedrawEntry(url: url, name: name, email: email).save(failOnError: true, flush: true)
		log.debug("Save entry: $entry")
		return entry
	}

	//will receive client events from 'saveTodo' topic
	@Listener(topic = 'afterInsert', namespace = "gorm")
	entrySaved(PrizedrawEntry entry) {
		def data = [latest: new Date()]
		log.debug("Notify client: $data")
		event('notifyClient', data) // will trigger registered browsers on 'savedTodo' topic
	}
}
