package functionaltests

import org.raml.parser.visitor.RamlValidationService
import spock.lang.Specification
import util.TestUtilities

/**
 * Created by jorgwel on 1/06/16.
 */
class FunctionalTests extends Specification {
    
    def "File with 1 Resource, without nested Resources is valid" () {
        given:
            def fileName = "ramlexamples/resource_extracting/1_end_point.raml"
        when:
            def validationErrors = RamlValidationService.createDefault().validate(fileName)
        then:
            validationErrors.size() == 0
    }    
       
    def "File with 1 Resource and with 1 nested Resource is valid" () {
        given:
            def fileName = "ramlexamples/resource_extracting/1_end_point_1_nested_end_point.raml"
        when:
            def validationErrors = RamlValidationService.createDefault().validate(fileName)
        then:
            validationErrors.size() == 0
    }    
           
    def "File with 4 Resources, all of them with 1 nested Resource\
                                 and 1 of them with 2 nested levels" () {
        given:
            def fileName = "ramlexamples/resource_extracting/4_end_points_with_1_nested_end_point_per_each_and_one_end_point_with_2_nested.raml"
        when:
            def validationErrors = RamlValidationService.createDefault().validate(fileName)
        then:
            validationErrors.size() == 0
    }    
               
    def "File with 12 Resources. Six of them have 1 level of nested Resources. \
            Of those six, \
                two of them have 4 levels of nesting,\
                two of them have 3 levels of nesting\
                and last two have 2 levels of nesting." () {
        given:
            def fileName = "ramlexamples/resource_extracting/12_end_points_with_4,3,2and1,levels_of_nesting.raml"
        when:
            def validationErrors = RamlValidationService.createDefault().validate(fileName)
        then:
            validationErrors.size() == 0
    }    
    
}
