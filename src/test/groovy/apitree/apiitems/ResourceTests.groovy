package apitree.apiitems

import apitree.apiitems.Resource
import spock.lang.Specification

class ResourceTests extends Specification{

    def "Resource type is present"() {

        Resource ramlResource = Resource.class.newInstance()
        
        expect: ramlResource != null

    }
}
