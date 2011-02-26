package no.steria.trv

import java.util.HashMap;

import grails.test.*

class BookControllerTests extends ControllerUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSaveBook() {
		def chosenAuthor = new Author(firstName:"Ari",lastName:"Behn") 
		
		Author.metaClass.static.get = {Long id ->
			assert id == 10
			chosenAuthor
		}
		
		// mock the params object
		BookController.metaClass.getParams = {-> [title:"foo",initialAuthor:[id:10]] }
				
		def controller = new BookController();
		
		def response = controller.save()
		
		assert Book.list().size() == 1
    }
}
