package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HarbCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer",
            SOURCE_LINK);

    public static String retrieveDescription(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Element row = document.selectFirst(".job_show_description__vacancy_description");
        Element descriptionElement = row.selectFirst(".style-ugc");
        return descriptionElement.text();
    }

    public static void main(String[] args) throws IOException {
        for (int page = 1; page <= 5; page++) {
            Connection connection = Jsoup.connect(String.format("%s?page=%d",
                    PAGE_LINK,
                    page));
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                String vacancyName = titleElement.text();
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                Element dateElement = row.select(".vacancy-card__date").first().child(0);
                System.out.printf("%s %s %s%n",
                        new HarbCareerDateTimeParser().parse(dateElement.attr("datetime")),
                        vacancyName,
                        link);
                String text = null;
                try {
                    text = retrieveDescription(link);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("< " + text + " />");
            });
        }
    }
}