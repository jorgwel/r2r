package apitree.recollectors

import apitree.ItemRecollector

class ResourceRecollector {

    ResourceRecollector(String idOfItemToPrint, String levelIndicator) {
        super(idOfItemToPrint, levelIndicator)

    }

//    @Override
//    public void traverse(Map<String, RESTResource> resources) {//Map<String, RESTResource>
//        def resourcesIterator = resources.iterator()
//
//        incrementLevelIndicator()
//        while (resourcesIterator.hasNext()) {
//
//            Map.Entry<String, RESTResource> resourceEntry = resourcesIterator.next()
//            printObject resourceEntry
//
//            if (resourceEntry.value.resources.size() > 0)
//                traverse(resourceEntry.value.resources)
//
//        }
//        decrementLevelIndicator()
//    }

//    @Override
//    void printObject(Map.Entry<String, RESTResource> resourceEntry) {
//        printSingleProperties(resourceEntry)
//        printCollections(resourceEntry)
//        println ""
//    }
//
//    private void printCollections(Map.Entry<String, RESTResource> resourceEntry) {
//        new ActionPrinter("ACTION", levelIndicator).traverse(resourceEntry.value.actions)
//        new UriParametersPrinter("URI PARAMETER", levelIndicator).traverse(resourceEntry.value.uriParameters)
//        new UriParametersPrinter("BASE URI PARAMETER", levelIndicator).traverse(resourceEntry.value.baseUriParameters)
//        new UriParametersPrinter("RESOLVED URI PARAMETER", levelIndicator).traverse(resourceEntry.value.resolvedUriParameters)
//    }

//    private void printSingleProperties(Map.Entry<String, RESTResource> resourceEntry) {
//
//        def resource = resourceEntry.value
//
//        println levelIndicator + "=RESOURCE : "
//        println levelIndicator + "uri: " + resource.uri
//        println levelIndicator + "name: " + resourceEntry.key
//        println levelIndicator + "relativeUri: " + resource.relativeUri
//        println levelIndicator + "displayName: " + resource.displayName
//        println levelIndicator + "description: " + resource.description
//        println levelIndicator + "type: " + resource.type
//
//
//    }

}
