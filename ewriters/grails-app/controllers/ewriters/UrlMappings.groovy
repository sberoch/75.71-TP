package ewriters

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"narracion")
        "/narraciones"(resources: "narracion")
        "/concurso/participar/$id?"(controller: 'concurso', action: 'participar')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
