package co.anbora.labs.engvid.domain.model.stremio;

import lombok.Data;

@Data
public class Catalog {

    private final String type;
    private final String id;
    private final String name;
    private final String[] genres;
    private final String[] extraRequired;
    private final String[] extraSupported;

}
