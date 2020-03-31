package co.anbora.labs.engvid.api.manifest;

import co.anbora.labs.engvid.api.dto.Catalog;
import co.anbora.labs.engvid.domain.constants.StremioConstants;

import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstants.DEFAULT_IMAGE;
import static co.anbora.labs.engvid.domain.constants.StremioConstants.SEARCH;

public interface Plugin {
    String ID = "co.anbora.labs.engvid.videos";
    String VERSION = "1.0.0";
    String NAME = "English Lessons Videos";
    String DESCRIPTION = "List of videos and courses for english learners: Beginners, Intermediate and Advanced lessons";
    String[] ID_PREFIXES = new String[] {StremioConstants.StremioCatalog.VIDEO_PREFIX_ID};
    String[] RESOURCES = new String[] {"catalog", "meta", "stream"};
    String[] TYPES = new String[] {"movie"};
    Catalog[] CATALOGS = new Catalog[] {
            new Catalog(StremioConstants.StremioCatalog.MOVIE, StremioConstants.StremioCatalog.BEGINNER_ID_CATALOG, "Beginner Lessons Catalog", new String[] {}, new String[0], new String[] {SEARCH}),
            new Catalog(StremioConstants.StremioCatalog.MOVIE, StremioConstants.StremioCatalog.INTERMEDIATE_ID_CATALOG, "Intermediate Lessons Catalog", new String[] {}, new String[0], new String[] {SEARCH}),
            new Catalog(StremioConstants.StremioCatalog.MOVIE, StremioConstants.StremioCatalog.ADVANCED_ID_CATALOG, "Advanced Lessons Catalog", new String[] {}, new String[0], new String[] {SEARCH})
    };
    String BACKGROUND = "";
    String LOGO = DEFAULT_IMAGE;
    String CONTACT_EMAIL = "anboralabs@gmail.com";

    class Version {
        private String id;
        private String version;
        private String name;
        private String description;

        public Version(String id, String version, String name, String description) {
            this.id = id;
            this.version = version;
            this.name = name;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public String getVersion() {
            return version;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }

    class Resources {
        private String[] idPrefixes;
        private String[] resources;
        private String[] types;
        private Catalog[] catalogs;

        public Resources(String[] idPrefixes, String[] resources, String[] types, Catalog[] catalogs) {
            this.idPrefixes = idPrefixes;
            this.resources = resources;
            this.types = types;
            this.catalogs = catalogs;
        }

        public String[] getIdPrefixes() {
            return idPrefixes;
        }

        public String[] getResources() {
            return resources;
        }

        public String[] getTypes() {
            return types;
        }

        public Catalog[] getCatalogs() {
            return catalogs;
        }
    }

    class Contact {

        private String background;
        private String logo;
        private String contactEmail;

        public Contact(String background, String logo, String contactEmail) {
            this.background = background;
            this.logo = logo;
            this.contactEmail = contactEmail;
        }

        public String getBackground() {
            return background;
        }

        public String getLogo() {
            return logo;
        }

        public String getContactEmail() {
            return contactEmail;
        }
    }
}
