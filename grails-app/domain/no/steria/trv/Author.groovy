package no.steria.trv

import no.steria.trv.Contribution

class Author {
	static transients = ["books"]
	static hasMany = [contributions:Contribution,books:Book]
	String firstName
	String lastName
	
    static constraints = {
		firstName(maxSize:40)
		lastName(blank:false,maxSize:40)		
    }
	
	String toString(){
		lastName + ", " + firstName
	}
}
