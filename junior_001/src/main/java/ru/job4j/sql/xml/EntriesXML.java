package ru.job4j.sql.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * EntriesXML
 *
 * @author Vladislav Nechaev
 * @since 23.09.2019
 */
@XmlRootElement(name = "entries")
public class EntriesXML {

    private List<Entry> entries = null;

    public EntriesXML(List<Entry> entries) {
        this.entries = entries;
    }

    public EntriesXML() {
    }

    @XmlElement(name = "entry")
    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
