import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import spock.lang.Specification

/**
 * Created by jorge.bautista on 4/12/15.
 */
class AbstractRecursivePrinterTests extends Specification {


    def "iterateOverResources"() {
        expect: instance == 1
        where: instance = new APIPrinter().printEveryResource("yamlexamples/many_end_points.yaml")
    }

    def "Consider schemas"() {
        expect: true
        where: numberOfEndPoints = new APIPrinter().printEveryResource("yamlexamples/using_schemas.yaml")
    }

    def "Get First Level End Points"() {
        expect: numberOfEndPoints == 1
        where: numberOfEndPoints = new APIPrinter().extractEndPoints("yamlexamples/one_end_point.yaml").size()
    }

    def "Many End Points"() {
        expect: numberOfEndPoints == 3
        where: numberOfEndPoints = new APIPrinter().extractEndPoints("yamlexamples/many_end_points.yaml").size()
    }

    def "Extract Resources"() {
        expect: numberOfEndPoints == 3
        where: numberOfEndPoints = new APIPrinter().extractResources(bringRamlInstance("yamlexamples/many_end_points.yaml").getResources()).size()
    }


    private Raml bringRamlInstance(String filePath) {
        def inputStream = getInputStream filePath
        def raml = new RamlDocumentBuilder().build(inputStream, filePath);

        raml
    }

    private static InputStream getInputStream(String resourceLocation) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation);
    }


}
