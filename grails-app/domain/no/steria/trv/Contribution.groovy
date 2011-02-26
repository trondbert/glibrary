package no.steria.trv

class Contribution {
	static belongsTo = [author:Author,book:Book]
	Author author
	Book book

    static constraints = {
    	author(nullable:false)
    	book(nullable:false)
    }
}
