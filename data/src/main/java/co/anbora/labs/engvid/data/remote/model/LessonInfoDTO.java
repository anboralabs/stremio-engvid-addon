package co.anbora.labs.engvid.data.remote.model;

import co.anbora.labs.engvid.domain.model.EnglishLevel;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Data
public class LessonInfoDTO {

    private Integer id;
    private String date;
    private RenderDTO guid;
    private RenderDTO title;
    private RenderDTO content;
    private String link;
    private List<Integer> categories;

    public Integer getEnglishLevel() {
        Optional<EnglishLevel> level = Stream.of(EnglishLevel.values())
                .filter(englishLevel -> categories.contains(englishLevel.getId())).findFirst();
        return level.orElseGet(() -> EnglishLevel.BEGINNER).getId();
    }

}
