package apitree

import org.raml.parser.visitor.RamlValidationService
import util.Util

class APITree {

    def resources
    def filePath

    APITree(filePath) {
        this.resources = [] as LinkedList
        this.filePath = filePath == null? "": filePath
    }

    def fillTree() {
        validateRamlFile()
        
    }

    def validateRamlFile() {
        if (filePath.isEmpty())
            throw new FileNotSpecified()
        if (!getFile().exists())
            throw new FileDoesNotExist(getFile().absolutePath)
        if (!isValid(filePath))
            throw new InvalidRamlFile(filePath)
    }

    def File getFile() {
        def fullPath = Util.getAbsolutePathFromFileName(filePath)
        new File(fullPath);
    }

    def isValid(fileRelativePath) {
        def validationErrors = RamlValidationService.createDefault().validate(fileRelativePath)
        validationErrors.size() == 0
    }

    static class FileNotSpecified extends RuntimeException{
        FileNotSpecified() {
            super("No RAML file has been set yet")
        }
    }

    static class FileDoesNotExist extends RuntimeException{
        FileDoesNotExist(filePath) {
            super("The provided path does not exist: " + filePath)
            println "The provided path does not exist: " + filePath
        }
    }

    static class InvalidRamlFile extends RuntimeException{
        InvalidRamlFile(filePath) {
            super("The provided path does not corresponds to a valid raml file: " + filePath)
        }
    }
}
