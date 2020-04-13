package co.anbora.labs.engvid.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
