import no.steria.trv.Book
import no.steria.trv.Author
import no.steria.trv.Contribution

class BootStrap {

    def init = { servletContext ->
		// Check whether the test data already exists.
        if (!Book.count()) {
			Author king = new Author(firstName:'Steffen',lastName:'King').save(failOnError: true)
            Book shining = new Book(title: "The Shining")
            Contribution contribution = new Contribution(book:shining, author:king)
            shining.contributions = [contribution];
            shining.save(failOnError: true)

			
            //new Book(title: "Along Came a Spider").save(failOnError: true)
        }
    }
    def destroy = {    
    }
}
