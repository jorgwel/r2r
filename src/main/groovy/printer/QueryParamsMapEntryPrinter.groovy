package printer

import org.raml.model.parameter.QueryParameter

/**
 * Created by jorge.bautista on 7/12/15.
 */
class QueryParamsMapEntryPrinter extends AbstractRecursivePrinter<String, QueryParameter> {

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

