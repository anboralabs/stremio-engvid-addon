package co.anbora.labs.engvid.domain.model.stremio;


import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.LessonVideo;

import java.util.LinkedList;
import java.util.List;

public class CatalogContainer {
    private List<MetaItem> metas = new LinkedList<>();

    /**
     * @return the metas
     */
    public List<MetaItem> getMetas() {
        return metas;
    }

    public void addMovie(Lesson lesson) {
        metas.add(new LessonVideo(lesson.getId().toString(), lesson.getTitle(),
                lesson.getImageUrl(), lesson.getDescription()));
    }

    public static CatalogContainer from(List<Lesson> lessons) {
        CatalogContainer catalogContainer = new CatalogContainer();
        lessons.forEach(catalogContainer::addMovie);
        return catalogContainer;
    }
}