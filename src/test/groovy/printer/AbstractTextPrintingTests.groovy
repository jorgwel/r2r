package printer

import spock.lang.Specification

abstract class AbstractTextPrintingTests extends  Specification{

    def outContent = new ByteArrayOutputStream();
    def errContent = new ByteArrayOutputStream();
    def oldOut
    def oldErr

    void setup() {
        /*
        * Since this abstract class is in charge of print,
        * we should have some place where to validate printed text
        * Note: also check out the cleanup() method
        */
        oldOut = System.out
        oldErr = System.err
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    void cleanup() {
        System.setOut oldOut
        System.setErr oldErr
    }

    ByteArrayOutputStream getPrintedContent() {
        return outContent
    }

}
