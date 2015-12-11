package apitree.recollectors

class ResourceRecollector  {

    ResourceRecollector(String idOfItemToPrint, String levelIndicator) {
        super(idOfItemToPrint, levelIndicator)

    }

//    @Override
//    public void traverse(Map<String, Resource> resources) {//Map<String, Resource>
//        def resourcesIterator = resources.iterator()
//
//        incrementLevelIndicator()
//        while (resourcesIterator.hasNext()) {
//
//            Map.Entry<String, Resource> resourceEntry = resourcesIterator.next()
//            printObject resourceEntry
//
//            if (resourceEntry.value.resources.size() > 0)
//                traverse(resourceEntry.value.resources)
//
//        }
//        decrementLevelIndicator()
//    }

//    @Override
//    void printObject(Map.Entry<String, Resource> resourceEntry) {
//        printSingleProperties(resourceEntry)
//        printCollections(resourceEntry)
//        println ""
//    }
//
//    private void printCollections(Map.Entry<String, Resource> resourceEntry) {
//        new ActionPrinter("ACTION", levelIndicator).traverse(resourceEntry.value.actions)
//        new UriParametersPrinter("URI PARAMETER", levelIndicator).traverse(resourceEntry.value.uriParameters)
//        new UriParametersPrinter("BASE URI PARAMETER", levelIndicator).traverse(resourceEntry.value.baseUriParameters)
//        new UriParametersPrinter("RESOLVED URI PARAMETER", levelIndicator).traverse(resourceEntry.value.resolvedUriParameters)
//    }

//    private void printSingleProperties(Map.Entry<String, Resource> resourceEntry) {
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
