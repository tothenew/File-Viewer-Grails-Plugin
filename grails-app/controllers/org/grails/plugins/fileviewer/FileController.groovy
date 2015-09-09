package org.grails.plugins.fileviewer

/**
 * @author Himanshu Seth (himanshu@intelligrape.com)
 * @author Fabien Benichou (fabien.benichou@gmail.com)
 */
class FileController {

    FileLocations fileLocations

    def index(String filePath) {
        Map model = [locations: fileLocations.locations]
        if (filePath) {
            File file = new File(filePath)
            if (fileLocations.isValidPath(filePath) && file.exists()) {
                if (file.isFile()) {
                    List locations = getSubFiles(file.parentFile)
                    String fileContents = getFileContents(file)
                    model = [locations: locations, fileContents: fileContents, filePath: file.absolutePath]
                }
                else {
                    model = [locations: getSubFiles(file)]
                }
                if (!fileLocations.locations.contains(file.absolutePath)) {
                    model.prevLocation = file.parentFile?.absolutePath
                }
                model.showBackLink = true
            }
            else {
                model.errorMessage = message(code: 'default.path.invalid.message')
            }
        }
        render(view: "/file/fileList", model: model, plugin: 'fileViewer')
    }

    def downloadFile(String filePath) {
        File file = new File(filePath)
        byte[] assetContent = file.readBytes()
        response.contentLength = assetContent.size()
        response.setHeader("Content-disposition", "attachment; filename=$file.name")
        String contentType = FileViewerUtils.getMimeContentType(file.name.tokenize(".").last().toString())
        response.contentType = contentType
        OutputStream out = response.outputStream
        out.write(assetContent)
        out.flush()
        out.close()
    }

    private List<String> getSubFiles(File file) {
        List<String> locations = []
        file.eachFile { File subFile -> locations << subFile.absolutePath }
        locations
    }

    /**
     * Reads file line by line.
     * @return file contents formatted by <br/> html tag
     */
    private String getFileContents(File file) {
        List<String> contents = file.text.readLines()
        List<String> lines
        if (contents.size() > fileLocations.linesCount) {
            int startIndex = contents.size() - (fileLocations.linesCount + 1)
            int endIndex = contents.size() - 1
            lines = contents.subList(startIndex, endIndex)
        }
        else {
            lines = contents
        }
        lines*.encodeAsHTML().join("<br/>")
    }
}
