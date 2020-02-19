package co.anbora.labs.engvid.domain.model.stremio;

import co.anbora.labs.engvid.domain.model.Lesson;

import java.util.LinkedList;
import java.util.List;

public class Stream {

    private List<StreamItem> streams = new LinkedList<>();

    public List<StreamItem> getStreams() {
        return streams;
    }

    public Stream addStream(String url) {
        streams.add(new StreamItem(url));
        return this;
    }

    public static Stream from(Lesson lesson) {
        return new Stream().addStream(lesson.getYoutubeId());
    }
}
