package co.anbora.labs.engvid.domain.model.stremio;


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

    /*public void addMovie(Video video) {
        metas.add(new Movie(video.getId(), video.getTitle(),
                video.getUrlImageMedium(), video.getDescription()));
    }*/
}