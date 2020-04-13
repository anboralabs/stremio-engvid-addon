package co.anbora.labs.engvid.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MetaItem {

    @JsonProperty("id")
    protected String id;
    @JsonProperty("type")
    protected String type;
    @JsonProperty("name")
    protected String name;
    @JsonProperty("poster")
    protected String poster;
    @JsonProperty("description")
    protected String description;
    @JsonProperty("background")
    protected String background;

    public MetaItem(String id, String type, String name, String poster, String description, String background) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.poster = poster;
        this.description = description;
        this.background = background;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getPoster() {
        return poster;
    }

    public String getDescription() {
        return description;
    }

    public String getBackground() {
        return background;
    }
}
