package no.steria.trv

import no.steria.trv.Book

class Author {
	static hasMany = [book:Book]
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
