import spock.lang.Specification
import util.Util

import java.nio.file.Path
import java.nio.file.Paths

class UtilTests extends Specification{

    def "Util class exists"(){
        def utilInstance = Util.class.newInstance()
        
        expect: utilInstance != null
    }
    
    def "Bring absolute EXISTIENT path of a file name inside the project"() {
        def relativePath = "otherfiles/findable_file.example"
        String stringPath = Util.getAbsolutePathFromFileName(relativePath)
        File f = getFile(relativePath)

        println "stringPath: " + stringPath
        println "System.getProperty(\"user.dir\"): " + System.getProperty("user.dir")
        println "relativePath: " + relativePath
        
        expect:
            f.exists()
            stringPath.startsWith(System.getProperty("user.dir"))
            stringPath.endsWith(relativePath)
            
    }

    def getFile(String findableFilePath) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(findableFilePath)
        Path absolutePath = Paths.get(url.path)
        absolutePath.toFile()
    }
}
