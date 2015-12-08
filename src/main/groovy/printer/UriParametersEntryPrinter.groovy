package printer;

import org.raml.model.MimeType

/**
 * Created by jorge.bautista on 7/12/15.
 */
class UriParametersEntryPrinter extends AbstractRecursivePrinter<String, MimeType> {

    UriParametersEntryPrinter(String idOfItemToPrint, String levelIndicator) {
        super(idOfItemToPrint, levelIndicator)
    }

    @Override
    public void printObject(Map.Entry<String, MimeType> mimeTypeEntry) {

        def mimeType = mimeTypeEntry.value
        printText "mimeType: " + mimeTypeEntry.key
        printText "type: " + mimeType.type


    }

}
