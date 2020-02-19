package co.anbora.labs.engvid.data.remote.mapper;

import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Objects;
import java.util.function.BiFunction;

public class HtmlToLessonMediaMapper implements BiFunction<String, Integer, LessonMedia> {
    @Override
    public LessonMedia apply(String html, Integer lessonId) {
        if (Objects.nonNull(html)) {
            Document mediaHtml = Jsoup.parse(html);

        }
        return null;
    }
}
