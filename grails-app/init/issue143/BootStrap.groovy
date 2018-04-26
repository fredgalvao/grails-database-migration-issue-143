package issue143

class BootStrap {

	MyUltraImportantBean myUltraImportantBean

	def init = { servletContext ->
		log.debug("what do we have here: ${myUltraImportantBean}")
	}
	def destroy = {
	}
}
