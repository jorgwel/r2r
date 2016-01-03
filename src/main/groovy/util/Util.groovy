package util

import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import validation.APIFileValidator

class Util {

    def static getAbsolutePathFromFileName(fileName){
        URL url = Thread.currentThread().getContextClassLoader().getResource(fileName)
        url != null? url.file: ""
    }

    def static Raml bringRamlInstance(String filePath) {
        new APIFileValidator(filePath).validateApiFile()
        def inputStream = getInputStream filePath
        new RamlDocumentBuilder().build inputStream, filePath
    }

    def static InputStream getInputStream(String resourceLocation) {
        new APIFileValidator(resourceLocation).validateApiFile()
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation);
    }
    
    
}
