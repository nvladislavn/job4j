package ru.job4j.sql.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Parser
 *
 * @author Vladislav Nechaev
 * @since 08.10.2019
 */
public class Parser {

    private static final Logger LOG = LogManager.getLogger(Parser.class.getName());
    private int sum;

    /**
     * getSum
     *
     * @param xmlSource - the source xml-file.
     * @return - sum of attributes values.
     */
    public int getSum(File xmlSource) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(xmlSource, new SAXHandler());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return sum;
    }

    private class SAXHandler extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("entry")) {
                sum += Integer.parseInt(
                        attributes.getValue("field")
                );
            }
        }
    }
}
