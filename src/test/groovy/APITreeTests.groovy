import apitree.APITree
import spock.lang.Specification

class APITreeTests extends Specification{

    void setup() {
        
    }

    void cleanup() {
        
    }

    def "APITree class exists"() {

        APITree instance = APITree.class.newInstance()
        
        expect: instance != null

    }
    
    def "Get Resources from tree"(){
        def apiTreeInstance = new APITree()
        def listOfResources = apiTreeInstance.resources
        
        expect: listOfResources != null 
    }

    
//    def "Getting only 1 resource"(){
//        def apiTreeInstance = new APITree()
//        def listOfResources = apiTreeInstance.resources
//
//        expect: listOfResources.size() == 0
//    
//    }
    
}
