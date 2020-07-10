package ewriters

class DeterminarGanadorConcursoJob {

    def execute(context) {
        def concurso = context.mergedJobDataMap.get('concurso')
        concurso.finalizar()
        println "Termino el concurso de " + concurso.creador.nombreApellido
    }
}
