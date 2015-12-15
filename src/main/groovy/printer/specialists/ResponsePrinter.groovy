package printer.specialists

import org.raml.model.Response
import printer.AbstractPrinter

/**
 * Created by jorge.bautista on 7/12/15.
 */

class ResponsePrinter extends AbstractPrinter<String, Response> {

    public ResponsePrinter(String idOfItemToPrint, String levelIndicator) {
        super(idOfItemToPrint, levelIndicator)
    }

    @Override
    public void printObject(Map.Entry<String, Response> responseEntry) {
        def response = responseEntry.value

        printText "status: " + responseEntry.key
        printText "body: " + response.body
        printText "description: " + response.description

        def mimeTypes = response.body
        new MimeTypePrinter("Mime Type", getLevelIndicator()).traverse(mimeTypes)
    }


}

