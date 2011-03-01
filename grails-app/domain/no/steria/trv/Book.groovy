package no.steria.trv

import no.steria.trv.Contribution

class Book {
	static transients = ["initialContribution"]
	String title
	List contributions
	static hasMany = [contributions:Contribution]
	
	static constraints = {
		title(blank:false)		
	}
	
	String toString() {
		title
	}	
}
