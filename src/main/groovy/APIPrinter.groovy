import org.raml.model.Action
import org.raml.model.ActionType
import org.raml.model.MimeType
import org.raml.model.Raml
import org.raml.model.Resource
import org.raml.model.Response
import org.raml.model.parameter.QueryParameter
import org.raml.parser.visitor.RamlDocumentBuilder
import printer.RecursivePrinter

/**
 * Created by jorge.bautista on 4/12/15.
 */
class APIPrinter {

    APIPrinter() {
    }

    public List printEveryResource(Map<String, Resource> resources, RecursivePrinter<String, Resource> recursivePrinter) {

        recursivePrinter.traverse(resources)

        []
    }









}
