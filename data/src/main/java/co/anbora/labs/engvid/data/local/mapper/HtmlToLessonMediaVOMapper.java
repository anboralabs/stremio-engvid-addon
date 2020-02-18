package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Objects;
import java.util.function.BiFunction;

public class HtmlToLessonMediaVOMapper implements BiFunction<String, Integer, LessonMediaVO> {

    @Override
    public LessonMediaVO apply(String html, Integer mediaId) {
        if (Objects.nonNull(html)) {
            Document document = Jsoup.parse(html);

        }
        return null;
    }
}
