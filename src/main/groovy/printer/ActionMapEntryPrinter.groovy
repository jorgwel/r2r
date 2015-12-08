package printer

import org.raml.model.Action
import org.raml.model.ActionType

/**
 * Created by jorge.bautista on 7/12/15.
 */
class ActionMapEntryPrinter extends AbstractRecursivePrinter<ActionType, Action> {

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
        new QueryParamsMapEntryPrinterAbstract("QUERY PARAM", getLevelIndicator()).traverse(actionEntry.value.queryParameters)
        new ResponseMapEntryPrinterAbstract("RESPONSE", getLevelIndicator()).traverse(actionEntry.value.responses)

    }


}
