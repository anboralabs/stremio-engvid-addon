package co.anbora.labs.engvid.data.remote.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RenderDTO {

  @JsonProperty("rendered") private String rendered;
}
