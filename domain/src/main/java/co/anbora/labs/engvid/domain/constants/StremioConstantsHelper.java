package co.anbora.labs.engvid.domain.constants;

public class StremioConstantsHelper {

    public static final int MIN_EXTRAS = 1;
    public static final String SEARCH = "search";

    private StremioConstantsHelper() {}

    public interface StremioCatalog {
        String BEGINNER_ID_CATALOG = "beginner.english.video";
        String INTERMEDIATE_ID_CATALOG = "intermediate.english.video";
        String ADVANCED_ID_CATALOG = "advanced.english.video";
        String MOVIE = "movie";
        String VIDEO_PREFIX_ID = "engvid:";
    }
}
