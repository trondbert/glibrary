package no.steria.trv

import java.util.List;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

class BookController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
		
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        return [bookInstanceList: Book.list(params), bookInstanceTotal: Book.count()]
    }

	def create = {
		def book = new Book()		
		book.properties = params
		return [bookInstance: book]
	}
	
	def save = {
    	def bookInstance = saveNewBook()
		
		def contrib = bookInstance.contributions.toArray()[0]
		
        if (!bookInstance.hasErrors() && !contrib.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'book.label', default: 'Book'), bookInstance.id])}"
            redirect(action: "show", id: bookInstance.id)
        }
        else {
            render(view: "create", model: [bookInstance: bookInstance, contributions: bookInstance.contributions,initialContribution: bookInstance.contributions?.toArray()[0]])
        }
    }

	def saveNewBook = {
		def bookInstance = new Book()
		//necessary for testing
		bookInstance.contributions.add(
			new Contribution(author:new Author(firstName:"gredfdg",lastName:"praks"), book:bookInstance));
		bookInstance.properties = params
		
		removeContributionsWithoutAuthors bookInstance.contributions
		
		if (bookInstance.validate()) {
			def success = true;
			bookInstance.contributions.each {
				success = success && it.validate();
			}
			if (success) {			
				bookInstance.save()
				bookInstance.contributions*.save()
			}
		}		
		return bookInstance
	}	

	def show = {
        def bookInstance = Book.get(params.id)
        if (!bookInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), params.id])}"
            redirect(action: "list")
        }
        else {
            [bookInstance: bookInstance]
        }
    }

	def edit = {
        def bookInstance = Book.get(params.id)
        if (!bookInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [bookInstance: bookInstance]
        }
    }

	def update = {
        def bookInstance = Book.get(params.id)
        if (bookInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (bookInstance.version > version) {                    
                    bookInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'book.label', default: 'Book')] as Object[], "Another user has updated this Book while you were editing")
                    render(view: "edit", model: [bookInstance: bookInstance])
                    return
                }
            }
            bookInstance.properties = params
			bookInstance.validate()
            if (!bookInstance.hasErrors() && bookInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'book.label', default: 'Book'), bookInstance.id])}"
                redirect(action: "show", id: bookInstance.id)
            }
            else {
                render(view: "edit", model: [bookInstance: bookInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def bookInstance = Book.get(params.id)
        if (bookInstance) {
            try {
                bookInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'book.label', default: 'Book'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'book.label', default: 'Book'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def removeContributionsWithoutAuthors(List<Contribution> contributions) {
		def removedAuthors = []
		Integer index = 1
		contributions.each {
			if (index != 1 && it.author == null) {
				removedAuthors.add(it);
			}
			index++
		}
		removedAuthors.each {
			contributions.remove it
		}
	}
}
