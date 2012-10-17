package gr8conf2gx

class PrizedrawEntry {
	String url
	String name
	String email

	Date dateCreated

	static constraints = {
		url blank:  false
		name blank: false
		email blank: false, email: true
	}
}
