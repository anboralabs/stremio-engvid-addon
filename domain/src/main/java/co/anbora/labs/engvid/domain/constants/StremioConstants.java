package co.anbora.labs.engvid.domain.constants;


import co.anbora.labs.engvid.domain.model.stremio.Catalog;

import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstants.DEFAULT_IMAGE;

public class StremioConstants {

    private StremioConstants() {}

    public static final int MIN_EXTRAS = 1;
    public static final String SEARCH = "search";

    public interface Plugin {
        String ID = "co.anbora.labs.engvid.videos";
        String VERSION = "1.0.0";
        String NAME = "English Lessons Videos";
        String DESCRIPTION = "List of videos and courses for english learners: Beginners, Intermediate and Advanced lessons";
        String[] ID_PREFIXES = null;
        String[] RESOURCES = new String[] {"catalog", "meta", "stream"};
        String[] TYPES = new String[] {"movie"};
        Catalog[] CATALOGS = new Catalog[] {
                new Catalog(StremioCatalog.MOVIE, StremioCatalog.BEGINNER_ID_CATALOG, "Beginner Lessons Catalog", new String[] {}, new String[0], new String[] {SEARCH}),
                new Catalog(StremioCatalog.MOVIE, StremioCatalog.INTERMEDIATE_ID_CATALOG, "Intermediate Lessons Catalog", new String[] {}, new String[0], new String[] {SEARCH}),
                new Catalog(StremioCatalog.MOVIE, StremioCatalog.ADVANCED_ID_CATALOG, "Advanced Lessons Catalog", new String[] {}, new String[0], new String[] {SEARCH})
        };
        String BACKGROUND = "";
        String LOGO = DEFAULT_IMAGE;
        String CONTACT_EMAIL = "anboralabs@gmail.com";
    }

    public interface StremioCatalog {
        String BEGINNER_ID_CATALOG = "beginner.english.video";
        String INTERMEDIATE_ID_CATALOG = "intermediate.english.video";
        String ADVANCED_ID_CATALOG = "advanced.english.video";
        String MOVIE = "movie";
    }

}
