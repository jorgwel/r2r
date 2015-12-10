import printer.APIPrinter
import spock.lang.Specification

/**
 * Created by jorge.bautista on 4/12/15.
 */
class DomainExtractorTest extends Specification {
//    void setup() {
//
//    }
//
//    void cleanup() {
//
//    }

    def "Print RAML"() {

        when:
            def pathOfRamlFile = "ramlexamples/many_end_points.raml"
            new APIPrinter().printResources(pathOfRamlFile)

        then:
            true

    }
    
}
