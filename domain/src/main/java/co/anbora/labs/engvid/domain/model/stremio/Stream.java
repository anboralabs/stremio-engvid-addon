package co.anbora.labs.engvid.domain.model.stremio;

import java.util.LinkedList;
import java.util.List;

public class Stream {

    private List<StreamItem> streams = new LinkedList<>();

    public List<StreamItem> getStreams() {
        return streams;
    }

    public void addStream(String url) {
        streams.add(new StreamItem(url));
    }
}
