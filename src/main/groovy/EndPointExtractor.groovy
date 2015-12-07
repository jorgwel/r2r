import org.raml.model.Action
import org.raml.model.ActionType
import org.raml.model.MimeType
import org.raml.model.Raml
import org.raml.model.Resource
import org.raml.model.Response
import org.raml.model.parameter.QueryParameter
import org.raml.parser.visitor.RamlDocumentBuilder

/**
 * Created by jorge.bautista on 4/12/15.
 */
class EndPointExtractor {

    public List printEveryResource(Map<String, Resource> resources, Traverser resourceTraverser){

        resourceTraverser.traverse(resources)

        []
    }

    public static List extractResources(Map<String, Resource> resources) {
        def resourcesIterator = resources.iterator()

        while(resourcesIterator.hasNext()) {
            resourcesIterator.next()
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

    public static void printResource(Map.Entry<String, Resource> resource) {
        println "Printing"
        println "Key: " + resource.key + ", \t\t\tValue: " + resource.value
    }

    private Raml bringRamlInstance(String filePath) {
        def inputStream = getInputStream filePath
        def raml = new RamlDocumentBuilder().build(inputStream, filePath);

        raml
    }

    private static InputStream getInputStream(String resourceLocation) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation);
    }


    //Internal resources

    interface MapEntryPrinter<X,Y>{
        void print(Map.Entry<X,Y> object)
    }

    interface Traverser<X, Y> {
        public void traverse(Map<X, Y> collection);
    }


    class ResourceMapEntryPrinter implements Traverser<String,Resource>, MapEntryPrinter <String,Resource> {

        private String levelIndicator = "";

        @Override
        public void traverse(Map<String,Resource> resources) {//Map<String, Resource>
            def resourcesIterator = resources.iterator()


            while(resourcesIterator.hasNext()){

                Map.Entry<String, Resource>  resourceEntry = resourcesIterator.next()
                print resourceEntry

                if(resourceEntry.value.resources.size() > 0){
                    levelIndicator += "\t"
                    traverse(resourceEntry.value.resources)
                }

                if(levelIndicator.length() > 0)
                    levelIndicator = levelIndicator.substring(0, levelIndicator.length() -2)

            }
        }

        @Override
        void print(Map.Entry<String, Resource> resourceEntry) {
            printInternalProperties(resourceEntry)
            new ActionMapEntryPrinter(levelIndicator).traverse(resourceEntry.value.actions)

            println ""
        }

        private void printInternalProperties(Map.Entry<String, Resource> resourceEntry) {

            def resource = resourceEntry.value

            println levelIndicator + "=RESOURCE : "
            println levelIndicator + "name: " + resourceEntry.key
            println levelIndicator + "uri: " + resource.uri
            println levelIndicator + "displayName: " + resource.displayName
            println levelIndicator + "description: " + resource.description
            println levelIndicator + "type: " + resource.type
            println levelIndicator + "baseUriParameters: " + resource.baseUriParameters

        }

    }

    class ActionMapEntryPrinter implements Traverser<ActionType, Action>, MapEntryPrinter<ActionType, Action> {

        private String levelIndicator;

        ActionMapEntryPrinter(String levelIndicator) {
            this.levelIndicator = levelIndicator
        }

        @Override
        void traverse(Map<ActionType,Action> actions) {//Map<ActionType, Action>

            if(actions.size() == 0)
                return

            println this.levelIndicator + "ACTIONs: "
            def actionsIterator = actions.iterator()
            while (actionsIterator.hasNext())
                print actionsIterator.next()
        }

        @Override
        public void print(Map.Entry<ActionType, Action> actionEntry) {
            def extraLevel = this.levelIndicator
            printInternalProperties(extraLevel, actionEntry)
            new QueryParamsMapEntryPrinter(extraLevel).traverse(actionEntry.value.queryParameters)
            new ResponseMapEntryPrinter("RESPONSE", extraLevel).traverse(actionEntry.value.responses)

        }

        private void printInternalProperties(String extraLevel, Map.Entry<ActionType, Action> actionEntry) {
            def action = actionEntry.value
            println extraLevel + "--"
            println extraLevel + "Action: " + actionEntry.key
            println extraLevel + "Type: " + action.type
            println extraLevel + "action uri: " + action.baseUriParameters
        }


    }

    class QueryParamsMapEntryPrinter implements Traverser<String,QueryParameter>, MapEntryPrinter<String,QueryParameter> {

        private String levelIndicator;

        QueryParamsMapEntryPrinter(String levelIndicator) {
            this.levelIndicator = levelIndicator
        }

        @Override
        void traverse(Map<String,QueryParameter> queryParameters) {//Map<String, QueryParameter>

            if(queryParameters.size() == 0)
                return

            println this.levelIndicator + "QUERY PARAMETERs: "
            def queryParamsIterator = queryParameters.iterator()
            while(queryParamsIterator.hasNext()){
                print queryParamsIterator.next()
            }
        }

        @Override
        public void print(Map.Entry<String,QueryParameter> queryParameterEntry) {
            def extraLevel = this.levelIndicator + "\t"
            def queryParameter = queryParameterEntry.value

            println extraLevel + queryParameterEntry.key

            extraLevel += "\t"
            println extraLevel + "displayName: " + queryParameter.displayName
            println extraLevel + "description: " + queryParameter.description
            println extraLevel + "type: " + queryParameter.type

        }



    }


    class ResponseMapEntryPrinter extends RecursivePrinter<String, Response> {

        ResponseMapEntryPrinter(String idOfItemToPrint, String levelIndicator) {
            super(idOfItemToPrint, levelIndicator)
        }

        @Override
        public void printObject(Map.Entry<String, Response> responseEntry) {

            def response = responseEntry.value

            printText "status: " + responseEntry.key
            printText "body: " + response.body
            printText "description: " + response.description

            callMimeTypePrinter(response)

        }


        private void callMimeTypePrinter(Response response) {
            def mimeTypes = response.body
            new MimeTypeMapEntryPrinter("Mime Type", getLevelIndicator()).traverse(mimeTypes)
        }


    }

    class MimeTypeMapEntryPrinter extends RecursivePrinter<String,MimeType> {

        MimeTypeMapEntryPrinter(String idOfItemToPrint, String levelIndicator) {
            super(idOfItemToPrint, levelIndicator)
        }

        @Override
        public void printObject(Map.Entry<String,MimeType> mimeTypeEntry) {

            def mimeType = mimeTypeEntry.value
            printText "mimeType: " + mimeTypeEntry.key
            printText "type: " + mimeType.type


        }

    }

    abstract class RecursivePrinter<X,Y> {

        private String levelIndicator = ""
        private String id

        public RecursivePrinter(String idOfItemToPrint, String levelIndicator) {
            this.id = idOfItemToPrint
            this.levelIndicator = levelIndicator
        }

        void traverse(Map<X,Y> items) {

            if (items.size() == 0)
                return

            incrementLevelIndicator()
            printText id + "s: "
            def itemsIterator = items.iterator()

            while (itemsIterator.hasNext())
                printObject itemsIterator.next()

        }

        abstract void printObject(Map.Entry<X,Y> itemEntry);

        void printText(String text) {
            println getLevelIndicator() + text
        }

        String getLevelIndicator() {
            return levelIndicator
        }

        private void incrementLevelIndicator() {
            this.levelIndicator += "\t"
        }
    }

}



