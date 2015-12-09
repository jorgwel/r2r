package printer;

import org.raml.model.MimeType
import org.raml.model.parameter.FormParameter

/**
 * Created by jorge.bautista on 7/12/15.
 */
class FormParametersPrinter extends AbstractRecursivePrinter<String, FormParameter> {

    FormParametersPrinter(String idOfItemToPrint, String levelIndicator) {
        super(idOfItemToPrint, levelIndicator)
    }

    @Override
    public void printObject(Map.Entry<String, FormParameter> formParameterEntry) {

        def formParameter = formParameterEntry.value
        printText "name: " + formParameterEntry.key
        printText "description: " + formParameter.description
        printText "required: " + formParameter.required



    }

}
