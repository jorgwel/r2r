package printer.specialists

import org.raml.model.parameter.FormParameter
import printer.AbstractPrinter

/**
 * Created by jorge.bautista on 7/12/15.
 */
class FormParametersPrinter extends AbstractPrinter<String, FormParameter> {

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
