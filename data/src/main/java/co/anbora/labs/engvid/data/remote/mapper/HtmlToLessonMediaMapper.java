package co.anbora.labs.engvid.data.remote.mapper;

import co.anbora.labs.engvid.domain.constants.HtmlConstants;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Objects;
import java.util.function.BiFunction;

import static co.anbora.labs.engvid.domain.constants.Constants.EMPTY_VALUE;
import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstants.YOUTUBE_EMBED_URL;

public class HtmlToLessonMediaMapper implements BiFunction<String, Long, LessonMedia> {
    @Override
    public LessonMedia apply(String html, Long lessonId) {
        if (Objects.nonNull(html)) {
            Document mediaHtml = Jsoup.parse(html);
            Element video = getElement(mediaHtml, HtmlConstants.TWITTER_ATTR_PLAYER);
            Element image = getElement(mediaHtml, HtmlConstants.TWITTER_ATTR_IMAGE);

            return LessonMedia.builder()
                    .id(lessonId)
                    .imageUrl(image.attr(HtmlConstants.CONTENT))
                    .youtubeId(video.attr(HtmlConstants.CONTENT).replace(YOUTUBE_EMBED_URL, EMPTY_VALUE))
                    .sync(true)
                    .build();
        }
        return null;
    }

    private Element getElement(Document mediaHtml, String twitterAttrPlayer) {
        return mediaHtml
                .getElementsByAttributeValue(HtmlConstants.PROPERTY, twitterAttrPlayer).stream()
                .findFirst()
                .orElseGet(() -> new Element(HtmlConstants.META));
    }
}
