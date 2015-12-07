import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import spock.lang.Specification

/**
 * Created by jorge.bautista on 4/12/15.
 */
class EndPointsExtractorTests extends Specification {


    def "iterateOverResources"() {

        expect: numberOfEndPoints == 0
        where: numberOfEndPoints = new EndPointExtractor().printEveryResource(
                                                                bringRamlInstance("yamlexamples/many_end_points.yaml").getResources(),
                                                                new EndPointExtractor.ResourceMapEntryPrinter()
                                                            ).size()
    }

    def "Get First Level End Points"() {
        expect: numberOfEndPoints == 1
        where: numberOfEndPoints = new EndPointExtractor().extractEndPoints("yamlexamples/one_end_point.yaml").size()
    }

    def "Many End Points"() {
        expect: numberOfEndPoints == 3
        where: numberOfEndPoints = new EndPointExtractor().extractEndPoints("yamlexamples/many_end_points.yaml").size()
    }

    def "Extract Resources"() {
        expect: numberOfEndPoints == 3
        where: numberOfEndPoints = new EndPointExtractor().extractResources(bringRamlInstance("yamlexamples/many_end_points.yaml").getResources()).size()
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
