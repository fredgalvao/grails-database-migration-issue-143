import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

File defaultTomcatLogFolder = new File('/var/log/tomcat7/')
String logPath = defaultTomcatLogFolder.exists() ? defaultTomcatLogFolder.absolutePath : new File('').absolutePath

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

appender("FILE", FileAppender) {
	file = "${logPath}/main.log"
	encoder(PatternLayoutEncoder) {
		pattern = '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
				'%clr(%5p) ' + // Log level
				'%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
				'%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
				'%m%n%wex' // Message

		outputPatternAsHeader = true
	}
}

def targetDir = BuildSettings.TARGET_DIR
if ((Environment.isDevelopmentMode() || Environment.current == Environment.TEST) && targetDir != null) {
	appender("FULL_STACKTRACE", FileAppender) {
		file = "${targetDir}/stacktrace.log"
		append = true
		encoder(PatternLayoutEncoder) {
			pattern = "%level %logger - %msg%n"
		}
	}
	logger('StackTrace', ERROR, ['FULL_STACKTRACE'], false)
	logger('grails.app', DEBUG, ['FILE'])
	logger('issue143', DEBUG, ['FILE'])
	root(INFO, ['FILE', 'FULL_STACKTRACE'])
} else {
	root(INFO, ['FILE'])
}
