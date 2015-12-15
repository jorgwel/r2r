package util

class TestUtilities {

    def static String getTextFromFile(fileName) {
        def filePath = Thread.currentThread().getContextClassLoader().getResource(fileName).file
        def expectedOutput = new File(filePath).getText('UTF-8')
        expectedOutput
    }

}
