package gr8conf2gx

import groovy.xml.StreamingMarkupBuilder
import groovy.xml.XmlUtil


class SkanzWebsiteService {

	def extractUsernamePassword(URL url) {

			URL iframeUrl = findIframe(findRealUrl(url))
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
			if(conn.headerFields.'Location') {
				return findRealUrl(conn.headerFields.Location.first().toURL())
			} else {
				throw new RuntimeException('Failed to follow redirect')
			}
		}
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

		def name = contact.div[1].div.div[1].h1.text()?.trim()
		def email = contact.div[1].div.div[3].div[0].a.@href.text()
		if(email) {
			email = email -'mailto:'
		}

		return [name, email]
	}


	private parseHtml(URL url) {
		def text = url.getText('UTF-8')
		new XmlSlurper(new org.ccil.cowan.tagsoup.Parser()).parseText(text)
	}

	private prettyPrint(node) {
     XmlUtil.serialize(new StreamingMarkupBuilder().bind { mkp.yield node })
 }

}
