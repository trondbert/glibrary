import no.steria.trv.Book
import no.steria.trv.Author
import no.steria.trv.Contribution

class BootStrap {

    def init = { servletContext ->
		// Check whether the test data already exists.
        if (!Book.count()) {
            Author king = new Author(firstName:'Stephen',lastName:'King')
            Book shining = new Book(title: "The Shining")
            Contribution contribution = new Contribution(book:shining, author:king)

            king.save(failOnError: true)            
            shining.contributions = [contribution];
            shining.save(failOnError: true)
        }
    }
    def destroy = {    
    }
}
