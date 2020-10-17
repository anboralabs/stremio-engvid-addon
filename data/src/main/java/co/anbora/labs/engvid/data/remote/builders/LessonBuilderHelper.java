package co.anbora.labs.engvid.data.remote.builders;

import co.anbora.labs.engvid.domain.constants.HtmlConstantsHelper;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;

import static co.anbora.labs.engvid.domain.constants.ConstantsHelper.EMPTY_VALUE;
import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstantsHelper.POST_ID;

public class LessonBuilderHelper {

    public static Long lessonId(Element elementId) {
        String id = elementId.attr(HtmlConstantsHelper.ID).replace(POST_ID, EMPTY_VALUE);
        return Long.parseLong(id);
    }

    public static String title(Element title) {
        return formattedHtml(title.attr(HtmlConstantsHelper.CONTENT));
    }

    public static String description(Element description) {
        return formattedHtml(description.attr(HtmlConstantsHelper.CONTENT));
    }

    private static String formattedHtml(String html) {
        return StringEscapeUtils.unescapeHtml4(
                Jsoup.clean(html, Whitelist.none())
        );
    }
}
