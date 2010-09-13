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
				String fileContents = getFileContents(file)
				message = g.render(template: "/file/fileDetails", model: [fileContents: fileContents, filePath: file.absolutePath], plugin:'fileViewer')
			} else {
				List<String> locations = []
				file.eachFile{File subFile->
					locations << subFile.absolutePath
				}
				message = g.render(template: "/file/fileList", model: [locations:locations], plugin:'fileViewer')
				isFile = false
			}
		}
		Map model = [message:message,isFile:isFile]
		render model as JSON
	}


	private def getFileContents(File file) {
		String fileContents;
		List<String> contents = file.text.readLines()
		if (contents.size() > fileLocations.linesCount) {
			int startIndex = contents.size() - (fileLocations.linesCount + 1)
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
