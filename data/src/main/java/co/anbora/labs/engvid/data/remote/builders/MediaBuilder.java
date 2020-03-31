package co.anbora.labs.engvid.data.remote.builders;

import co.anbora.labs.engvid.domain.constants.HtmlConstants;
import org.jsoup.nodes.Element;

import static co.anbora.labs.engvid.domain.constants.Constants.EMPTY_VALUE;
import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstants.VIDEO_ID_PATH;
import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstants.YOUTUBE_EMBED_URL;
import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstants.YOUTUBE_PREVIEW_IMAGE;

public class MediaBuilder {

    private static String getVideoId(Element video) {
        return video.attr(HtmlConstants.CONTENT).replace(YOUTUBE_EMBED_URL, EMPTY_VALUE);
    }

    public static String videoId(Element video) {
        return getVideoId(video);
    }

    public static String imageUrl(Element video) {
        return YOUTUBE_PREVIEW_IMAGE.replace(VIDEO_ID_PATH, getVideoId(video));
    }
}
