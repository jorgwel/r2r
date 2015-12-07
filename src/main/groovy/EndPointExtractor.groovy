import org.raml.model.Action
import org.raml.model.ActionType
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

    interface Printer <T>{
        void print(T object)
    }

    interface Traverser<X, Y> {
        public void traverse(Map<X, Y> collection);
    }

    /*interface ResourceTraverser {
        public void actOnResources(Map<String, Resource> resources);
    }

    interface ActionTraverser {
        public void actOnActions(Map<ActionType, Action> actions);
    }

    interface QueryParametersTraverser {
        public void actOnActions(Map<ActionType, Action> actions);
    }
*/


    class ResourcePrinter implements Traverser<String,Resource>, Printer <Resource> {

        private String levelIndicator = "";

        @Override
        public void traverse(Map<String,Resource> resources) {//Map<String, Resource>
            def resourcesIterator = resources.iterator()


            while(resourcesIterator.hasNext()){

                Map.Entry<String, Resource>  resourceEntry = resourcesIterator.next()
                print resourceEntry.value

                if(resourceEntry.value.resources.size() > 0){
                    levelIndicator += "\t"
                    traverse(resourceEntry.value.resources)
                }

                if(levelIndicator.length() > 0)
                    levelIndicator = levelIndicator.substring(0, levelIndicator.length() -2)

            }
        }

        @Override
        void print(Resource resource) {
            printInternalProperties(resource)
            new ActionPrinter(levelIndicator).traverse(resource.actions)

            println ""
        }

        private void printInternalProperties(Resource resource) {
            println levelIndicator + "=RESOURCE : "
            println levelIndicator + "uri: " + resource.uri
            println levelIndicator + "displayName: " + resource.displayName
            println levelIndicator + "description: " + resource.description
            println levelIndicator + "type: " + resource.type
            println levelIndicator + "baseUriParameters: " + resource.baseUriParameters
        }

    }

    class ActionPrinter implements Traverser<ActionType, Action>, Printer<Action> {

        private String levelIndicator;

        ActionPrinter(String levelIndicator) {
            this.levelIndicator = levelIndicator
        }

        @Override
        void traverse(Map<ActionType,Action> actions) {//Map<ActionType, Action>

            if(actions.size() == 0)
                return

            println this.levelIndicator + "ACTIONs: "
            def actionsIterator = actions.iterator()
            while (actionsIterator.hasNext())
                print actionsIterator.next().value
        }

        @Override
        public void print(Action action) {
            def extraLevel = this.levelIndicator
            printInternalProperties(extraLevel, action)
            new QueryParamsPrinter(extraLevel).traverse(action.queryParameters)
            new ResponsePrinter(extraLevel).traverse(action.responses)

        }

        private void printInternalProperties(String extraLevel, Action action) {
            println extraLevel + "--"
            println extraLevel + "Type: " + action.type
            println extraLevel + "action uri: " + action.baseUriParameters
        }


    }

    class QueryParamsPrinter implements Traverser<String,QueryParameter>, Printer<QueryParameter> {

        private String levelIndicator;

        QueryParamsPrinter(String levelIndicator) {
            this.levelIndicator = levelIndicator
        }

        @Override
        void traverse(Map<String,QueryParameter> queryParameters) {//Map<ActionType, Action>

            if(queryParameters.size() == 0)
                return

            println this.levelIndicator + "QUERY PARAMETERs: "
            def queryParamsIterator = queryParameters.iterator()
            while(queryParamsIterator.hasNext()){
                print queryParamsIterator.next().value
            }
        }

        @Override
        public void print(QueryParameter queryParameter) {
            def extraLevel = this.levelIndicator + "\t"
            printInternalProperties(extraLevel, queryParameter)

        }

        private void printInternalProperties(String extraLevel, QueryParameter queryParameter) {
            println extraLevel + "name: " + queryParameter
            println extraLevel + "displayName: " + queryParameter.displayName
            println extraLevel + "description: " + queryParameter.description
            println extraLevel + "type: " + queryParameter.type
        }


    }


    class ResponsePrinter implements Traverser<String,Response>, Printer<Response> {

        private String levelIndicator;

        ResponsePrinter(String levelIndicator) {
            this.levelIndicator = levelIndicator
        }

        @Override
        void traverse(Map<String,Response> responses) {//Map<ActionType, Action>

            if(responses.size() == 0)
                return

            println this.levelIndicator + "RESPONSEs: "
            def responsesIterator = responses.iterator()
            while(responsesIterator.hasNext()){
                print responsesIterator.next().value
            }
        }

        @Override
        public void print(Response response) {
            def extraLevel = this.levelIndicator + "\t"
            printInternalProperties(extraLevel, response)

        }

        private void printInternalProperties(String extraLevel, Response response) {
            println extraLevel + "response: " + response
        }


    }

}



