# Operation definition

If you have a file with an [Open API](https://github.com/OAI/OpenAPI-Specification/blob/master/README.md)
definition (a.k.a. a Swagger file) you can render it by a given `method` and `path`, or by `operationId`. 
Both `yaml` and `json` formats are supported.

Let's consider [Uber's API definition](https://github.com/OAI/OpenAPI-Specification/blob/master/examples/v2.0/json/uber.json) as an example:

    :include-open-api: open-api/uber.json {method: "get", path: "/v1/estimates/time"}
    
:include-open-api: open-api/uber.json {method: "get", path: "/v1/estimates/time"}

Note: descriptions are treated as Markdown, using the [CommonMark](http://commonmark.org/help/) specification

# Produces/Consumes

By default `produces` and `consumes` parts are not displayed. If you are documenting a modern API, chances are it will
be consistent and you can specify at the start of your documentation that you are dealing with `application/json`.

Use `showConsumes` and `showProduces` params to show what content types are supported.

    :include-open-api: open-api/uber.json {method: "get", path: "/v1/estimates/time", 
        showConsumes: true, showProduces: true}
    
:include-open-api: open-api/uber.json {method: "get", path: "/v1/estimates/time", 
    showConsumes: true, showProduces: true}


# Auto Section

Use the `autoSection` property to automatically generate a page section with a title taken from an operation summary.

    :include-open-api: open-api/uber.yaml {method: "get", path: "/v1/estimates/time", autoSection: true}

Note: The section below is automatically generated by `include-open-api` 
    
:include-open-api: open-api/uber.yaml {method: "get", path: "/v1/estimates/time", autoSection: true}

# Tags

Specify `tags` to automatically generate multiple entries from a service definition file.

    :include-open-api: open-api/uber.json {tags: "Estimates", autoSection: true}

Note: The sections below are automatically generated by `include-open-api` 

:include-open-api: open-api/uber.json {tags: "Estimates", autoSection: true}
 

