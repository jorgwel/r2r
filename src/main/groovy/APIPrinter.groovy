import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import printer.ResourcePrinter

/**
 * Created by jorge.bautista on 4/12/15.
 */
class APIPrinter {

    def printResources(String ramlFilePath) {
        def ramlDocumentInstance = bringRamlInstance(ramlFilePath)
        def recursivePrinter = new ResourcePrinter("RESOURCE", "")
        recursivePrinter.traverse(ramlDocumentInstance.resources)
    }

    private Raml bringRamlInstance(String filePath) {
        def inputStream = getInputStream filePath
        new RamlDocumentBuilder().build inputStream, filePath
    }

    private static InputStream getInputStream(String resourceLocation) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation);
    }









}
