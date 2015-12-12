package util

import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import spock.lang.Specification
import validation.APIFileValidator

import java.nio.file.Path
import java.nio.file.Paths

class UtilTests extends Specification{

    def "Util class exists"(){
        def utilInstance = Util.class.newInstance()
        
        expect: utilInstance != null
    }
    
    def "Bring absolute EXISTENT path of a file name inside the project"() {
        def relativePath = "otherfiles/findable_file.example"
        String stringPath = Util.getAbsolutePathFromFileName(relativePath)
        File f = getFile(relativePath)

        expect:
            f.exists()
            stringPath.startsWith(System.getProperty("user.dir"))
            stringPath.endsWith(relativePath)
            
    }

    def "Throws an FileNotDefined when the file is NULL"() {
        when:
            Util.bringRamlInstance(null)
        then:
            thrown APIFileValidator.FileNotSpecified

    }

    def "Throws an FileNotSpecified when the file is empty"() {
        when:
            Util.bringRamlInstance("")
        then:
            thrown APIFileValidator.FileNotSpecified

    }

    def "If we pass an inexistant file, a NotExistingFile exception is raised"(){
        when:
            Util.bringRamlInstance("file_doesnt_exists.txt")

        then:
            thrown APIFileValidator.FileDoesNotExist
    }

    def "If we pass an invalid RAML file, an InvalidRamlFile exception is raised"(){
        when:
            Util.bringRamlInstance("ramlexamples/invalid.raml")

        then:
            thrown APIFileValidator.InvalidRamlFile
    }

    def "If we pass an valid RAML file, we get a nice Raml instance"(){
        when:
            Raml ramlInstance = Util.bringRamlInstance("ramlexamples/one_end_point.raml")

        then:
            ramlInstance != null
            ramlInstance.class.name == "org.raml.model.Raml"

    }

    def "Throws an FileNotDefined when the file is NULL (stream)"() {
        when:
            def stream = Util.getInputStream null
        then:
            thrown APIFileValidator.FileNotSpecified

    }

    def "Throws an FileNotSpecified when the file is empty (stream)"() {
        when:
            def stream = Util.getInputStream ""
        then:
            thrown APIFileValidator.FileNotSpecified

    }

    def "If we pass an inexistant file, a NotExistingFile exception is raised (stream)"(){
        when:
            Util.getInputStream("file_doesnt_exists.txt")

        then:
            thrown APIFileValidator.FileDoesNotExist
    }

    def "If we pass an invalid RAML file, an InvalidRamlFile exception is raised (stream)"(){
        when:
            Util.getInputStream("ramlexamples/invalid.raml")

        then:
            thrown APIFileValidator.InvalidRamlFile
    }

    def "If we pass an valid RAML file, we get a nice InputStream instance"(){
        when:
            def inputStream = Util.getInputStream("ramlexamples/one_end_point.raml")


        then:
            inputStream.class.name == "java.io.BufferedInputStream"

    }

    def getFile(String findableFilePath) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(findableFilePath)
        Path absolutePath = Paths.get(url.path)
        absolutePath.toFile()
    }
}
