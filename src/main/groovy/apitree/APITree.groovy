package apitree

import apitree.recollectors.ResourceRecollector
import validation.APIFileValidator

class APITree {

    def resourcesContainer
    def relativePathOfApiDescriptorFile

    APITree(){
        this("")
    }

    APITree(filePath) {
        this.resourcesContainer = [] as LinkedList
        this.relativePathOfApiDescriptorFile = filePath == null? "": filePath
    }

    def fillTree() {
        def apiValidator = new APIFileValidator(this.relativePathOfApiDescriptorFile)
        apiValidator.validateRamlFile()
        //collectResources()
        
//        def apiPrinter = new APIPrinter()
//        apiPrinter.printResources(this.relativePathOfApiDescriptorFile)
    }
    
//    def collectResources() {
//        ResourceRecollector resourceRecollector = new ResourceRecollector()
//        def resources = resourceRecollector.recollect(resourcesContainer)
//    }
}
