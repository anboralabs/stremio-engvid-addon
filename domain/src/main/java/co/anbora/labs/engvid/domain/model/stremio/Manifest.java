package co.anbora.labs.engvid.domain.model.stremio;

import lombok.Data;

@Data
public class Manifest {

    final String id;
    final String version;
    final String name;
    final String description;
    final String[] idPrefixes;
    final String[] resources;
    final String[] types;
    final Catalog[] catalogs;
    final String background;
    final String logo;
    final String contactEmail;

}