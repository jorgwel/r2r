package util

class Util {
    
    def static getAbsolutePathFromFileName(fileName){
        URL url = Thread.currentThread().getContextClassLoader().getResource(fileName)
        url != null? url.file: ""
    }
    
}
