package co.anbora.labs.engvid.data.remote.mapper;

import co.anbora.labs.engvid.data.remote.builders.MediaBuilderHelper;
import co.anbora.labs.engvid.domain.constants.HtmlConstantsHelper;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Objects;
import java.util.function.BiFunction;

public class HtmlToLessonMediaMapper implements BiFunction<String, LessonTitle, Lesson> {
    @Override
    public Lesson apply(String html, LessonTitle lessonId) {
        if (Objects.nonNull(html)) {
            Document mediaHtml = Jsoup.parse(html);
            Element video = getElement(mediaHtml, HtmlConstantsHelper.TWITTER_ATTR_PLAYER);

            return Lesson.builder()
                    //.id(lessonId)
                    .imageUrl(MediaBuilderHelper.imageUrl(video))
                    .youtubeId(MediaBuilderHelper.videoId(video))
                    .build();
        }
        return null;
    }

    private Element getElement(Document mediaHtml, String twitterAttrPlayer) {
        return mediaHtml
                .getElementsByAttributeValue(HtmlConstantsHelper.PROPERTY, twitterAttrPlayer).stream()
                .findFirst()
                .orElseGet(() -> new Element(HtmlConstantsHelper.META));
    }
}
