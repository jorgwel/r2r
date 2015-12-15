package printer

import spock.lang.Specification

class AbstractPrinterTests extends Specification{

    def "AbstractRecursivePrinterTests instantiation"(){
        when:

            def recursivePrinter = getAbstractPrinterImplementation()
            def parameter = ['color':'Blue', 'shape':'Circle'] as Map
            recursivePrinter.traverse(parameter)
        then:
            println "recursivePrinter: " + recursivePrinter

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
