package org.grails.plugins.fileviewer

import org.grails.plugins.fileviewer.FileViewerUtils

class FileController {

	def fileLocations

	def index = {
		Map model = [locations:fileLocations.locations]
		if (params.filePath) {
			File file = new File(params.filePath)
			if (file.exists()) {
				if ( file.isFile()) {
					List locations = getSubFiles(file.parentFile)
					String fileContents = getFileContents(file)
					model= [locations: locations, fileContents: fileContents, filePath: file.absolutePath]
				} else {
					List locations = getSubFiles(file)
					model= [locations:locations]
				}
				if(!fileLocations.locations.contains(file.absolutePath)){
					model['prevLocation'] = file.getParentFile().absolutePath
				}
				model['showBackLink'] = true
			}
		}
		render(view: "/file/fileList", model: model, plugin:'fileViewer')
	}

	private List getSubFiles(File file) {
		List<String> locations = []
		file.eachFile {File subFile ->
			locations << subFile.absolutePath
		}
		return locations
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
