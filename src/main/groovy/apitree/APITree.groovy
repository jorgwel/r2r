package apitree

class APITree {

    def resources
    def filePath = ""

    APITree(filePath) {
        this.resources = [] as LinkedList
        this.filePath = filePath == null? "": filePath
    }

    def fillTree() {
        validateFile()
    }

    def validateFile() {

        if(filePath.isEmpty())
            throw new NotRAMLFileDefinedYet()

        File f = new File(filePath);
        if(!f.exists()) {
            println "Exiwte"
            throw new NotExistantFile()
        }

        return true

    }

    static class NotRAMLFileDefinedYet extends RuntimeException{
        NotRAMLFileDefinedYet() {
            super("No RAML file has been set yet")
        }
    }

    static class NotExistantFile extends RuntimeException{
        NotExistantFile(filePath) {
            super("The provided path does not exist: " + filePath)
        }
    }
}
