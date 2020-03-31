package co.anbora.labs.engvid.api;

import co.anbora.labs.engvid.api.dto.Catalog;
import co.anbora.labs.engvid.api.manifest.Plugin;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Manifest {

    @JsonProperty("id")
    private final String id;
    @JsonProperty("version")
    private final String version;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("description")
    private final String description;
    @JsonProperty("idPrefixes")
    private final String[] idPrefixes;
    @JsonProperty("resources")
    private final String[] resources;
    @JsonProperty("types")
    private final String[] types;
    @JsonProperty("catalogs")
    private final Catalog[] catalogs;
    @JsonProperty("background")
    private final String background;
    @JsonProperty("logo")
    private final String logo;
    @JsonProperty("contactEmail")
    private final String contactEmail;

    public Manifest(Plugin.Version version, Plugin.Resources resources, Plugin.Contact contact) {
        this.id = version.getId();
        this.version = version.getVersion();
        this.name = version.getName();
        this.description = version.getDescription();
        this.idPrefixes = resources.getIdPrefixes();
        this.resources = resources.getResources();
        this.types = resources.getTypes();
        this.catalogs = resources.getCatalogs();
        this.background = contact.getBackground();
        this.logo = contact.getLogo();
        this.contactEmail = contact.getContactEmail();
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
