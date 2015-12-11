package printer.specialists;

import org.raml.model.MimeType
import printer.AbstractRecursivePrinter

/**
 * Created by jorge.bautista on 7/12/15.
 */
class MimeTypePrinter extends AbstractRecursivePrinter<String, MimeType> {

    MimeTypePrinter(String idOfItemToPrint, String levelIndicator) {
        super(idOfItemToPrint, levelIndicator)
    }

    @Override
    public void printObject(Map.Entry<String, MimeType> mimeTypeEntry) {

        def mimeType = mimeTypeEntry.value
        printText "mimeType: " + mimeTypeEntry.key
        printText "type: " + mimeType.type
        printText "schema: " + mimeType.schema
        printText "Body: " + mimeType.getFormParameters()

        new FormParametersPrinter("FORM PARAMETER", getLevelIndicator()).traverse(mimeTypeEntry.value.formParameters)


    }

}
