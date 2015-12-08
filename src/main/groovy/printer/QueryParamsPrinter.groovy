package printer

import org.raml.model.parameter.QueryParameter

/**
 * Created by jorge.bautista on 7/12/15.
 */
class QueryParamsPrinter extends AbstractRecursivePrinter<String, QueryParameter> {

    QueryParamsPrinter(String idOfItemToPrint, String levelIndicator) {
        super(idOfItemToPrint, levelIndicator)
    }

    @Override
    public void printObject(Map.Entry<String, QueryParameter> queryParameterEntry) {

        def queryParameter = queryParameterEntry.value
        printText "--"
        printText "name: " + queryParameterEntry.key
        printText "displayName: " + queryParameter.displayName
        printText "description: " + queryParameter.description
        printText "type: " + queryParameter.type

    }


}

