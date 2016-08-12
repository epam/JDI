package ddt.framework

import com.eviware.soapui.model.iface.MessageExchange
import com.eviware.soapui.model.testsuite.TestCaseRunContext
import ddt.framework.model.search.Product
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

import java.util.regex.Pattern
import org.apache.commons.io.IOUtils
import java.util.zip.ZipFile
import groovy.util.logging.Log4j

@Log4j
/**
 * Class with methods witch may be evaluated from the test data file during test step execution.
 */
class EvalUtils {
    static VARIABLES = [:]
    static PRODUCT_VARIABLES = [:]
    private static LINE_ITERATOR = 0

    /**
     * Matched regex with input string and store value from the first matched group to the VARIABLES map with key name.
     *
     * If regex contains no groups or if regex and input string is not matched then no values are stored.
     *
     * @param string - Input string for matching regex;
     * @param name - Name for extracted variable;
     * @param regex - Regular expression to match with input string.
     * @return
     */
    static extractVariable(string, name, regex) {
        def pattern = regex.getClass() != (~/./).class ? Pattern.compile(regex as String) : regex
        def matcher = (string =~ pattern)

        if (matcher.groupCount() < 1) {
            log.error("Cannot extract variable ${name}: matcher groups is not found for pattern ${pattern.pattern()}")
            return
        }

        if (matcher) {
            def matchedSubstring = matcher[0]
            def matchedValue = (matchedSubstring as ArrayList)[1]
            log.info("Extract variable: ${name}=${matchedValue}")
            VARIABLES.put(name, matchedValue)
        } else
            log.info("Cannot extract variable ${name}: regex and string is not matched")
    }

    static String setVariable(name, value) {
        if (name) {
            log.info("Set variable: $name=$value")
            VARIABLES.put(name, value)
            return value
        } else {
            log.info("Cannot set variable '${name}'")
            return ''
        }
    }

    static String getVariable(name) {
        if (name) {
            log.info("Get variable: ${name}=${VARIABLES.get(name)}")
            return VARIABLES.get(name)
        } else {
            log.info("Cannot get variable '${name}'")
            return ""
        }
    }

    static String setProduct(name, value) {
        if (name) {
            def json = [:]
            try {
                json = new JsonSlurper().parseText(value)
            } catch (Exception e) {
                log.error "Unnable to parse product json: $value"
                log.error "message: $e"
                return ''
            }
            def product = new Product(json)
            log.info "Set  product: '$name' = '$value'"
            PRODUCT_VARIABLES.put(name, product)
            return value
        } else {
            log.info "Cannot set product '$name'"
            return ''
        }
    }

    static String getProduct(name) {
        if (name) {
            def product = PRODUCT_VARIABLES.get(name)
            def json = JsonOutput.toJson(product)
            log.info "Get product: '$name'='$json'"
            return json
        } else {
            log.info("Cannot get product '$name'")
            return ''
        }
    }

    /**
     * Get pkpass file from response content and extract the 'pass.json' from it
     *
     * @param messageExchange
     * @return 'pass.json' from pkpass file or empty string.
     */
    static String getPassJson(MessageExchange messageExchange) {
        def filename = getPKPASSFileName(messageExchange)
        //Create temp file with response
        def pkpassFile = File.createTempFile("temp_", "_$filename")
        pkpassFile << messageExchange?.rawResponseData

        //Open pkpass as zip archive
        def zipFile = new ZipFile(pkpassFile)

        //Open 'pass.json' file
        def zippedJson = zipFile.getEntry('pass.json')
        def is = zipFile?.getInputStream(zippedJson)

        //Unzip 'pass.json'
        def unzippedJson = ""
        if (is)
            unzippedJson = IOUtils.toString(is, "UTF-8")
        else
            log.info "The 'pass.json' file is not found in archive"

        log.info "pass.json = '$unzippedJson'"
        //Delete temp file
        pkpassFile.delete()

        return unzippedJson
    }

    /**
     * Get response content length.
     *
     * @param messageExchange - MessageExchange with Response;
     * @return response content length.
     */
    static String getContentLength(MessageExchange messageExchange) {
        def contentLength = messageExchange?.response?.contentLength
        log.info "Content-Length: $contentLength"
        return contentLength
    }

    /**
     * Get byte[] from response and store to file.
     *
     * @param messageExchange
     * @param context
     */
    static void downloadPKPASS(MessageExchange messageExchange, TestCaseRunContext context) {
        def filename = getPKPASSFileName(messageExchange)
        def downloadsPath = "${TestCaseUtils.getDownloadFolder(context)}/$filename"
        def downloadedFile = new File(downloadsPath)

        downloadedFile.getParentFile().mkdirs()

        if (downloadedFile.exists()) downloadedFile.delete()

        log.info "Download pkpass file: $downloadsPath"
        downloadedFile << messageExchange?.rawResponseData
    }

    /**
     * Get Content-Disposition from response Headers.
     *
     * @param messageExchange - MessageExchange with Response;
     * @return Value of Content-Disposition from response Headers.
     */
    static String getContentDisposition(MessageExchange messageExchange) {
        def contentDisposition = messageExchange?.responseHeaders["Content-Disposition"]
        log.info "Content-Disposition: $contentDisposition"
        return contentDisposition
    }

    /**
     * Get name of the PKPASS file from response headers
     *
     * @param messageExchange
     * @return name of the PKPASS file from response headers
     */
    static String getPKPASSFileName(MessageExchange messageExchange) {
        def filename = getContentDisposition(messageExchange).find(/\d+_\d+.pkpass/)
        log.info "PKPASS file name = '$filename'"
        return filename
    }

    /**
     * Convert Cookies from String to Map
     *
     * @param cookies - set-cookie string from response headers
     * @return map - Map<String,String>
     */
    static Map<String, String> convertCookieToMap(String cookies) {
        def map = [:]
        cookies.replaceAll(/[\[\]]/, '').split(',').each {
            it.split(';').each {
                def header = it.split('=')

                if (header?.length > 1)
                    map[header[0].trim()] = header[1].trim()
                else
                    map[header[0].trim()] = ''
            }
        }
        return map
    }

    /**
     * Read text from file.
     *
     * @param filePath - Full path to file to read.
     * @return Returns String with text from file or an empty string if file is not exists.
     */
    static String readFromFile(filePath) {
        def file = new File(filePath as String)

        if (file.exists()) {
            log.info "Read response from file = ${file.absolutePath}"
            return file.text
        }

        log.info("File ${file.absolutePath} is not exist")
        return ""
    }

    /**
     * Iterates on file lines.
     *
     * @param filePath - Full path to file to read.
     * @return Returns String with line from file or an empty string if file is not exists.
     */
    static String getNextLineFromFile(filePath) {
        def file = new File(filePath as String)

        if (file.exists()) {
            log.info "Read response from file = ${file.absolutePath}"
            def linesArray = file.readLines()
            def index = LINE_ITERATOR++ % linesArray.size()
            return linesArray[index]
        }

        log.info("File ${file.absolutePath} is not exist")
        return ""
    }
}
