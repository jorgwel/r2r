package restconverters

import apitree.apiitems.GenericRESTResource
import org.raml.model.Resource

class ResourceConverter implements RESTElementConverter <Map.Entry<String, Resource>, GenericRESTResource>{
    @Override
    GenericRESTResource convert(Map.Entry<String, Resource> resourceEntry) {
        def resource = resourceEntry.getValue()
        println "ssuri: ${resource}"
        new GenericRESTResource(
                name: resourceEntry.key,
                uri: resource.uri,
                
                relativeUri: resource.relativeUri,
                displayName: resource.displayName,
                description: resource.description,
                type: resource.type
        )
    }
}
