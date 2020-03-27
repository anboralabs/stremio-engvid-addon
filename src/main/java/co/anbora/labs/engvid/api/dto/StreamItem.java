package co.anbora.labs.engvid.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StreamItem {

    @JsonProperty("ytId")
    private final String ytId;

    public StreamItem(String ytId) {
        this.ytId = ytId;
    }

    public String getYtId() {
        return ytId;
    }
}
