package apitree

import validation.APIFileValidator

class RESTStructureContainer {

    def resourcesContainer
    def relativePathOfApiDescriptorFile

    RESTStructureContainer(){
        this("")
    }

    RESTStructureContainer(filePath) {
        this.resourcesContainer = [] as LinkedList
        this.relativePathOfApiDescriptorFile = filePath == null? "": filePath
    }

    def fillTree() {
        def apiValidator = new APIFileValidator(this.relativePathOfApiDescriptorFile)
        apiValidator.validateApiFile()
//        collectResources(new ResourceColle)
        
//        def apiPrinter = new APIPrinter()
//        apiPrinter.printResources(this.relativePathOfApiDescriptorFile)
    }
    
//    def collectResources() {
//        ResourceRecollector resourceRecollector = new ResourceRecollector()
//        def resources = resourceRecollector.recursiveRecollect(resourcesContainer)
//    }
}
