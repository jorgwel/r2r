package printer

import printer.specialists.ResourcePrinter
import util.Util

class APIPrinter {

    def printResources(String ramlFilePath) {
        def ramlDocumentInstance = Util.bringRamlInstance(ramlFilePath)
        def recursivePrinter = new ResourcePrinter("RESOURCE", "")
        recursivePrinter.traverse(ramlDocumentInstance.resources)
    }











}
