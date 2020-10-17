package co.anbora.labs.engvid.data.remote.mapper;

import co.anbora.labs.engvid.data.remote.builders.LessonBuilderHelper;
import co.anbora.labs.engvid.data.remote.builders.MediaBuilderHelper;
import co.anbora.labs.engvid.domain.constants.HtmlConstantsHelper;
import co.anbora.labs.engvid.domain.exceptions.LessonNotFoundException;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.BiFunction;

import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstantsHelper.ERROR_ID;
import static co.anbora.labs.engvid.domain.constants.HtmlConstantsHelper.ID_VIDEO_CLASS;

public class HtmlToLessonMediaMapper implements BiFunction<String, LessonTitle, Lesson> {
    @Override
    public Lesson apply(String html, LessonTitle lessonTitle) {
        if (Objects.nonNull(html)) {
            Document mediaHtml = Jsoup.parse(html);
            checkLessonVideo(mediaHtml);

            return getLesson(mediaHtml, lessonTitle);
        }
        return null;
    }

    private Lesson getLesson(Document mediaHtml, LessonTitle lessonTitle) {
        Element id = getLessonId(mediaHtml);
        Element title = getTitleInformation(mediaHtml);
        Element description = getDescriptionInformation(mediaHtml);
        Element video = getVideoInformation(mediaHtml);

        LocalDateTime now = LocalDateTime.now();

        return Lesson.builder()
                .id(LessonBuilderHelper.lessonId(id))
                .title(LessonBuilderHelper.title(title))
                .description(LessonBuilderHelper.description(description))
                .category(lessonTitle.getCategory().getId().longValue())
                .date(now.toString())
                .renderLink(lessonTitle.getRenderLink())
                .slug(lessonTitle.getSlug())
                .imageUrl(MediaBuilderHelper.imageUrl(video))
                .youtubeId(MediaBuilderHelper.videoId(video))
                .build();
    }

    private void checkLessonVideo(Document mediaHtml) {
        Element error404 = mediaHtml.getElementById(ERROR_ID);
        if (error404 != null) {
            throw new LessonNotFoundException();
        }
    }

    private Element getLessonId(Document mediaHtml) {
        return mediaHtml.getElementsByClass(ID_VIDEO_CLASS)
                .first();
    }

    private Element getTitleInformation(Document mediaHtml) {
        return getElementByPropertyAndValue(mediaHtml, HtmlConstantsHelper.TITLE_ATTR);
    }

    private Element getDescriptionInformation(Document mediaHtml) {
        return getElementByPropertyAndValue(mediaHtml, HtmlConstantsHelper.DESCRIPTION_ATTR);
    }

    private Element getVideoInformation(Document mediaHtml) {
        return getElementByPropertyAndValue(mediaHtml, HtmlConstantsHelper.TWITTER_ATTR_PLAYER);
    }

    private Element getElementByPropertyAndValue(Document mediaHtml, String propertyValue) {
        return mediaHtml
                .getElementsByAttributeValue(HtmlConstantsHelper.PROPERTY, propertyValue).stream()
                .findFirst()
                .orElseGet(() -> new Element(HtmlConstantsHelper.META));
    }
}
