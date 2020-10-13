package co.anbora.labs.engvid.data.remote.mapper;

import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static co.anbora.labs.engvid.domain.constants.HtmlConstantsHelper.ALL_LESSONS_CATEGORY_CLASS;
import static co.anbora.labs.engvid.domain.constants.HtmlConstantsHelper.ALL_LESSONS_CLASS;
import static co.anbora.labs.engvid.domain.constants.HtmlConstantsHelper.ALL_LESSONS_LINKS_CLASS;
import static co.anbora.labs.engvid.domain.constants.HtmlConstantsHelper.HREF;

public class HtmlToLessonTitleMapper implements Function<String, List<LessonTitle>> {
    @Override
    public List<LessonTitle> apply(String html) {
        if (Objects.nonNull(html)) {
            Document mediaHtml = Jsoup.parse(html);
            Elements allRows = mediaHtml.getElementsByClass(ALL_LESSONS_CLASS);

            return allRows.stream().map(this::getTitleFromHtmlElement).collect(Collectors.toList());
        }
        return null;
    }

    private LessonTitle getTitleFromHtmlElement(Element lessonHtml) {
        Element lessonAttributes = lessonHtml.getElementsByClass(ALL_LESSONS_LINKS_CLASS)
                .first();
        String slug = lessonAttributes.attr(HREF);
        Element level = lessonAttributes.getElementsByClass(ALL_LESSONS_CATEGORY_CLASS).first();

        return LessonTitle.builder()
                .slug(slug)
                .category(EnglishLevel.getLevel(level.text()))
                .build();
    }

    /*
    return StringEscapeUtils.unescapeHtml4(
                Jsoup.clean(render.getRendered(), Whitelist.none())
        );
     */

}
