package no.steria.trv

import java.util.HashMap;

import grails.test.*

class BookControllerTests extends ControllerUnitTestCase {
    protected void setUp() {
        super.setUp()
		BookController.metaClass.message = {args -> println "message : ${args}"}
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSaveNewBook() {
		def author = new Author(firstName:"Ari",lastName:"Behn")		
		def authorRepo = [author]
		mockDomain(Author, authorRepo)
		 
		mockDomain(Book) 
		
		mockDomain(Contribution)

		BookController.metaClass.getParams = {-> [title:"foo",initialAuthor:[id:1]] }
				
		def controller = new BookController();
		
		controller.save()
		
		assertEquals 1, Book.list().size()
		def newBook = Book.list().get(0)
		assertEquals "foo", newBook.title
		
		assertEquals 1, Contribution.list().size()
		assertEquals 1, newBook.contributions.size()
		def newContr = Contribution.list().get(0)
		assertEquals author, newContr.author
		assertEquals newBook, newContr.book
    }
	
	void testSaveWithoutInitialAuthor() {
		def author = new Author(firstName:"Ari",lastName:"Behn")
		def authorRepo = [author]
		mockDomain(Author, authorRepo)
						
		assert Author.get("1") == author
		 
		mockDomain(Book)		
		mockDomain(Contribution)

		BookController.metaClass.getParams = {-> [title:"foo"] }
				
		def controller = new BookController();		
		controller.save()
		
		assertEquals 0, Book.list().size()
		
		assert controller.modelAndView.model.contributions.toArray()[0].hasErrors()
		
		assertEquals 0, Contribution.list().size()
	}
}
