package apitree

import spock.lang.Specification
import validation.APIFileValidator

class RESTStructureContainerTests extends Specification{

    def "RESTStructureContainer class exists"() {

        RESTStructureContainer instance = RESTStructureContainer.class.newInstance()
        expect: instance != null

    }
    
    def "Get Resources from tree"(){
        def apiTreeInstance = new RESTStructureContainer()
        def listOfResources = apiTreeInstance.resourcesContainer
        
        expect: 
            listOfResources != null 
    }

    def "If we want to collect without an assigned path name, a FileNotSpecified exception is raised"(){
        setup:
            def apiTree = new RESTStructureContainer()

        when:
            apiTree.fillTree()

        then:
            thrown APIFileValidator.FileNotSpecified
    }

    def "If we pass an inexistant file, a NotExistingFile exception is raised"(){
        setup:
            def apiTree = new RESTStructureContainer("file_doesnt_exists.txt")

        when:
            apiTree.fillTree()

        then:
            thrown APIFileValidator.FileDoesNotExist
    }

    def "If we pass an invalid RAML file, an InvalidRamlFile exception is raised"(){
        setup:
            def apiTree = new RESTStructureContainer("ramlexamples/invalid.raml")

        when:
            apiTree.fillTree()

        then:
            thrown APIFileValidator.InvalidRamlFile
    }

    def "Passing a valid file with 1 resource returns LinkedList with 1 resource inside"(){
        setup:
            def apiTree = new RESTStructureContainer("ramlexamples/one_end_point.raml")

        when:
            apiTree.fillTree()

        then:
            apiTree.resourcesContainer.size() == 1
    }


}
