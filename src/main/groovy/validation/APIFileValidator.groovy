package validation

import org.raml.parser.visitor.RamlValidationService
import util.Util

class APIFileValidator {

    def relativePathFile
    def validationErrors

    APIFileValidator() {
        this("")
    }

    APIFileValidator(pathFile) {
        this.relativePathFile = pathFile != null? pathFile : "" 
    }

    def validateApiFile() {
        if (relativePathFile.isEmpty())
            throw new FileNotSpecified()
        if (!getFile(relativePathFile).exists())
            throw new FileDoesNotExist(getFile(relativePathFile).absolutePath)
        if (getValidationErrors(relativePathFile).size() != 0)
            throw new InvalidRamlFile(relativePathFile)
    }

    def File getFile(relativePathFile) {
        def fullPath = Util.getAbsolutePathFromFileName(relativePathFile)
        new File(fullPath);
    }

    def getValidationErrors(fileRelativePath) {
        validationErrors = RamlValidationService.createDefault().validate(fileRelativePath)
        validationErrors
    }

    static class FileNotSpecified extends RuntimeException{
        FileNotSpecified() {
            super("No RAML file has been set yet. You should send 'relativePathOfApiDescriptorFile' value in the constructor or set it manually")
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
