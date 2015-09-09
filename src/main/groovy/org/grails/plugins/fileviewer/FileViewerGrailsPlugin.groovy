package org.grails.plugins.fileviewer

import grails.plugins.Plugin

class FileViewerGrailsPlugin extends Plugin {

    def grailsVersion = '3.0.0 > *'
    def title = 'File Viewer plugin'
    def description = 'Provides a user-friendly way for viewing folders and files on the system, for example logs on the server.'
    def documentation = 'http://github.com/IntelliGrape/File-Viewer-Grails-Plugin/wiki'
    def developers = [
        [name: 'Himanshu Seth', email: 'himanshu@intelligrape.com'],
        [name: 'Fabien Benichou', email: 'fabien.benichou@gmail.com']
    ]
    def issueManagement = [url: 'http://github.com/IntelliGrape/File-Viewer-Grails-Plugin/issues']
    def scm = [url: 'http://github.com/IntelliGrape/File-Viewer-Grails-Plugin']
    def profiles = ['web']

    Closure doWithSpring() {{ ->
        def conf = config.grails.fileViewer

        fileLocations(FileLocations) {
            locations = conf.locations ?: [System.getProperty("java.io.tmpdir")]
            linesCount = conf.linesCount ?: 300
            areDoubleDotsAllowedInFilePath = conf.areDoubleDotsAllowedInFilePath ?: false
        }
    }}
}
