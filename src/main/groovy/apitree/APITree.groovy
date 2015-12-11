package apitree

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
        
//        def apiPrinter = new APIPrinter()
//        apiPrinter.printResources(this.relativePathOfApiDescriptorFile)
    }

}
