package ewriters

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class NarracionServiceSpec extends Specification {

    NarracionService narracionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Narracion(...).save(flush: true, failOnError: true)
        //new Narracion(...).save(flush: true, failOnError: true)
        //Narracion narracion = new Narracion(...).save(flush: true, failOnError: true)
        //new Narracion(...).save(flush: true, failOnError: true)
        //new Narracion(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //narracion.id
    }

    void "test get"() {
        setupData()

        expect:
        narracionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Narracion> narracionList = narracionService.list(max: 2, offset: 2)

        then:
        narracionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        narracionService.count() == 5
    }

    void "test delete"() {
        Long narracionId = setupData()

        expect:
        narracionService.count() == 5

        when:
        narracionService.delete(narracionId)
        sessionFactory.currentSession.flush()

        then:
        narracionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Narracion narracion = new Narracion()
        narracionService.save(narracion)

        then:
        narracion.id != null
    }
}
