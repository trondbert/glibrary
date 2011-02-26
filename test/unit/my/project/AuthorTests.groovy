package my.project

import grails.test.*
import no.steria.trv.Author

class AuthorTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testToString() {
    	Author author = new Author(firstName: "Benny", lastName: "Hill");
    	assertEquals "Hill, Benny", author.toString();
    }
}
