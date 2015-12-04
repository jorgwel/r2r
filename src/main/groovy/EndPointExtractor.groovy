import org.raml.model.ActionType
import org.raml.model.MimeType
import org.raml.model.Raml
import org.raml.model.Resource
import org.raml.parser.visitor.RamlDocumentBuilder

/**
 * Created by jorge.bautista on 4/12/15.
 */
class EndPointExtractor {


    public List extractResources(String filePath) {
        def ramlParser = bringRamlInstance(filePath);
        Iterator<Map.Entry<String, Resource>> resourcesIterator = ramlParser.iterator()

        while(resourcesIterator.hasNext()) {
            Map.Entry<String, Resource> resource = resourcesIterator.next()
            println "Title: " + resource.key
        }

        []
    }

    public List extractEndPoints(String filePath) {
        def resourceIterator = bringResourceIterator(filePath)
        def arrayOfResources = collectResources(resourceIterator)

        arrayOfResources
    }

    private List collectResources(Iterator<Map.Entry<String, Resource>> resourceIterator) {
        def arrayOfResources = []
        while (resourceIterator.hasNext()){
            def entry = resourceIterator.next();
            arrayOfResources << entry
            printResource(entry)
        }


        arrayOfResources
    }

    def Iterator<Map.Entry<String, Resource>> bringResourceIterator(String filePath) {

        def raml = bringRamlInstance(filePath)
        def resources = raml.getResources();

        resources.entrySet().iterator()
    }

    def printResource(Map.Entry<String, Resource> resource) {
        println "Key: " + resource.key + ", Value: " + resource.value
    }

    private Raml bringRamlInstance(String filePath) {
        def inputStream = getInputStream filePath
        def raml = new RamlDocumentBuilder().build(inputStream, filePath);

        raml
    }

    private static InputStream getInputStream(String resourceLocation) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation);
    }

}
