package ddt.framework

import org.custommonkey.xmlunit.Diff
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier
import org.custommonkey.xmlunit.XMLUnit
import org.xml.sax.SAXParseException

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

import org.w3c.dom.Document
import org.w3c.tidy.Tidy
import org.xml.sax.InputSource
import org.xml.sax.SAXException

import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

import groovy.util.logging.Log4j

/**
 * Class with XML parsers and comparators.
 */
@Log4j
class XMLUtils {

    /**
     * Abstract class implements Callable interface to parse documents in separate thread
     */
    static protected abstract class DocumentAsyncParser implements Callable {

        abstract protected Document parse() throws Exception

        @Override
        Document call() throws Exception {
            return parse()
        }
    }

    /**
     * Class extends abstract class DocumentAsyncParser to parse XML documents.
     *
     * It use javax.xml.parsers.DocumentBuilder to parse document. Do not validate against  dtd schema.
     */
    static private class XMLAsyncParser extends DocumentAsyncParser {

        static private DocumentBuilderFactory DOCUMENT_BUILDER_FACTORY
        private String xml

        static {
            DOCUMENT_BUILDER_FACTORY = initDocumentBuilderFactory()
        }

        XMLAsyncParser(String xml) {
            this.xml = xml
        }

        private static final DocumentBuilderFactory initDocumentBuilderFactory() {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance()

            // don't validate dtd
            factory.setValidating(false)
            try {
                factory.setFeature("http://xml.org/sax/features/namespaces", false)
                factory.setFeature("http://xml.org/sax/features/validation", false)
                factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false)
                factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
            } catch (ParserConfigurationException e) {
                e.printStackTrace()
            }

            //factory.setValidating(false)
            factory.setCoalescing(true)
            factory.setIgnoringComments(true)
            factory.setIgnoringElementContentWhitespace(true)
            factory.setNamespaceAware(true)

            return factory
        }

        protected Document parse() throws SAXException {

            DocumentBuilder builder = DOCUMENT_BUILDER_FACTORY.newDocumentBuilder()

            def document
            try {
                document = builder.parse(new InputSource(new StringReader(this.xml)))
            } catch (SAXParseException saxParseException) {
                log.info "${saxParseException.message}"
                log.info "Parse Document as XHTML"
                def xhtml = HTMLParser.parse(this.xml)
                document = builder.parse(new InputSource(new StringReader(xhtml)))
            }

            return document
        }
    }

    /**
     * Create ThreadPool for 2 thread. Parse each document at separate thread.
     *
     * If any of document is invalid XML shutdown all threads and throw SAXException exception.
     *
     * @param expected
     * @param actual
     * @return CompareResult
     * @throws SAXException
     */
    static Diff compareXML(String expected, String actual) throws SAXException {

        def expectedParser = new XMLAsyncParser(expected.replaceAll(~/&/, '&amp;'))
        def actualParser = new XMLAsyncParser(actual.replaceAll(~/&/, '&amp;'))

        ExecutorService es = Executors.newFixedThreadPool(2)

        Future<Document> expectedFuture = es.submit(expectedParser)
        Future<Document> actualFuture = es.submit(actualParser)

        Document expectedDocument = null
        try {
            expectedDocument = expectedFuture.get()
        } catch (ExecutionException e) {
            log.info "Expected value is not valid XML Document"
            es.shutdownNow()
            throw e.cause
        }

        Document actualDocument = null
        try {
            actualDocument = actualFuture.get()
        } catch (ExecutionException e) {
            log.info "Actual value is not valid XML Document"
            es.shutdownNow()
            throw e.cause
        }

        return compareXML(expectedDocument, actualDocument)
    }

    /**
     * Class with static HTML parser.
     *
     * It use org.w3c.tidy.Tidy to parse HTML and convert it to XHTML.
     */
    static private class HTMLParser {

        static String parse(String html) {
            def tidy = new Tidy()

            def errorOutput = new StringWriter()
            tidy.setErrout(new PrintWriter(errorOutput, true))
            //tidy.setXmlOut(true)
            tidy.setXHTML(true)
            tidy.setTidyMark(false)

            def reader = new StringReader(html)
            def writer = new StringWriter()
            tidy.parse(reader, writer)

            return writer.toString()
        }
    }

    /**
     * Compare expected Document with actual Document and return org.custommonkey.xmlunit.Diff object.
     *
     * @param expected
     * @param actual
     * @return Diff
     */
    static Diff compareXML(Document expected, Document actual) {
        XMLUnit.setIgnoreAttributeOrder(Boolean.TRUE)
        XMLUnit.setIgnoreComments(Boolean.TRUE)
        XMLUnit.setIgnoreDiffBetweenTextAndCDATA(Boolean.TRUE)

        log.info "Compare XML Documents"
        Diff diff = new Diff(expected, actual)
        diff.overrideElementQualifier(new ElementNameAndAttributeQualifier())

        return diff
    }
}