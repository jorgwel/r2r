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

    def "SayHello"() {
        expect: hello == "Hello Groovy"
        where: hello = new DomainExtractor().sayHello()
    }
}
