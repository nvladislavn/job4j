package ru.job4j.sql.xml;

import java.io.File;
import java.util.List;

/**
 * Main
 *
 * @author Vladislav Nechaev
 * @since 22.09.2019
 */
public class Main {

    public static void main(String[] args) {
        Config config = new Config();
        List<Entry> entries = null;
        try (StoreSQL storeSQL = new StoreSQL(config)) {
            entries = storeSQL.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        StoreXML storeXML = new StoreXML(new File(config.get("target")));
        storeXML.save(entries);
        ConvertXSLT convertXSLT = new ConvertXSLT();
        convertXSLT.convert(new File(config.get("target")),
                new File(config.get("destination")),
                new File(config.get("xslt")));
        Parser parser = new Parser();
        System.out.println(parser.getSum(new File(config.get("destination"))));
    }
}
