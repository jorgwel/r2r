package printer.specialists

import org.raml.model.parameter.UriParameter
import printer.AbstractPrinter

/**
 * Created by jorge.bautista on 7/12/15.
 */
class UriParametersPrinter extends AbstractPrinter<String, UriParameter> {

    UriParametersPrinter(String idOfItemToPrint, String levelIndicator) {
        super(idOfItemToPrint, levelIndicator)
    }

    @Override
    public void printObject(Map.Entry<String, UriParameter> uriParameterEntry) {

        def uriParameter = uriParameterEntry.value
        printText "Name: " + uriParameterEntry.key
        printText "Type: " + uriParameter.type


    }

}
