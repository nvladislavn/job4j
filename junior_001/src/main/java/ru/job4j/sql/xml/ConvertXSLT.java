package ru.job4j.sql.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * ConvertXSLT
 *
 * @author Vladislav Nechaev
 * @since 24.09.2019
 */
public class ConvertXSLT {

    private static final Logger LOG = LogManager.getLogger(ConvertXSLT.class.getName());

    /**
     * convert
     *
     * @param source - the source xml-file
     * @param dest - the target xml-file
     * @param scheme - xsl-schema
     */
    public void convert(File source, File dest, File scheme) {
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer(new StreamSource(scheme));
            transformer.transform(new StreamSource(source), new StreamResult(dest));
        } catch (TransformerException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
