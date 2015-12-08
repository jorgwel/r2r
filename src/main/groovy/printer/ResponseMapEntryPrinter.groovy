package printer

import org.raml.model.Response

/**
 * Created by jorge.bautista on 7/12/15.
 */

class ResponseMapEntryPrinter extends AbstractRecursivePrinter<String, Response> {

    public ResponseMapEntryPrinter(String idOfItemToPrint, String levelIndicator) {
        super(idOfItemToPrint, levelIndicator)
    }

    @Override
    public void printObject(Map.Entry<String, Response> responseEntry) {

        def response = responseEntry.value

        printText "status: " + responseEntry.key
        printText "body: " + response.body
        printText "description: " + response.description

        def mimeTypes = response.body
        new MimeTypeMapEntryPrinter("Mime Type", getLevelIndicator()).traverse(mimeTypes)

    }


}

