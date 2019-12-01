package ru.job4j.parsers;

import ru.job4j.Item;
import ru.job4j.ParserHTML;
import ru.job4j.config.Config;
import ru.job4j.items.Vacancy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ru.job4j.parsers.ParserSQLRu
 *
 * @author Vladislav Nechaev
 * @since 19.11.2019
 */
public class ParserSQLRu implements ParserHTML {

    private static final Logger LOG = LogManager.getLogger(ParserSQLRu.class.getName());
    private static final String IS_JAVA = "(.*(java|JAVA|Java)\\s*)(?!script|SCRIPT|Script).*";
    private static final String[] MONTHS = {"янв", "фев", "мар", "апр", "май", "июн",
            "июл", "авг", "сен", "окт", "ноя", "дек"};
    private static final String LINK = new Config().getProperties("link");
    private static final String COMMENT_DATE_CELLS = "td[class=altCol]";
    private static final String MESSAGE_DATE_CELLS = "td[class=msgBody]";
    private Calendar lastEntryDate;

    public ParserSQLRu(Calendar lastEntryDate) {
        this.lastEntryDate = lastEntryDate;
    }

    /**
     * getDocument
     * gets a Jsoup document with the specified url.
     *
     * @param url - url for getting the Jsoup document.
     * @return - the Jsoup document.
     */
    private Document getDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(url), 30000);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return doc;
    }

    /**
     * parse
     * parses to the specified url.
     *
     * @return - list with selected vacancies.
     */
    @Override
    public List<Item> parse() {
        List<Item> vacancies = new ArrayList<>();
        int i = 0;
        while (i >= 0) {
            i++;
            String page = i > 1 ? String.valueOf(i) : "";
            String url = String.format(LINK + "%s", page);
            Document html = getDocument(url);
            Elements onlyJava = html.getElementsByClass("postslisttopic")
                    .select(String.format("a:matches(%s)", IS_JAVA));
            for (Element message : onlyJava) {
                Elements children = message.parent().parent().select(COMMENT_DATE_CELLS);
                String commentDateData = children.get(1).text();
                Date lastCommentDate = stringToDate(commentDateData);
                if (lastCommentDate.getTime() < lastEntryDate.getTimeInMillis()) {
                    i = -1;
                    break;
                }
                String msgURL = message.attr("href");
                Document msgHtml = getDocument(msgURL);
                Elements elements = msgHtml.select(MESSAGE_DATE_CELLS);
                String msgText = elements.get(1).text();
                Date msgDate = getMsgDateFromHTML(msgHtml);
                if (msgDate.getTime() > lastEntryDate.getTimeInMillis()) {
                    vacancies.add(new Vacancy(message.text(), msgURL, msgText, msgDate));
                }
            }
        }
        Collections.sort(vacancies, Comparator.comparing(Item::getMsgDate));
        return vacancies;
    }

    /**
     * getMsgDateFromHTML
     * gets date from jsoup document.
     *
     * @param msgHtml - jsoup document.
     * @return - date.
     */
    private Date getMsgDateFromHTML(Document msgHtml) {
        Elements elements = msgHtml.getElementsByClass("msgFooter");
        String stringDate = elements.get(0).text();
        return stringToDate(stringDate);
    }

    /**
     * stringToDate
     * turns a string with date into a Date.
     *
     * @param stringDate - a string with date.
     * @return - Date.
     */
    private Date stringToDate(String stringDate) {
        Date date = new Date();
        if (stringDate.contains("вчера")) {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, -1);
            date = calendar.getTime();
        } else if (!stringDate.contains("сегодня")) {
            DateFormatSymbols dfs = DateFormatSymbols.getInstance(new Locale("ru"));
            dfs.setShortMonths(MONTHS);
            SimpleDateFormat sdf = new SimpleDateFormat("d MMM yy", new Locale("ru"));
            sdf.setDateFormatSymbols(dfs);
            try {
                date = sdf.parse(stringDate);
            } catch (ParseException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return date;
    }
}
