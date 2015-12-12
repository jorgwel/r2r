package printer

import printer.specialists.ResourcePrinter
import util.Util

/**
 * Created by jorge.bautista on 4/12/15.
 */
class APIPrinter {

    def printResources(String ramlFilePath) {
        def ramlDocumentInstance = Util.bringRamlInstance(ramlFilePath)
        def recursivePrinter = new ResourcePrinter("RESOURCE", "")
        recursivePrinter.traverse(ramlDocumentInstance.resources)
    }











}
