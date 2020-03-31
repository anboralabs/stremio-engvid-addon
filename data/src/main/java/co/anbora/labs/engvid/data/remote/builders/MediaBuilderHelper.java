package co.anbora.labs.engvid.data.remote.builders;

import co.anbora.labs.engvid.domain.constants.HtmlConstantsHelper;
import org.jsoup.nodes.Element;

import static co.anbora.labs.engvid.domain.constants.ConstantsHelper.EMPTY_VALUE;
import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstantsHelper.VIDEO_ID_PATH;
import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstantsHelper.YOUTUBE_EMBED_URL;
import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstantsHelper.YOUTUBE_PREVIEW_IMAGE;

public class MediaBuilderHelper {

    private static String getVideoId(Element video) {
        return video.attr(HtmlConstantsHelper.CONTENT).replace(YOUTUBE_EMBED_URL, EMPTY_VALUE);
    }

    public static String videoId(Element video) {
        return getVideoId(video);
    }

    public static String imageUrl(Element video) {
        return YOUTUBE_PREVIEW_IMAGE.replace(VIDEO_ID_PATH, getVideoId(video));
    }
}
