package com.fileviewer

class FileController {

	def fileLocations

	def index = {
		println fileLocations.locations
		println "Chnaged"
		render(view: 'fileList', model: [locations: fileLocations.locations])
//		render(view: 'fileList', model: [locations: ['/home/himanshu/irofoot', '/tmp/a.txt']])
	}

	def showDetails = {
		println params
		File file = new File(params.filePath)
		String message = "Something..."
		if (file.exists()) {
			if ( file.isFile()) {
				message = file.text
			} else {
				file.eachFileRecurse {File subFile->
					message += "${subFile.name} <br/>"
				}
			}
		}
		render message
	}
}
