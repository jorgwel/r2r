package printer

import spock.lang.Specification

class AbstractRecursivePrinterTests extends Specification{

    def "AbstractRecursivePrinterTests instantiation"(){
        when:
//        Map.Entry<X, Y> itemEntry
////        AbstractMap.SimpleEntry<String, Integer>("exmpleString", 42);
//            def recursivePrinter = [printObject: { new AbstractMap.SimpleEntry<String, Integer>("hola", "mundo")->
        def recursivePrinter = [printObject: { ['color':'Blue', 'shape':'Circle'] as AbstractMap.SimpleEntry ->
                                            println "map: " + map

                                        }] as AbstractRecursivePrinter
            def parameter = ['color':'Blue', 'shape':'Circle'] as Map.Entry<String, String>
            //def paramToPrint = parameter.iterator()
            recursivePrinter.traverse(parameter)
        then:
            println "recursivePrinter: " + recursivePrinter
//        printObject(Map.Entry<X, Y> itemEntry);
    }

}
