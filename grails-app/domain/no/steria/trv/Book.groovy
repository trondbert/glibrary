package no.steria.trv

import no.steria.trv.Contribution
import java.util.ArrayList

class Book {
	static transients = ["initialContribution"]
	String title
	List contributions = new ArrayList()
	static hasMany = [contributions:Contribution]
	
	static constraints = {
		title blank: false
		contributions minSize: 1
	}
	
	String toString() {
		title
	}	
}
