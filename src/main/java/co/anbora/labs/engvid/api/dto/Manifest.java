package co.anbora.labs.engvid.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Manifest {

  @JsonProperty("id") final String id;
  @JsonProperty("version") final String version;
  @JsonProperty("name") final String name;
  @JsonProperty("description") final String description;
  @JsonProperty("idPrefixes") final String[] idPrefixes;
  @JsonProperty("resources") final String[] resources;
  @JsonProperty("types") final String[] types;
  @JsonProperty("catalogs") final Catalog[] catalogs;
  @JsonProperty("background") final String background;
  @JsonProperty("logo") final String logo;
  @JsonProperty("contactEmail") final String contactEmail;

  public Manifest(String id, String version, String name, String description,
                  String[] idPrefixes, String[] resources, String[] types,
                  Catalog[] catalogs, String background, String logo,
                  String contactEmail) {
    this.id = id;
    this.version = version;
    this.name = name;
    this.description = description;
    this.idPrefixes = idPrefixes;
    this.resources = resources;
    this.types = types;
    this.catalogs = catalogs;
    this.background = background;
    this.logo = logo;
    this.contactEmail = contactEmail;
  }

  public String getId() { return id; }

  public String getVersion() { return version; }

  public String getName() { return name; }

  public String getDescription() { return description; }

  public String[] getIdPrefixes() { return idPrefixes; }

  public String[] getResources() { return resources; }

  public String[] getTypes() { return types; }

  public Catalog[] getCatalogs() { return catalogs; }

  public String getBackground() { return background; }

  public String getLogo() { return logo; }

  public String getContactEmail() { return contactEmail; }
}