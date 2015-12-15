package printer

import spock.lang.Specification
import util.TestUtilities

class AbstractPrinterTests extends AbstractTextPrintingTests {

    def "AbstractRecursivePrinterTests instantiation"(){
        when:
            def expectedOutput = TestUtilities.getTextFromFile("outputsamples/expected_output_with_colors_example.txt")

            def recursivePrinter = getAbstractPrinterImplementation()
            def parameter = ['color':'Blue', 'shape':'Circle'] as Map
            recursivePrinter.traverse(parameter)
        then:
            expectedOutput == getPrintedContent().toString()

    }

    def getAbstractPrinterImplementation() {
        def abstractPrinter = [
//                printObject: { mapEntry = ['color': 'Blue',
//                                           'shape': 'Circle'] as AbstractMap.SimpleEntry ->
//                    println "map: " + mapEntry
//                }
                printObject: { mapEntry ->
                    println "mapEntry: " + mapEntry
                }
        ] as AbstractPrinter

        abstractPrinter.id = "COLOR"

        abstractPrinter
    }

}
