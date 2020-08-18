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
        "/narracion/$id/responder/$id"(controller: 'narracion', action: 'responder')
        "/concursos"(resources: "concurso")
        "/talleres"(resources: "taller")
        "/concurso/participar/$id?"(controller: 'concurso', action: 'participar')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
