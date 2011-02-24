import no.steria.trv.Book

class BootStrap {

    def init = { servletContext ->
		// Check whether the test data already exists.
        if (!Book.count()) {
            new Book(title: "The Shining").save(failOnError: true)
            new Book(title: "Along Came a Spider").save(failOnError: true)
        }
    }
    def destroy = {    
    }
}
