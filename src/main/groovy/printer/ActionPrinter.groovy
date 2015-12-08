package printer

import org.raml.model.Action
import org.raml.model.ActionType

/**
 * Created by jorge.bautista on 7/12/15.
 */
class ActionPrinter extends AbstractRecursivePrinter<ActionType, Action> {

    ActionPrinter(String idOfItemToPrint, String levelIndicator) {
        super(idOfItemToPrint, levelIndicator)
    }

    @Override
    public void printObject(Map.Entry<ActionType, Action> actionEntry) {
        def action = actionEntry.value
        printText "--"
        printText "Action: " + actionEntry.key
        printText "Type: " + action.type
        printText "action uri: " + action.baseUriParameters
        new QueryParamsPrinter("QUERY PARAM", getLevelIndicator()).traverse(actionEntry.value.queryParameters)
        new ResponsePrinter("RESPONSE", getLevelIndicator()).traverse(actionEntry.value.responses)

    }


}
