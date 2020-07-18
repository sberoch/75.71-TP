package ewriters

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ConcursoServiceSpec extends Specification {

    ConcursoService concursoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Concurso(...).save(flush: true, failOnError: true)
        //new Concurso(...).save(flush: true, failOnError: true)
        //Concurso concurso = new Concurso(...).save(flush: true, failOnError: true)
        //new Concurso(...).save(flush: true, failOnError: true)
        //new Concurso(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //concurso.id
    }

    void "test get"() {
        setupData()

        expect:
        concursoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Concurso> concursoList = concursoService.list(max: 2, offset: 2)

        then:
        concursoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        concursoService.count() == 5
    }

    void "test delete"() {
        Long concursoId = setupData()

        expect:
        concursoService.count() == 5

        when:
        concursoService.delete(concursoId)
        sessionFactory.currentSession.flush()

        then:
        concursoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Concurso concurso = new Concurso()
        concursoService.save(concurso)

        then:
        concurso.id != null
    }
}
