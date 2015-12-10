import printer.APIPrinter
import spock.lang.Specification

class AbstractRecursivePrinterTests extends Specification {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    def oldOut
    def oldErr
    
    void setup() {
        /*
        * Since this AbstractRecursivePrinterTests is in charge of print, 
        * we should have some place where to validate printed text
        * Note: also check out the cleanup() method
        */
        oldOut = System.out
        oldErr = System.err
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    void cleanup() {
        System.setOut oldOut
        System.setErr oldErr
    }
    
    def "Text from one end point"() {
        when:
            def pathOfSampleOutputFile = "outputsamples/expected_output_from_one_end_point.txt"
            def expectedOutput = getTextFromFile(pathOfSampleOutputFile)
            def pathOfRamlFile = "ramlexamples/one_end_point.raml"
            new APIPrinter().printResources(pathOfRamlFile)

        then:
            expectedOutput == outContent.toString()
    }

    def "Text from many end points"() {
        when:
            def pathOfSampleOutputFile = "outputsamples/expected_output_from_many_end_points.txt"
            def expectedOutput = getTextFromFile(pathOfSampleOutputFile)
            def pathOfRamlFile = "ramlexamples/many_end_points.raml"
            new APIPrinter().printResources(pathOfRamlFile)

        then:
            expectedOutput == outContent.toString()
    }


    def String getTextFromFile(fileName) {
        def filePath = Thread.currentThread().getContextClassLoader().getResource(fileName).file
        def expectedOutput = new File(filePath).getText('UTF-8')
        expectedOutput
    }
    
}
