package printer

import org.raml.model.Resource

/**
 * Created by jorge.bautista on 7/12/15.
 */
class ResourceMapEntryPrinter extends AbstractRecursivePrinter<String, Resource> {



    ResourceMapEntryPrinter(String idOfItemToPrint, String levelIndicator) {
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
        println levelIndicator + "uriParameters: " + resource.uriParameters


    }

}
