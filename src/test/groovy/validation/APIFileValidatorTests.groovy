package validation

import spock.lang.Specification
import validation.APIFileValidator

class APIFileValidatorTests extends Specification{


    def "If we want to collect without an assigned path name, a FileNotSpecified exception is raised"(){
        when:
            def apiValidator = new APIFileValidator()
            apiValidator.validateRamlFile()

        then:
            thrown APIFileValidator.FileNotSpecified
    }

    def "If we pass an inexistant file, a NotExistingFile exception is raised"(){
        when:
            def apiValidator = new APIFileValidator("file_doesnt_exists.txt")
            apiValidator.validateRamlFile()

        then:
            thrown APIFileValidator.FileDoesNotExist
    }

    def "If we pass an invalid RAML file, an InvalidRamlFile exception is raised"(){
        when:
            def apiValidator = new APIFileValidator("ramlexamples/invalid.raml")
            apiValidator.validateRamlFile()

        then:
            thrown APIFileValidator.InvalidRamlFile
    }

    def "If we pass a valid RAML file, validation errors are 0"(){
        when:
            def apiValidator = new APIFileValidator("ramlexamples/one_end_point.raml")
            apiValidator.validateRamlFile()

        then:
            apiValidator.validationErrors.size() == 0
    }

    def "If we pass a valid RAML file by setting directly the property 'relativePathFile', valildation errors are also 0"(){
        when:
            def apiValidator = new APIFileValidator()
            apiValidator.relativePathFile = "ramlexamples/one_end_point.raml"
            apiValidator.validateRamlFile()

        then:
            apiValidator.validationErrors.size() == 0
    }



}
