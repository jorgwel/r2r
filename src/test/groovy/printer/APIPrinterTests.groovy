package printer

class APIPrinterTests extends AbstractTextPrintingTests {
    
    def "Text from one end point"() {
        when:
            def pathOfSampleOutputFile = "outputsamples/expected_output_from_one_end_point.txt"
            def expectedOutput = getTextFromFile(pathOfSampleOutputFile)
            def pathOfRamlFile = "ramlexamples/one_end_point.raml"
            new APIPrinter().printResources(pathOfRamlFile)

        then:
            expectedOutput == getPrintedContent().toString()
    }

    def "Text from many end points"() {
        when:
            def pathOfSampleOutputFile = "outputsamples/expected_output_from_many_end_points.txt"
            def expectedOutput = getTextFromFile(pathOfSampleOutputFile)
            def pathOfRamlFile = "ramlexamples/many_end_points.raml"
            new APIPrinter().printResources(pathOfRamlFile)

        then:
            expectedOutput == getPrintedContent().toString()
    }


    def String getTextFromFile(fileName) {
        def filePath = Thread.currentThread().getContextClassLoader().getResource(fileName).file
        def expectedOutput = new File(filePath).getText('UTF-8')
        expectedOutput
    }
    
}
