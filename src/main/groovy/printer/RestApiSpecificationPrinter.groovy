package printer

import printer.specialists.ResourcePrinter
import util.Util

class RestApiSpecificationPrinter {

    def printResources(String ramlFilePath) {
        def ramlDocumentInstance = Util.bringRamlInstance(ramlFilePath)
        def recursivePrinter = new ResourcePrinter("RESOURCE", "")
        recursivePrinter.traverse(ramlDocumentInstance.resources)
    }











}
