package ru.job4j.sql.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * StoreXML
 *
 * @author Vladislav Nechaev
 * @since 21.09.2019
 */
public class StoreXML {

    private static final Logger LOG = LogManager.getLogger(StoreXML.class.getName());
    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Entry> list) {
        EntriesXML entriesXML = new EntriesXML(list);
        try {
            JAXBContext context = JAXBContext.newInstance(EntriesXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(entriesXML, target);
        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
