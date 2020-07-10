package ewriters

class TestJob {
	
    def execute(context) {
    	def date = context.mergedJobDataMap.get('date')
        println "Executing..." + date.toString()
    }
}
