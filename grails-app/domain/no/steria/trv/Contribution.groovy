package no.steria.trv

class Contribution {	
	Author author
	Book book
	static belongsTo = [Author,Book]

    static constraints = {
    	author(nullable:false)
    	book(nullable:false)
    }
	
	String toString() {
		return "" + book + ", by " + author 
	}
}
