import apitree.RAMLResource
import spock.lang.Specification

class RAMLResourceTests extends Specification{

    def "Resource type is present"() {

        RAMLResource ramlResource = RAMLResource.class.newInstance()
        
        expect: ramlResource != null

    }
}
