package restconverters

import apitree.apiitems.RESTResource
import org.raml.model.Resource

class ResourceConverter implements RESTElementConverter <Map.Entry<String, Resource>, RESTResource>{
    @Override
    RESTResource convert(Map.Entry<String, Resource> resourceEntry) {
        def resource = resourceEntry.getValue()
        println "ssuri: ${resource}"
        new RESTResource(
                name: resourceEntry.key,
                uri: resource.uri,
                
                relativeUri: resource.relativeUri,
                displayName: resource.displayName,
                description: resource.description,
                type: resource.type
        )
    }
}
