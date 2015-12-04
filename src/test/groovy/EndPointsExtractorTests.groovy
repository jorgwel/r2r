import spock.lang.Specification

/**
 * Created by jorge.bautista on 4/12/15.
 */
class EndPointsExtractorTests extends Specification {

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
        where: numberOfEndPoints = new EndPointExtractor().extractResources("yamlexamples/many_end_points.yaml").size()
    }

}
