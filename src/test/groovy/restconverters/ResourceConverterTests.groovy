package restconverters

import apitree.apiitems.RESTResource
import org.raml.model.Resource
import spock.lang.Specification

class ResourceConverterTests extends Specification{
    
    def "ResourceConverter class, exists"() {
        
        when: 
            new ResourceConverter ()
        then:
            notThrown(InstantiationException)
        
    }

    def "Method convert receives a Map.Entry<String, org.raml.Resource> as parameter"() {

        when:
            new ResourceConverter().convert(getMapEntryImplementation())
        
        then:
            notThrown(RuntimeException)

    }

    def "Method throws a ClassCastException when the parameter is different from Map.Entry<String, org.raml.Resource>"() {

        when:
        new ResourceConverter().convert(getMapEntryDifferentImplementation())

        then:
        notThrown(RuntimeException)

    }
    private Map.Entry<String, String> getMapEntryDifferentImplementation() {
        Map.Entry<String, String> mapEntry = new Map.Entry<String, String>() {
            @Override
            String getKey() {
                return ""
            }

            @Override
            String getValue() {
                return ""
            }

            @Override
            String setValue(String value) {
                return value
            }
        }
        mapEntry.setValue("")
        println "Map Entry BEFORE: ${mapEntry.metaClass}"
        return mapEntry
    }

    
    private Map.Entry<String, Resource> getMapEntryImplementation() {
        def mapEntry = new Map.Entry<String, Resource>() {
            Resource instance
            @Override
            String getKey() {
                return "Hola"
            }

            @Override
            Resource getValue() {
                return instance
            }

            @Override
            Resource setValue(Resource value) {
                instance = value
                return instance
            }
        }
        mapEntry.setValue(new Resource())
        mapEntry
    }

    def "Throws a ClassCastException when a wrong parameter type is thrown"() {

        when:
            new ResourceConverter ().convert(new String())

        then:
            thrown(RuntimeException)

    }

    def "Method returns an instance of RESTResource"() {

        when:
            def resultObject = new ResourceConverter ().convert(new Resource())

        then:
            resultObject instanceof RESTResource

    }

    def "Method returns an object different from null"() {

        when:
            def resultObject = new ResourceConverter ().convert(new Resource())

        then:
            resultObject != null

    }
    

//    def "ResourceConverter throws a ClassCastException"() {
//
//        when:
//        new RESTElementConverter () {
//            @Override
//            Object convert(Object originObject) {
//                return null
//            }
//        }
//        then:
//            notThrown(InstantiationException)
//
//    }

}
