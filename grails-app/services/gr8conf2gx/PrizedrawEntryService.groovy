package gr8conf2gx

import grails.events.Listener
import org.grails.mandrill.MandrillMessage
import org.grails.mandrill.MandrillRecipient
import org.grails.plugin.platform.events.publisher.EventsPublisher

class PrizedrawEntryService {

	def mandrillService

	boolean isDoubleEntry(String url) {
		def competition = PrizedrawEntry.findByUrl(url)
		log.debug("isDoubleEntry: ${competition ? 'yes' : 'no'}")

		return competition ? true : false
	}


	def saveEntry(String url, String name, String email) {
		def entry = new PrizedrawEntry(url: url, name: name, email: email).save(failOnError: true, flush: true)
		log.debug("Save entry: $entry")
		def sendMailData = [name: entry.name, email: entry.email]
		event('sendConfirmMail', sendMailData,[(EventsPublisher.FORK): true])
		return entry
	}

	@Listener(topic = 'afterInsert', namespace = "gorm")
	public void entrySaved(PrizedrawEntry entry) {
		def data = [latest: new Date()]

		log.debug("Notify client: $data")
		event('notifyClient', data,[(EventsPublisher.FORK): true])
	}

	@Listener(topic = 'sendConfirmMail')
	public void sendConfirmMail(def data) {
		def recpts = []
		recpts.add(new MandrillRecipient(name:data.name, email:data.email))
		def message = new MandrillMessage(
		                                  text:"""\
Hi.

Thank you for participating in the GR8Conf Europe 2013 prizedraw at Springone2GX.

We will get back to you, if you win the prize.

Best regards

The GR8Conf Crew
		                                  """,
		                                  subject:"Springone2GX GR8Conf prizedraw",
		                                  from_email:"info@gr8conf.org",
		                                  to:recpts)
		def ret = mandrillService.send(message)
		log.debug ret

	}

}
