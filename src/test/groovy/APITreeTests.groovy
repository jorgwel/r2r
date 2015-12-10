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

    def "If we want to collect without an assigned path name, a FileNotSpecified exception is raised"(){
        setup:
            def apiTree = new APITree()

        when:
            apiTree.fillTree()

        then:
            thrown APITree.FileNotSpecified
    }

    def "If we pass an inexistant file, a NotExistingFile exception is raised"(){
        setup:
            def apiTree = new APITree("file_doesnt_exists.txt")

        when:
            apiTree.fillTree()

        then:
            thrown APITree.FileDoesNotExist
    }

    def "If we pass an invalid RAML file, an InvalidRamlFile exception is raised"(){
        setup:
            def apiTree = new APITree("ramlexamples/invalid.raml")

        when:
            apiTree.fillTree()

        then:
            thrown APITree.InvalidRamlFile
    }

    def "Passing a valid file with 1 resource returns LinkedList with 1 resource inside"(){
        setup:
            def apiTree = new APITree("ramlexamples/one_end_point.raml")

        when:
            apiTree.fillTree()

        then:
            apiTree.resources.size() == 1
    }


}
