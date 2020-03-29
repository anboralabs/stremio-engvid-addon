package co.anbora.labs.engvid.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Catalog {

  @JsonProperty("type") private final String type;
  @JsonProperty("id") private final String id;
  @JsonProperty("name") private final String name;
  @JsonProperty("genres") private final String[] genres;
  @JsonProperty("extraRequired") private final String[] extraRequired;
  @JsonProperty("extraSupported") private final String[] extraSupported;

  public Catalog(String type, String id, String name, String[] genres,
                 String[] extraRequired, String[] extraSupported) {
    this.type = type;
    this.id = id;
    this.name = name;
    this.genres = genres;
    this.extraRequired = extraRequired;
    this.extraSupported = extraSupported;
  }

  public String getType() { return type; }

  public String getId() { return id; }

  public String getName() { return name; }

  public String[] getGenres() { return genres; }

  public String[] getExtraRequired() { return extraRequired; }

  public String[] getExtraSupported() { return extraSupported; }
}
