import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import printer.ResourcePrinter

/**
 * Created by jorge.bautista on 4/12/15.
 */
class APIPrinter {

    def printEveryResource(String ramlFilePath) {
        def ramlDocumentInstance = bringRamlInstance("yamlexamples/many_end_points.yaml")
        def recursivePrinter = new ResourcePrinter("RESOURCE", "")
        recursivePrinter.traverse(ramlDocumentInstance.resources)
        1
    }

    private Raml bringRamlInstance(String filePath) {
        def inputStream = getInputStream filePath
        new RamlDocumentBuilder().build inputStream, filePath
    }

    private static InputStream getInputStream(String resourceLocation) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation);
    }









}
