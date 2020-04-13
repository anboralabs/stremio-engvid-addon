package co.anbora.labs.engvid.data.remote.model;

import co.anbora.labs.engvid.domain.model.EnglishLevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonInfoDTO {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("date")
    private String date;
    @JsonProperty("guid")
    private RenderDTO guid;
    @JsonProperty("title")
    private RenderDTO title;
    @JsonProperty("content")
    private RenderDTO content;
    @JsonProperty("link")
    private String link;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("categories")
    private List<Integer> categories;

    public Integer getEnglishLevel() {
        Optional<EnglishLevel> level = Stream.of(EnglishLevel.values())
                .filter(englishLevel -> categories.contains(englishLevel.getId())).findFirst();
        return level.orElseGet(() -> EnglishLevel.BEGINNER).getId();
    }

}
