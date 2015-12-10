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

    def "If we want to collect without an assigned path name, an exception is raised"(){
        setup:
            def apiTree = new APITree()

        when:
            apiTree.fillTree()

        then:
            thrown APITree.NotRAMLFileDefinedYet
    }

    def "If we want to collect with an inexistant file"(){


        setup:
            def apiTree = new APITree("file_doesnt_exists.txt")

        when:
            apiTree.fillTree()

        then:
            thrown APITree.NotExistantFile
    }


    
}
