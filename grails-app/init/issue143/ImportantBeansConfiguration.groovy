package issue143

import grails.core.GrailsApplication
import grails.util.Holders
import grails.web.context.ServletContextHolder
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.servlet.ServletContext

@CompileStatic
@Configuration
@Slf4j
class ImportantBeansConfiguration {

	@Autowired
	GrailsApplication grailsApplication
	@Autowired
	ServletContext servletContext

	@Bean
	MyUltraImportantBean myUltraImportantBean() {
		log.debug("@Autowired servletContext::${servletContext}")
		log.debug("Holders.servletContext::${Holders.servletContext}")
		log.debug("ServletContextHolder.servletContext::${ServletContextHolder.servletContext}")
		log.debug("@Autowired grailsApplication.mainContext::${grailsApplication.mainContext}")
		log.debug("@Autowired grailsApplication.mainContext?.servletContext::${grailsApplication.mainContext?.getBean('servletContext')}")
		log.debug("this.getClass().getResource(\"/\").getPath()::${this.getClass().getResource("/").getPath()}")
		log.debug("getProtectionDomain().getCodeSource()::${getClass().getProtectionDomain().getCodeSource().getLocation().getFile().replace(getClass().getSimpleName() + ".class", "").substring(1)}")
		log.debug("this.getClass().getClassLoader().getResource('.')::${this.getClass().getClassLoader().getResource('.')}")
		log.debug("this.getClass().getClassLoader().getResource('/')::${this.getClass().getClassLoader().getResource('/')}")
		log.debug("@Autowired servletContext.getRealPath('.')::${servletContext.getRealPath('.')}")
		log.debug("@Autowired servletContext.getRealPath('/')::${servletContext.getRealPath('/')}")

		return new MyUltraImportantBean(yes: 'maybe')
	}
}
