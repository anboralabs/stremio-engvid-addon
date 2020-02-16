package co.anbora.labs.engvid.data.remote.model;

import co.anbora.labs.engvid.domain.model.EnglishLevel;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Data
public class LessonInfoDTO {

    @SerializedName("id")
    private Integer id;
    @SerializedName("date")
    private String date;
    @SerializedName("guid")
    private RenderDTO guid;
    @SerializedName("title")
    private RenderDTO title;
    @SerializedName("content")
    private RenderDTO content;
    @SerializedName("link")
    private String link;
    @SerializedName("categories")
    private List<Integer> categories;

    public Integer getEnglishLevel() {
        Optional<EnglishLevel> level = Stream.of(EnglishLevel.values())
                .filter(englishLevel -> categories.contains(englishLevel.getId())).findFirst();
        return level.orElseGet(() -> EnglishLevel.BEGINNER).getId();
    }

}
