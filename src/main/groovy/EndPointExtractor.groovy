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

    public List printEveryResource(Map<String, Resource> resources, RecursivePrinter<String, Resource> recursivePrinter) {

        recursivePrinter.traverse(resources)

        []
    }

//    public static List extractResources(Map<String, Resource> resources) {
//        def resourcesIterator = resources.iterator()
//
//        while (resourcesIterator.hasNext()) {
//            resourcesIterator.next()
//        }
//
//        []
//    }
//
//    public List extractEndPoints(String filePath) {
//        def resourceIterator = bringResourceIterator(filePath)
//        def arrayOfResources = collectResources(resourceIterator)
//
//        arrayOfResources
//    }
//
//    private List collectResources(Iterator<Map.Entry<String, Resource>> resourceIterator) {
//        def arrayOfResources = []
//        while (resourceIterator.hasNext()) {
//            def entry = resourceIterator.next();
//            arrayOfResources << entry
//            printResource(entry)
//        }
//
//
//        arrayOfResources
//    }
//
//    def Iterator<Map.Entry<String, Resource>> bringResourceIterator(String filePath) {
//
//        def raml = bringRamlInstance(filePath)
//        def resources = raml.getResources();
//
//        resources.entrySet().iterator()
//    }
//
//    public static void printResource(Map.Entry<String, Resource> resource) {
//        println "Printing"
//        println "Key: " + resource.key + ", \t\t\tValue: " + resource.value
//    }
//
//    private Raml bringRamlInstance(String filePath) {
//        def inputStream = getInputStream filePath
//        def raml = new RamlDocumentBuilder().build(inputStream, filePath);
//
//        raml
//    }
//
//    private static InputStream getInputStream(String resourceLocation) {
//        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation);
//    }


    class ResourceMapEntryPrinter extends RecursivePrinter<String, Resource> {
        
        public ResourceMapEntryPrinter(String idOfItemToPrint, String levelIndicator) {
            super(idOfItemToPrint, levelIndicator)

        }

        @Override
        public void traverse(Map<String, Resource> resources) {//Map<String, Resource>
            def resourcesIterator = resources.iterator()


            while (resourcesIterator.hasNext()) {

                Map.Entry<String, Resource> resourceEntry = resourcesIterator.next()
                printObject resourceEntry

                if (resourceEntry.value.resources.size() > 0) {
                    incrementLevelIndicator()
                    traverse(resourceEntry.value.resources)
                    decrementLevelIndicator()
                }


            }
        }

        @Override
        void printObject(Map.Entry<String, Resource> resourceEntry) {
            printInternalProperties(resourceEntry)
            new ActionMapEntryPrinter("ACTION", levelIndicator).traverse(resourceEntry.value.actions)

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

    class ActionMapEntryPrinter extends RecursivePrinter<ActionType, Action> {

        ActionMapEntryPrinter(String idOfItemToPrint, String levelIndicator) {
            super(idOfItemToPrint, levelIndicator)
        }

        @Override
        public void printObject(Map.Entry<ActionType, Action> actionEntry) {
            def action = actionEntry.value
            printText "--"
            printText "Action: " + actionEntry.key
            printText "Type: " + action.type
            printText "action uri: " + action.baseUriParameters
            new QueryParamsMapEntryPrinter("QUERY PARAM", getLevelIndicator()).traverse(actionEntry.value.queryParameters)
            new ResponseMapEntryPrinter("RESPONSE", getLevelIndicator()).traverse(actionEntry.value.responses)

        }


    }

    class QueryParamsMapEntryPrinter extends RecursivePrinter<String, QueryParameter> {

        private String levelIndicator;

        QueryParamsMapEntryPrinter(String idOfItemToPrint, String levelIndicator) {
            super(idOfItemToPrint, levelIndicator)
        }

        @Override
        public void printObject(Map.Entry<String, QueryParameter> queryParameterEntry) {

            def queryParameter = queryParameterEntry.value
            printText queryParameterEntry.key
            incrementLevelIndicator()
            printText "displayName: " + queryParameter.displayName
            printText "description: " + queryParameter.description
            printText "type: " + queryParameter.type
            decrementLevelIndicator()

        }


    }


    class ResponseMapEntryPrinter extends RecursivePrinter<String, Response> {

        public ResponseMapEntryPrinter(String idOfItemToPrint, String levelIndicator) {
            super(idOfItemToPrint, levelIndicator)
        }

        @Override
        public void printObject(Map.Entry<String, Response> responseEntry) {

            def response = responseEntry.value

            printText "status: " + responseEntry.key
            printText "body: " + response.body
            printText "description: " + response.description

            def mimeTypes = response.body
            new MimeTypeMapEntryPrinter("Mime Type", getLevelIndicator()).traverse(mimeTypes)

        }


    }

    class MimeTypeMapEntryPrinter extends RecursivePrinter<String, MimeType> {

        MimeTypeMapEntryPrinter(String idOfItemToPrint, String levelIndicator) {
            super(idOfItemToPrint, levelIndicator)
        }

        @Override
        public void printObject(Map.Entry<String, MimeType> mimeTypeEntry) {

            def mimeType = mimeTypeEntry.value
            printText "mimeType: " + mimeTypeEntry.key
            printText "type: " + mimeType.type


        }

    }

    abstract class RecursivePrinter<X, Y> {

        private String levelIndicator = ""
        private String id

        public RecursivePrinter(String idOfItemToPrint, String levelIndicator) {
            this.id = idOfItemToPrint
            this.levelIndicator = levelIndicator
        }

        void traverse(Map<X, Y> items) {

            if (items.size() == 0)
                return

            incrementLevelIndicator()
            printText id + "s: "
            def itemsIterator = items.iterator()

            while (itemsIterator.hasNext())
                printObject itemsIterator.next()

        }

        abstract void printObject(Map.Entry<X, Y> itemEntry);

        void printText(String text) {
            println getLevelIndicator() + text
        }

        String getLevelIndicator() {
            return levelIndicator
        }

        public void incrementLevelIndicator() {
            this.levelIndicator += "\t"
        }

        public void decrementLevelIndicator() {
            if (this.levelIndicator.length() >= 2) {
                this.levelIndicator = this.levelIndicator.substring(0, this.levelIndicator.length() - 1)
            }
        }

    }
}
