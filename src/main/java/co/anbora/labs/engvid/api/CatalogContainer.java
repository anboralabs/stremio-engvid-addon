package co.anbora.labs.engvid.api;


import co.anbora.labs.engvid.api.dto.LessonVideo;
import co.anbora.labs.engvid.api.dto.MetaItem;
import co.anbora.labs.engvid.domain.model.Lesson;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CatalogContainer {

    @JsonProperty("metas")
    private List<MetaItem> metas = new LinkedList<>();

    /**
     * @return the metas
     */
    public List<MetaItem> getMetas() {
        return metas;
    }

    public void addMovie(Lesson lesson) {
        metas.add(new LessonVideo(lesson.getInternalId(), lesson.getTitle(),
                lesson.getImageUrl(), lesson.getDescription(), lesson.getImageUrl()));
    }

    public static CatalogContainer from(List<Lesson> lessons) {
        CatalogContainer catalogContainer = new CatalogContainer();
        lessons.forEach(catalogContainer::addMovie);
        return catalogContainer;
    }
}
