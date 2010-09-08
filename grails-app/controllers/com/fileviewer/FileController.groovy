package com.fileviewer

import grails.converters.JSON

class FileController {

	def fileLocations

	def index = {
		render(view: 'fileList', model: [locations: fileLocations.locations])
	}

	def showDetails = {
		File file = new File(params.filePath)
		String message = ""
		Boolean isFile = true
		if (file.exists()) {
			if ( file.isFile()) {
				if(FileViewerUtils.isText(file.name)) {
					String fileContents = getFileContents(file)
					message = g.render(template: "fileDetails", model: [fileContents: fileContents, filePath: file.absolutePath])
				} else {
					forward(action:'downloadFile', params:params)
					return
				}
			} else {
				List<String> locations = []
				file.eachFile{File subFile->
					locations << subFile.absolutePath
				}
				message = g.render(template: "fileList", model: [locations:locations])
				isFile = false
			}
		}
		Map model = [message:message,isFile:isFile]
		render model as JSON
	}


	private def getFileContents(File file) {
		String fileContents;
		List<String> contents = file.text.readLines()
		if (contents.size() > 300) {
			int startIndex = contents.size() - 301
			int endIndex = contents.size() - 1
			fileContents = contents.subList(startIndex, endIndex).join("<br/>")
		} else {
			fileContents = contents.join("<br/>")
		}
		return fileContents
	}

	def downloadFile = {
		File file = new File(params.filePath)
		byte[] assetContent =file.readBytes();
		response.setContentLength(assetContent.size())
		response.setHeader("Content-disposition", "attachment; filename=${file.name}")
		String contentType = FileViewerUtils.getMimeContentType(file.name.tokenize(".").last().toString())
		response.setContentType(contentType)
		OutputStream out = response.getOutputStream()
		out.write(assetContent)
		out.flush()
		out.close()
	}
}
