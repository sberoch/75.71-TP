package ewriters
import java.time.*

class BootStrap {

    def init = { servletContext ->
    	//def date = new Date(System.currentTimeMillis())
    	//println "Starting exec at: " + date.toString()
    	//date = Date.from(LocalDateTime.now().plusSeconds(7).atZone(ZoneId.systemDefault()).toInstant())
    	//println "Will trigger at: " + date.toString()
    	//TestJob.schedule(date, [date: date])
    }
    def destroy = {
    }
}
