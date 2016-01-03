package restconverters;

import spock.lang.Specification;

public class RestElementConverterTests extends Specification {
    
    def "Interface RestElementConverter exists"() {
        when:
            RESTElementConverter restElementConverter = new RESTElementConverter(){
                @Override
                Object convert(Object originObject) {
                    return null
                }
            }
        then:
            restElementConverter != null
    }

    def "Interface has 'convert' method"() {
        when:
            RESTElementConverter restElementConverter = new RESTElementConverter<String, String>(){
                @Override
                String convert(String originObject) {
                    return "String result"
                }
            }
            restElementConverter.convert("String param")
        then:
            notThrown(MissingMethodException)
    }

    def "If it receives a wrong type, throws a ClassCastException"() {
        when:
            RESTElementConverter restElementConverter = new RESTElementConverter<Integer, String>(){
                @Override
                String convert(Integer originObject) {
                    return "String result"
                }
            }
            restElementConverter.convert("String param")
        then:
            thrown(ClassCastException)
    }

    def "If it returns a wrong type, throws a ClassCastException"() {
        when:
            RESTElementConverter restElementConverter = new RESTElementConverter<Integer, String>(){
                @Override
                String convert(Integer originObject) {
                    return 0
                }
            }
            restElementConverter.convert("String param")
        then:
            thrown(ClassCastException)
    }





}
