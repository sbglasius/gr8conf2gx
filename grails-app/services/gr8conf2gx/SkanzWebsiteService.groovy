package gr8conf2gx

import groovy.xml.StreamingMarkupBuilder
import groovy.xml.XmlUtil


class SkanzWebsiteService {

	def extractUsernamePassword(URL url) {
		URL iframeUrl = findIframe(findRealUrl(url))
		log.debug("Found iframe: $iframeUrl")
		if(iframeUrl) {
			return findNameAndEmail(iframeUrl)
		}
		return []
	}


	private URL findRealUrl(URL url) {
		HttpURLConnection conn = url.openConnection() as HttpURLConnection
		conn.followRedirects = false
		conn.requestMethod = 'HEAD'
		if(conn.responseCode in [301, 302]) {
			log.debug("resolving redirect url: $url")
			if(conn.headerFields.'Location') {
				return findRealUrl(conn.headerFields.Location.first().toURL())
			} else {
				throw new RuntimeException('Failed to follow redirect')
			}
		}
		log.debug("was a real url: $url")
		return url
	}


	private findIframe(URL url) {
		url = findRealUrl(url)
		def html = parseHtml(url)
		def baseurl = url.toString() - url.path - url.query - '?'
		def src = html.'**'.find { "iframe".equalsIgnoreCase(it.name()) && it.@id == 'myskanzframe'}.@src
		return new URL(baseurl + src)
	}


	private findNameAndEmail(URL url) {
		def html = parseHtml(url)
		def contact = html.'**'.find { it.@id == 'contact'}

		def name = findName(contact)
		def email = findEmail(contact)

		return [name, email]
	}


	private String findName(contact) {
		contact.'**'.find { it.name() == 'h1'}.text()?.trim()
	}


	private String findEmail(contact) {
		def email = contact.'**'.findAll { it.name() == 'a' }.find { it.@href.text().startsWith('mailto')}?.@href?.text()
		if(email) {
			return email - 'mailto:'
		}
	}


	private parseHtml(URL url) {
		def text = url.getText('UTF-8')
		new XmlSlurper(new org.ccil.cowan.tagsoup.Parser()).parseText(text)
	}


	private prettyPrint(node) {
		XmlUtil.serialize(new StreamingMarkupBuilder().bind { mkp.yield node })
	}
}
