package no.steria.trv

import no.steria.trv.Author

class Book {
	String title
	Author author
	
	static constraints = {
		author(nullable:true)
	}
	
	String toString() {
		title
	}
}
