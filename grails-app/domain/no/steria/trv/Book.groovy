package no.steria.trv

import no.steria.trv.Contribution

class Book {
	static transients = ["initialAuthor"]
	String title
	static hasMany = [contributions:Contribution]
	
	Author initialAuthor	
	
	static constraints = {
		title(nullable:false)		
	}
	
	String toString() {
		title
	}	
}
