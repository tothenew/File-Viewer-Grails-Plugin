import org.codehaus.groovy.grails.commons.DefaultGrailsApplication

class FileViewerGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.3 > *"
    // the other plugins this plugin depends on
	def dependsOn = [:]

    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "Himanshu Seth, Fabien Benichou"
    def authorEmail = "himanshu@intelligrape.com, fabien.benichou@gmail.com"
    def title = "Plugin summary/headline"
    def description = '''\\
File Viewer Grails plugin provides a user friendly way for viewing folders and files on the system. The most common use-case is to see the logs on the server.
Documentation available at http://github.com/IntelliGrape/File-Viewer-Grails-Plugin/wiki
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/file-viewer"

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before 
    }

    def doWithSpring = {
		// TODO Implement runtime spring config (optional)
		def config = application.config.grails.fileViewer
		fileLocations(com.fileviewer.FileLocations) {
			locations = config.locations ?: [System.getProperty("java.io.tmpdir")]
			linesCount = config.linesCount ?: 300
		}
	}

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
